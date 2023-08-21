package com.example.emarket.presenter

import android.content.Context
import android.util.Log
import com.example.emarket.model.local.entity.Addresse
import com.example.emarket.model.local.entity.User
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants
import com.google.gson.Gson

class AddressPresenter(private val view: AddressContract.View, private val context: Context): AddressContract.Presenter {
    override fun getUserId(): String {
        val userString = AppUtils.getSharedPrefsString(context, ViewConstants.ACTIVITY_PREFERENCE, ViewConstants.ACTIVITY_PREFERENCE_USER)
        val user = Gson().fromJson(userString, User::class.java)
        return user.userId
    }

    override fun getAddressesRemote(userId: String) {
        VolleyHandler.getAddressesByUser(context, userId, object:CheckoutDeliveryContract.ReponseCallback{
            override fun onResponse(status: Int, message: String, address: List<Addresse>?) {
                if (address != null) {
                    view.displayAddresses(address)
                }else {
                    AppUtils.showToast(context, message)
                }
            }
            override fun onError(errorMessage: String) {
                Log.i("tag", errorMessage)
                AppUtils.showToast(context, errorMessage)
            }
        })
    }

    override fun addAddress(userId: String, title: String, address: String) {
        VolleyHandler.addAddress(context, userId, title, address, object:CheckoutDeliveryContract.ReponseCallback{
            override fun onResponse(status: Int, message: String, address: List<Addresse>?) {
                AppUtils.showToast(context, message)
            }

            override fun onError(errorMessage: String) {
                AppUtils.showToast(context, errorMessage)
            }
        })
    }
}