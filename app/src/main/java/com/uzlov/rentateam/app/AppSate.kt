package com.uzlov.rentateam.app

import com.uzlov.rentateam.repo.models.UserPage

sealed class AppSate {
    data class Success(val page: UserPage) : AppSate()
    data class Error(val t: Throwable) : AppSate()
    data class Loading(val isLoading : Boolean) : AppSate()

}