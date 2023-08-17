package com.example.emarket.presenter

import android.content.Context

interface IntroContract {
    interface View {
        fun displayTabs()
        fun navigateToLogin()
    }

    interface Presenter {
        fun getIntroSharedPreference(context: Context)
        fun disableIntroSharedPreference(context: Context)
    }
}