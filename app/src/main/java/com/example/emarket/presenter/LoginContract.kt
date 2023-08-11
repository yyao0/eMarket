package com.example.emarket.presenter

import android.content.Context

interface LoginContract {
    interface View {
        fun navigateToSignup()

        fun navigateToProfile()

    }

    interface Presenter {
        fun checkLogin(username: String, password:String)

        fun getLoginPreference() : Boolean

        fun setLoginPreference(loggedIn: Boolean)

    }
}