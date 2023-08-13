package com.example.emarket.presenter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.emarket.model.local.entity.User
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants
import com.google.gson.Gson

class LoginPresenter(private val view: LoginContract.View, private val context: Context) : LoginContract.Presenter {
    override fun checkLoginRemote(
        email: String,
        password: String
    ) {
        VolleyHandler.userLogin(context, email, password, object:LoginContract.ResponseCallback{
            override fun onResponse(status: Int, message: String, user: User?) {
                if (status == 0) {
                    setLoginPreference(true)
                    if (user != null) {
                        setUserPreference(user)
                    }
                    AppUtils.showToast(context, message)
                    view.navigateToProfile()
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

    override fun getLoginPreference() : Boolean {
        return AppUtils.getSharedPrefsBoolean(context, ViewConstants.ACTIVITY_PREFERENCE, ViewConstants.ACTIVITY_PREFERENCE_LOGIN, false)
    }

    override fun setLoginPreference(loggedIn: Boolean) {
        AppUtils.setSharedPrefsBoolean(context, ViewConstants.ACTIVITY_PREFERENCE, ViewConstants.ACTIVITY_PREFERENCE_LOGIN, loggedIn)
    }

    override fun setUserPreference(user: User) {
        val userString = Gson().toJson(user)
        AppUtils.setSharedPrefsString(context, ViewConstants.ACTIVITY_PREFERENCE, ViewConstants.ACTIVITY_PREFERENCE_USER, userString)
    }
}