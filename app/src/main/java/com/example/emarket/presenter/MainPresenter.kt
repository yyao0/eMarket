package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.utils.AppUtils

class MainPresenter(private val view: MainContract.View, private val context: Context) : MainContract.Presenter {
    override fun activateSplashOnDestroy() {
        AppUtils.setSharedPrefsBoolean(context, "ActivityPrefs", "splash", true)
    }

    override fun logout() {
        AppUtils.setSharedPrefsBoolean(context, "ActivityPrefs", "loggedIn", false)
        AppUtils.showToast(context, "You've logged out.")
    }
}