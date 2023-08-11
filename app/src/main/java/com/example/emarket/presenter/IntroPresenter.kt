package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants

class IntroPresenter(private val view:IntroContract.View, private val context: Context) : IntroContract.Presenter {
    override fun getIntroSharedPreference(context: Context) {
        if (AppUtils.getSharedPrefsBoolean(context, ViewConstants.ACTIVITY_PREFERENCE, ViewConstants.ACTIVITY_PREFERENCE_INTRO, true)) {
            disableIntroSharedPreference(context)
        } else {
            view.navigateToLogin()
        }
    }

    override fun disableIntroSharedPreference(context: Context) {
        AppUtils.setSharedPrefsBoolean(context, ViewConstants.ACTIVITY_PREFERENCE, ViewConstants.ACTIVITY_PREFERENCE_INTRO, false)
    }
}