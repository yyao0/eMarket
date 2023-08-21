package com.example.emarket.presenter

import android.content.Context
import android.util.Log
import com.example.emarket.model.local.entity.Category
import com.example.emarket.model.local.entity.Product
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils

class CategoryPresenter(private val view: CategoryContract.View, private val context: Context) : CategoryContract.Presenter {
    override fun getCategoryRemote() {
        VolleyHandler.getCategory(context, object:CategoryContract.CategoryCallback{
            override fun onResponse(status: Int, message: String, categories: List<Category>?) {
                if (categories != null) {
                    view.displayCategory(categories)
                } else {
                    AppUtils.showToast(context, message)
                }
            }
            override fun onError(errorMessage: String) {
                AppUtils.showToast(context, errorMessage)
            }
        }
        )
    }

    override fun getSearchProductRemote(keyword: String) {
        VolleyHandler.getSearchResult(context, keyword, object: CategoryContract.ProductCallback{
            override fun onResponse(status: Int, message: String, products: List<Product>?) {
                if (products != null){
                    view.displaySearchProduct(products)
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