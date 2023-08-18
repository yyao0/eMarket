package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.model.local.entity.Item
import com.example.emarket.model.local.entity.Order
import com.example.emarket.model.local.entity.User
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class CheckoutSummaryPresenter(private val view: CheckoutSummaryContract.View, private val context: Context) : CheckoutSummaryContract.Presenter {
    override fun finalizeOrder(cartDao: CartDao): Order {
        val items = mutableListOf<Item>()
        val itemsInCart = cartDao.getAllItems()
        for (i in itemsInCart){
            if (i.quantity != ""){
                if (i.quantity.toInt() > 0){
                    items.add(i)
                }
            }
        }
        val addressTitle = AppUtils.getSharedPrefsString(context, ViewConstants.CHECKOUT_PREFERENCE, ViewConstants.CHECKOUT_PREFERENCE_ADDRESS_TITLE, "")
        val address = AppUtils.getSharedPrefsString(context, ViewConstants.CHECKOUT_PREFERENCE, ViewConstants.CHECKOUT_PREFERENCE_ADDRESS, "")
        val payment = AppUtils.getSharedPrefsString(context, ViewConstants.CHECKOUT_PREFERENCE, ViewConstants.CHECKOUT_PREFERENCE_PAYMENT, "")
        val order = Order(
            bill_amount=cartDao.calculateTotalPriceForAllProducts().toString(),
            items = items,
            address_title = addressTitle,
            address = address,
            payment_method = payment
            )
        return order
    }

    override fun createOrderJson(order: Order): JSONObject {
        val orderJson = JSONObject()
        val userString = AppUtils.getSharedPrefsString(context, ViewConstants.ACTIVITY_PREFERENCE, ViewConstants.ACTIVITY_PREFERENCE_USER)
        val user = Gson().fromJson(userString, User::class.java)
        orderJson.put("user_id", user.userId.toInt())
        val deliveryAddress = JSONObject()
        deliveryAddress.put("title", order.address_title)
        deliveryAddress.put("address", order.address)
        orderJson.put("delivery_address", deliveryAddress)
        val itemsArray = JSONArray()
        for (i in order.items){
            val item = JSONObject()
            item.put("product_id", i.product_id.toInt())
            item.put("quantity", i.quantity.toInt())
            item.put("unit_price", i.unit_price.toInt())
            itemsArray.put(item)
        }
        orderJson.put("items", itemsArray)
        orderJson.put("bill_amount", order.bill_amount.toInt())
        var payment = order.payment_method
        if (payment == "Cash On Delivery") {
            payment = "COD"
        }
        orderJson.put("payment_method", payment)
        return orderJson
    }

    override fun placeOrder(orderJson: JSONObject) {
        VolleyHandler.placeOrder(context, orderJson, object: CheckoutSummaryContract.ReponseCallback{
            override fun onResponse(status: Int, message: String, orderId: Int?) {
                AppUtils.showToast(context, message)
                if (orderId != null) {
                    view.handleOrderResult(orderId)
                }
            }
            override fun onError(errorMessage: String) {
                AppUtils.showToast(context, errorMessage)
            }
        })
    }
}