package com.example.emarket.presenter

import android.content.Context

interface SplashContract {
    interface View{
        fun navigateToIntro()

        fun navigateToIntroAfterDelay()
    }

    interface Presenter{
        fun accessSplashPreference(context: Context)

    }
}