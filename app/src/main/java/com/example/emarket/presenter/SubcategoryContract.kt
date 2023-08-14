package com.example.emarket.presenter

import androidx.fragment.app.Fragment
import com.example.emarket.model.local.entity.Subcategory

interface SubcategoryContract {
    interface View {
        fun displaySubcategoryProduct(subcategories: List<Subcategory>)
        fun navigateToProduct(productFragment: Fragment)
    }

    interface Presenter{
        fun getSubcategoryRemote(categoryId: String)
    }

    interface ReponseCallback{
        fun onResponse(status: Int, message: String, subcategories: List<Subcategory>?)
        fun onError(errorMessage: String)
    }
}