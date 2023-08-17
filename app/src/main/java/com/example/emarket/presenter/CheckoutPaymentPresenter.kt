package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants

class CheckoutPaymentPresenter(private val view: CheckoutPaymentContract.View, private val context: Context) : CheckoutPaymentContract.Presenter {
    override fun savePaymentOption(payment: String) {
        AppUtils.setSharedPrefsString(context, ViewConstants.CHECKOUT_PREFERENCE, ViewConstants.CHECKOUT_PREFERENCE_PAYMENT, payment)
    }
}