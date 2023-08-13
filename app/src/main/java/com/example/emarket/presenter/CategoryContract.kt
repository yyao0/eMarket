package com.example.emarket.presenter

import com.example.emarket.model.local.entity.Category

interface CategoryContract {

    interface View {
        fun displayCategory(categories: List<Category>)
    }

    interface Presenter{
        fun getCategoryRemote()
    }

    interface ReponseCallback{
        fun onResponse(status: Int, message: String, categories: List<Category>?)
        fun onError(errorMessage: String)
    }
}