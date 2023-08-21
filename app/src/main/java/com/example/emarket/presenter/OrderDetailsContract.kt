package com.example.emarket.presenter

import com.example.emarket.model.local.entity.Order

interface OrderDetailsContract {
    interface View{
        fun displayOrderDetails(order: Order)
    }
    interface Presenter{
        fun getOrderDetailsRemote(orderId: String)
    }
    interface ResponseCallback{
        fun onResponse(status: Int, message: String, order: Order?)
        fun onError(errorMessage: String)
    }
}