package com.example.emarket.presenter

import android.content.Context

interface LoginContract {
    interface View {
        fun navigateToSignup()

        fun navigateToProfile()

        fun showLoginMessage(message: String)
    }

    interface Presenter {
        fun checkLogin(username: String, password:String)

        fun getLoginPreference() : Boolean

        fun setLoginPreference(loggedIn: Boolean)

    }
}