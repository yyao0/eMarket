package com.example.emarket.presenter

interface SignupContract {

    interface View {
        fun navigateToLogin()
    }

    interface Presenter {
        fun checkSignupRemote(fullName: String, mobileNo: String, emailId: String, password: String,)

        fun setLoginPreference(loggedIn: Boolean)
    }

    interface ResponseCallback {
        fun onResponse(status: Int, message: String)
        fun onError(errorMessage: String)
    }

}