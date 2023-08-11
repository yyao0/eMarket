package com.example.emarket.presenter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun activateSplashOnDestroy(context: Context) {
        val sharedPreferences = context.getSharedPreferences("ActivityPrefs", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("splash", true)
        editor.apply()
    }
}