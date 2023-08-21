package com.example.emarket.presenter

import com.example.emarket.model.local.entity.Order

interface OrdersContract {
    interface View{
        fun displayOrders(orders: List<Order>)
        fun navigateToOrderDetails(orderId: String)
    }
    interface Presenter{
        fun getOrders(userId: String)
    }
    interface ResponseCallback{
        fun onResponse(status: Int, message: String, orders: List<Order>?)
        fun onError(errorMessage: String)
    }
}