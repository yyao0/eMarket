package com.example.emarket.presenter

import android.content.Context

interface MainContract {
    interface View {

        fun initNavigationDrawer()
        fun navigateToLogin()

    }

    interface Presenter{
        fun activateSplashOnDestroy()

        fun logout()



    }

}