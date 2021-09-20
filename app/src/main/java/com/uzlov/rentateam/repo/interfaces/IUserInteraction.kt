package com.uzlov.rentateam.repo.interfaces

import com.uzlov.rentateam.repo.models.UserPage
import io.reactivex.rxjava3.core.Single

interface IUserInteraction {
    fun getUsers(isOnline: Boolean): Single<UserPage>
}