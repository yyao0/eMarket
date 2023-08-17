package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.model.local.entity.Product
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils

class ProductBySubcategoryPresenter(private val view: ProductBySubcategoryContract.View, private val context: Context) :  ProductBySubcategoryContract.Presenter{
    override fun getProductRemote(subcategoryId: String) {
        VolleyHandler.getSubcategoryProducts(context, subcategoryId, object:ProductBySubcategoryContract.ReponseCallback{
            override fun onResponse(status: Int, message: String, products: List<Product>?) {
                if (products != null){
                    view.displayProduct(products)
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