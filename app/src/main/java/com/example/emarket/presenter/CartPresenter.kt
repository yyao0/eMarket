package com.example.emarket.presenter

import android.content.Context
import android.util.Log
import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.model.local.entity.Item
import com.example.emarket.model.local.entity.Order
import com.example.emarket.model.local.entity.Product
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants

class CartPresenter(private val view: CartContract.View, private val context: Context) : CartContract.Presenter {

    override fun calculateTotalBill(cartDao: CartDao): Int {
        return cartDao.calculateTotalPriceForAllProducts()
    }

    override fun createOrder(cartDao: CartDao): Order {
        val items = mutableListOf<Item>()
        val itemsInCart = cartDao.getAllItems()
        for (i in itemsInCart){
            if (i.quantity != ""){
                if (i.quantity.toInt() > 0){
                    items.add(i)
                }
            }
        }
        val order = Order(bill_amount=cartDao.calculateTotalPriceForAllProducts().toString(), items=items)
        return order
    }
}
