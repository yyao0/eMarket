package com.example.emarket.presenter

import com.example.emarket.model.local.entity.User

interface MainContract {
    interface View {
        fun initNavigationDrawer()
        fun navigateToLogin()

    }

    interface Presenter{
        fun activateSplashOnDestroy()
        fun getUserPreference() : User
        fun logoutRemote(email: String)



    }

    interface LogoutCallback {
        fun onResponse(status: Int, message: String)
        fun onError(errorMessage: String)
    }

}