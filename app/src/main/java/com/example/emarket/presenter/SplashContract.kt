package com.example.emarket.presenter

import android.content.Context

interface SplashContract {
    interface View{
        fun navigateToLogin()
    }

    interface Presenter{
        fun accessSplashPreference(context: Context)
    }
}