package com.example.emarket.presenter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class SplashPresenter(private val view:SplashContract.View):SplashContract.Presenter {
    override fun accessSplashPreference(context: Context) {
        val sharedPreferences = context.getSharedPreferences("ActivityPrefs", AppCompatActivity.MODE_PRIVATE)
        var splash = sharedPreferences.getBoolean("splash", true)
        if (splash){
            val editor = sharedPreferences.edit()
            editor.putBoolean("splash", false)
            editor.apply()
            view.navigateToLogin(true)
        } else {
            view.navigateToLogin(false)
        }
    }
}