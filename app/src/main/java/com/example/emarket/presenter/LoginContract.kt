package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.model.local.entity.User

interface LoginContract {
    interface View {
        fun navigateToSignup()
        fun navigateToProfile()

    }

    interface Presenter {
        fun checkLoginRemote(email: String, password:String)
        fun getLoginPreference() : Boolean
        fun setLoginPreference(loggedIn: Boolean)
        fun setUserPreference(user: User)



    }

    interface ResponseCallback {
        fun onResponse(status: Int, message: String, user: User?)
        fun onError(errorMessage: String)
    }
}