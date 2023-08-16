package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.model.local.entity.Product
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants

class ProductDetailsPresenter(private val view: ProductDetailsContract.View, private val context: Context) : ProductDetailsContract.Presenter {
    override fun getProductDetailsRemote(productId: String) {
        VolleyHandler.getProductDetails(context, productId, object:ProductDetailsContract.ReponseCallback{
            override fun onResponse(status: Int, message: String, product: Product?) {
                if (product != null){
                    view.displayProductDetails(product)
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

     override fun updateProductQuantity(productId: String) {
     }

     override fun getProductQuantity(productId: String): Int {
         return AppUtils.getSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, productId, 0)
     }
}