package com.example.emarket.presenter

import android.content.Context

interface MainContract {
    interface View {

        fun navigateToLogin()

    }

    interface Presenter{
        fun activateSplashOnDestroy()

        fun logout()



    }

}