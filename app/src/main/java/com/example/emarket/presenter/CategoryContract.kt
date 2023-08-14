package com.example.emarket.presenter

import androidx.fragment.app.Fragment
import com.example.emarket.model.local.entity.Category

interface CategoryContract {

    interface View {
        fun displayCategory(categories: List<Category>)
        fun navigateToSubcategory(subcategoryFragment: Fragment)
    }

    interface Presenter{
        fun getCategoryRemote()
    }

    interface ReponseCallback{
        fun onResponse(status: Int, message: String, categories: List<Category>?)
        fun onError(errorMessage: String)
    }
}