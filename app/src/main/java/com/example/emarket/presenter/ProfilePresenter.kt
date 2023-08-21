package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.model.local.entity.User
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants
import com.google.gson.Gson

class ProfilePresenter(private val view: ProfileContract.View, private val context: Context): ProfileContract.Presenter {
    override fun getUser(): User {
        val userString = AppUtils.getSharedPrefsString(context, ViewConstants.ACTIVITY_PREFERENCE, ViewConstants.ACTIVITY_PREFERENCE_USER)
        val user = Gson().fromJson(userString, User::class.java)
        return user
    }
}