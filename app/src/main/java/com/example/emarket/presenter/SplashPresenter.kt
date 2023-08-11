package com.example.emarket.presenter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.emarket.utils.AppUtils

class SplashPresenter(private val view:SplashContract.View):SplashContract.Presenter {
    override fun accessSplashPreference(context: Context) {
        val splash = AppUtils.getSharedPrefsBoolean(context, "ActivityPrefs", "splash", true)
        if (splash){
            AppUtils.setSharedPrefsBoolean(context, "ActivityPrefs", "splash", false)
            view.navigateToLogin(true)
        } else {
            view.navigateToLogin(false)
        }
    }
}