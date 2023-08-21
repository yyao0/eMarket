package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.model.local.entity.Order
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils

class OrderPresenter(private val view: OrdersContract.View, private val context: Context): OrdersContract.Presenter {
    override fun getOrders(userId: String) {
        VolleyHandler.getOrdersByUser(context, userId, object: OrdersContract.ResponseCallback{
            override fun onResponse(status: Int, message: String, orders: List<Order>?) {
                if (orders != null){
                    view.displayOrders(orders)
                } else {
                    AppUtils.showToast(context, message)
                }
            }
            override fun onError(errorMessage: String) {
                AppUtils.showToast(context, errorMessage)
            }
        })
    }
}