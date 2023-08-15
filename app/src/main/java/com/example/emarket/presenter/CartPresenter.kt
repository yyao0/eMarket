package com.example.emarket.presenter

import android.content.Context
import android.util.Log
import com.example.emarket.model.local.entity.Product
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants

class CartPresenter(private val view: CartContract.View, private val context: Context) : CartContract.Presenter {



    override fun getProductDetailsRemote(callback: (MutableList<Product>) -> Unit) {
        val sharedPreferences = context.getSharedPreferences(ViewConstants.CART_PREFERENCE, Context.MODE_PRIVATE)
        val allEntries: Map<String, *> = sharedPreferences.all
        val productIds: Set<String> = allEntries.keys
        val productList = mutableListOf<Product>()
        val remainingRequests = productIds.size
        for (productId in productIds) {
            VolleyHandler.getProductDetails(
                context,
                productId,
                object : ProductDetailsContract.ReponseCallback {
                    override fun onResponse(status: Int, message: String, product: Product?) {
                        if (product != null) {
                            productList.add(product)
                        } else {
                            AppUtils.showToast(context, message)
                        }
                        if (productList.size == remainingRequests) {
                            callback(productList)
                        }
                    }

                    override fun onError(errorMessage: String) {
                        AppUtils.showToast(context, errorMessage)

                        if (productList.size == remainingRequests) {
                            callback(productList)
                        }
                    }
                })
        }
    }

    override fun calculateTotalBill(products: MutableList<Product>): Int {
        var bill = 0
        val sharedPreferences = context.getSharedPreferences(ViewConstants.CART_PREFERENCE, Context.MODE_PRIVATE)
        val allEntries: Map<String, *> = sharedPreferences.all
        for ((key, value) in allEntries) {
            if (!products.none { it.product_id == key } && value.toString().toInt() > 0){
                val product: Product = products.filter { it.product_id == key }[0]
                bill += product.price.toInt() * value.toString().toInt()
            }
        }
        return bill
    }
}