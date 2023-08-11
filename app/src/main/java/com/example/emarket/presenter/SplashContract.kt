package com.example.emarket.presenter

import android.content.Context

interface SplashContract {
    interface View{
        fun navigateToLogin(splash: Boolean)
    }

    interface Presenter{
        fun accessSplashPreference(context: Context)
    }
}