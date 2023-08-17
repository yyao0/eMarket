package com.example.emarket.presenter

import androidx.fragment.app.Fragment
import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.model.local.entity.Item
import com.example.emarket.model.local.entity.Order
import com.example.emarket.model.local.entity.Product

interface CartContract {
    interface View {
        fun displayCartProducts()
        fun navigateToCheckout()
    }

    interface Presenter{
        fun calculateTotalBill(cartDao: CartDao):Int
        fun createOrder(cartDao: CartDao) : Order
    }
}