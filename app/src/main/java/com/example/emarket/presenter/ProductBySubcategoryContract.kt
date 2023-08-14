package com.example.emarket.presenter

import androidx.fragment.app.Fragment
import com.example.emarket.model.local.entity.Product

interface ProductBySubcategoryContract {
    interface View {
        fun displayProduct(products: List<Product>)
        fun navigateToProduct(productFragment: Fragment)
    }

    interface Presenter{
        fun getProductRemote(subcategoryId: String)
    }

    interface ReponseCallback{
        fun onResponse(status: Int, message: String, products: List<Product>?)
        fun onError(errorMessage: String)
    }
}