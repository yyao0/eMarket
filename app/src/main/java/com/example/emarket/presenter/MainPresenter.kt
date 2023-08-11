package com.example.emarket.presenter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.activity.LoginActivity
import com.example.emarket.view.activity.SignupActivity

class MainPresenter(private val view: MainContract.View, private val context: Context) : MainContract.Presenter {
    override fun activateSplashOnDestroy() {
        AppUtils.setSharedPrefsBoolean(context, "ActivityPrefs", "splash", true)
    }

    override fun logout() {
        AppUtils.setSharedPrefsBoolean(context, "ActivityPrefs", "loggedIn", false)
        AppUtils.showToast(context, "You've logged out.")
    }
}