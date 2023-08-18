package com.example.emarket.presenter

import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.model.local.entity.Addresse
import com.example.emarket.model.local.entity.Order
import org.json.JSONObject

interface CheckoutSummaryContract {
    interface View{
        fun displayOrder(order: Order)
        fun userPlaceOrder()
        fun handleOrderResult(orderId: Int)
    }

    interface Presenter {
        fun finalizeOrder(cartDao: CartDao) : Order
        fun createOrderJson(order: Order): JSONObject
        fun placeOrder(orderJson: JSONObject)
    }

    interface ReponseCallback{
        fun onResponse(status: Int, message: String, orderId: Int?)
        fun onError(errorMessage: String)
    }
}