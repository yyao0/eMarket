package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.model.local.entity.User
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants
import com.google.gson.Gson

class MainPresenter(private val view: MainContract.View, private val context: Context) : MainContract.Presenter {
    override fun activateSplashOnDestroy() {
        AppUtils.setSharedPrefsBoolean(context, "ActivityPrefs", "splash", true)
    }

    override fun getUserPreference(): User {
        val userString = AppUtils.getSharedPrefsString(context, ViewConstants.ACTIVITY_PREFERENCE, ViewConstants.ACTIVITY_PREFERENCE_USER)
        return Gson().fromJson(userString, User::class.java)
    }

    override fun logoutRemote(emailId: String) {
        VolleyHandler.userLogout(context, emailId, object:MainContract.LogoutCallback{
            override fun onResponse(status: Int, message: String) {
                if (status == 0) {
                    AppUtils.setSharedPrefsBoolean(context, ViewConstants.ACTIVITY_PREFERENCE, ViewConstants.ACTIVITY_PREFERENCE_LOGIN, false)
                    AppUtils.showToast(context, message)
                    view.navigateToLogin()
                } else {
                    AppUtils.showToast(context, message)
                }
            }

            override fun onError(errorMessage: String) {
                AppUtils.showToast(context, errorMessage)
            }
        })
    }


}