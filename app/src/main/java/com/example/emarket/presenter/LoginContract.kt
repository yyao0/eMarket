package com.example.emarket.presenter

import android.content.Context

interface LoginContract {
    interface View {
        fun navigateToSignup()

        fun navigateToProfile()

    }

    interface Presenter {
        fun checkLoginFromAPI(email: String, password:String, callback: ResponseCallback)

        fun getLoginPreference() : Boolean

        fun setLoginPreference(loggedIn: Boolean)

    }

    interface ResponseCallback {
        fun onResponse(status: Int, message: String)
        fun onError(errorMessage: String)
    }
}