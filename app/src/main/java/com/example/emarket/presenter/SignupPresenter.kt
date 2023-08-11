package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.utils.AppUtils

class SignupPresenter(private val view: SignupContract.View, private val context: Context) : SignupContract.Presenter {
    override fun checkSignup(username: String, password: String, password2: String) {
        if (username != "" && password != "" && password2 != "" && password == password2) {
            AppUtils.showToast(context, "Signup success")
            setLoginPreference(true)
            view.navigateToProfile()
        }
    }

    override fun setLoginPreference(loggedIn: Boolean) {
        AppUtils.setSharedPrefsBoolean(context, "ActivityPrefs", "loggedIn", loggedIn)
    }
}