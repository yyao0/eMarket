package com.example.emarket.presenter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.emarket.utils.AppUtils

class LoginPresenter(private val view: LoginContract.View, private val context: Context) : LoginContract.Presenter {

    override fun checkLogin(username: String, password: String) {
        if (username != "" && password != "") {
            setLoginPreference(true)
            AppUtils.showToast(context, "Login success")
            view.navigateToProfile()
        } else {
            setLoginPreference(false)
            AppUtils.showToast(context, "Invalid username or password")
        }
    }

    override fun getLoginPreference() : Boolean {
        val sharedPreferences = context.getSharedPreferences("ActivityPrefs", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getBoolean("loggedIn", false)
    }

    override fun setLoginPreference(loggedIn: Boolean) {
        val sharedPreferences = context.getSharedPreferences("ActivityPrefs", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("loggedIn", loggedIn)
        editor.apply()
    }
}