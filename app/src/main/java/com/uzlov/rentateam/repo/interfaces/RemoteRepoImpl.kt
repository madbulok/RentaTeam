package com.uzlov.rentateam.repo.interfaces

import com.uzlov.rentateam.repo.api.ServerApi
import com.uzlov.rentateam.repo.models.Data
import com.uzlov.rentateam.repo.models.Support
import com.uzlov.rentateam.repo.models.UserPage
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val api: ServerApi,
    private val userInteractions: ILocalUserInteractions,
    val scheduler: Scheduler
) : IUserInteraction {
    override fun getUsers(isOnline: Boolean): Single<UserPage> {
        if (isOnline) {
            return api.getUsers()
                .subscribeOn(scheduler)
                .flatMap {
                    it.data?.let<List<Data>, @NonNull Single<UserPage>?> { list ->
                        userInteractions.saveUser(list).subscribe({

                        },{ error->
                            error.printStackTrace()
                        })
                        Single.just(
                            UserPage(
                                data = list,
                                page = 1,
                                per_page = list.size,
                                support = Support("", ""),
                                total = list.size,
                                total_pages = 1
                            )
                        )
                    }
                }
        } else {
            return userInteractions.getUsers()
                .subscribeOn(scheduler).flatMap { list ->
                    Single.just(
                        UserPage(
                            data = list,
                            page = 1,
                            per_page = list.size,
                            support = Support("", ""),
                            total = list.size,
                            total_pages = 1
                        )
                    )
                }
        }

    }
}