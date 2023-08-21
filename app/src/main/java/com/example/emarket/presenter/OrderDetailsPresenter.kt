package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.model.local.entity.Order
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils

class OrderDetailsPresenter(private val view: OrderDetailsContract.View, private val context: Context): OrderDetailsContract.Presenter {
    override fun getOrderDetailsRemote(orderId: String) {
        VolleyHandler.getOrderDetails(context, orderId, object: OrderDetailsContract.ResponseCallback{
            override fun onResponse(status: Int, message: String, order: Order?) {
                if (order != null) {
                    view.displayOrderDetails(order)
                }
                else {
                    AppUtils.showToast(context, message)
                }
            }
            override fun onError(errorMessage: String) {
                AppUtils.showToast(context, errorMessage)
            }
        })
    }
}