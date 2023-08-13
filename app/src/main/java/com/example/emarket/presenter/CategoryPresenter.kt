package com.example.emarket.presenter

import android.content.Context
import android.util.Log
import com.example.emarket.model.local.entity.Category
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils

class CategoryPresenter(private val view: CategoryContract.View, private val context: Context) : CategoryContract.Presenter {
    override fun getCategoryRemote() {
        VolleyHandler.getCategory(context, object:CategoryContract.ReponseCallback{
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
}