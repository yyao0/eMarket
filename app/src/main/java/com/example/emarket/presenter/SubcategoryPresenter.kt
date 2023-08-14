package com.example.emarket.presenter

import android.content.Context
import com.example.emarket.model.local.entity.Subcategory
import com.example.emarket.model.remote.VolleyHandler
import com.example.emarket.utils.AppUtils

class SubcategoryPresenter(private val view: SubcategoryContract.View, private val context: Context): SubcategoryContract.Presenter {
    override fun getSubcategoryRemote(categoryId: String) {
        VolleyHandler.getSubcategory(context, categoryId, object:SubcategoryContract.ReponseCallback{
            override fun onResponse(status: Int, message: String, subcategories: List<Subcategory>?) {
                if (subcategories != null) {
                    view.displaySubcategory(subcategories)
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