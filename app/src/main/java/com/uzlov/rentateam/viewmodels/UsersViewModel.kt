package com.uzlov.rentateam.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uzlov.rentateam.app.App
import com.uzlov.rentateam.app.AppSate
import com.uzlov.rentateam.network_android.INetworkStatus
import com.uzlov.rentateam.repo.interfaces.IUserInteraction
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class UsersViewModel(
    private val usersLiveData: MutableLiveData<AppSate> = MutableLiveData()
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    @field:Named("ui")
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var userRepository: IUserInteraction
    @Inject
    lateinit var networkStatus: INetworkStatus

    init {
        App.appComponent.inject(this)
        loadUser()
    }

    fun observe(): LiveData<AppSate> = usersLiveData

    private fun loadUser() {
        networkStatus.isOnline().flatMap { isOnline ->
            if (!isOnline) {
                usersLiveData.postValue(AppSate.Error(Exception("Отсутствует интернет соединение. Загрузка из кеша!")))
                userRepository.getUsers(isOnline)
                    .observeOn(uiScheduler)
                    .doOnSubscribe {
                        compositeDisposable.add(it)
                        usersLiveData.postValue(AppSate.Loading(isLoading = true))
                    }.toObservable()
            } else {
                // loading from local or remote service with message to user
                userRepository.getUsers(isOnline)
                    .observeOn(uiScheduler)
                    .doOnSubscribe {
                        compositeDisposable.add(it)
                        usersLiveData.postValue(AppSate.Loading(isLoading = true))
                    }.toObservable()
            }
        }.subscribe({
            usersLiveData.postValue(AppSate.Loading(isLoading = false))
            if (!it.data.isNullOrEmpty()) {
                usersLiveData.postValue(AppSate.Success(it))
            } else {
                usersLiveData.postValue(AppSate.Error(Exception("Users is empty")))
            }
        }, {
            usersLiveData.postValue(AppSate.Error(it))
        })
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}