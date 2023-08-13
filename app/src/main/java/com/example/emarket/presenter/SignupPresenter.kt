package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils

class SignupPresenter(private val view: SignupContract.View, private val context: Context) : SignupContract.Presenter {

    override fun checkSignupRemote(
        fullName: String,
        mobileNo: String,
        emailId: String,
        password: String
    ) {
        VolleyHandler.userRegister(context, fullName, mobileNo, emailId, password, object:SignupContract.ResponseCallback{
            override fun onResponse(status: Int, message: String) {
                if (status == 0) {
                    setLoginPreference(false)
                    AppUtils.showToast(context, message)
                    view.navigateToLogin()
                } else {
                    setLoginPreference(false)
                    AppUtils.showToast(context, message)
                }
            }

            override fun onError(errorMessage: String) {
                setLoginPreference(false)
                AppUtils.showToast(context, errorMessage)
            }
        })
    }

    override fun setLoginPreference(loggedIn: Boolean) {
        AppUtils.setSharedPrefsBoolean(context, "ActivityPrefs", "loggedIn", loggedIn)
    }
}