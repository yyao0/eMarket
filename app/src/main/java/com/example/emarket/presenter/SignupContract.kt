package com.example.emarket.presenter

interface SignupContract {

    interface View {
        fun navigateToProfile()
    }

    interface Presenter {
        fun checkSignup(username: String, password:String, password2: String)

        fun setLoginPreference(loggedIn: Boolean)
    }
}