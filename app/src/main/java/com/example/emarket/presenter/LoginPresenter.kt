package com.example.emarket.presenter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.emarket.model.local.entity.User
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils

class LoginPresenter(private val view: LoginContract.View, private val context: Context) : LoginContract.Presenter {
    override fun checkLoginRemote(
        email: String,
        password: String
    ) {
        VolleyHandler.userLogin(context, email, password, object:LoginContract.ResponseCallback{
            override fun onResponse(status: Int, message: String, user: User?) {
                if (status == 0) {
                    setLoginPreference(true)
                    AppUtils.showToast(context, message)
                    view.navigateToProfile()
                } else {
                    setLoginPreference(false)
                    AppUtils.showToast(context, message)
                }
            }

            override fun onError(errorMessage: String) {
                setLoginPreference(false)
                AppUtils.showToast(context, errorMessage)
            }
        })
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