package com.example.emarket.presenter

import androidx.fragment.app.Fragment
import com.example.emarket.model.local.entity.Category
import com.example.emarket.model.local.entity.Product

interface CategoryContract {
    interface View {
        fun displayCategory(categories: List<Category>)
        fun navigateToSubcategory(subcategoryFragment: Fragment)
        fun displaySearchProduct(products: List<Product>)
        fun navigateToProductDetails(productFragment: Fragment)
        fun onProductClick(product: Product)
    }

    interface Presenter{
        fun getCategoryRemote()
        fun getSearchProductRemote(keyword: String)
    }

    interface CategoryCallback{
        fun onResponse(status: Int, message: String, categories: List<Category>?)
        fun onError(errorMessage: String)
    }

    interface ProductCallback{
        fun onResponse(status: Int, message: String, products: List<Product>?)
        fun onError(errorMessage: String)
    }
}