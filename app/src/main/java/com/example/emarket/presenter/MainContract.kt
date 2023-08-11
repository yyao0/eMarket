package com.example.emarket.presenter

import android.content.Context

interface MainContract {
    interface View {

    }

    interface Presenter{
        fun activateSplashOnDestroy(context: Context)

    }

}