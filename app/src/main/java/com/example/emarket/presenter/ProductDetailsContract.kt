package com.example.emarket.presenter

import androidx.fragment.app.Fragment
import com.example.emarket.model.local.entity.Product

interface ProductDetailsContract {
    interface View {
        fun displayProductDetails(product: Product)
        fun addProductToCart()
    }

    interface Presenter{
        fun getProductDetailsRemote(productId: String)
        fun updateProductQuantity(productId: String)
        fun getProductQuantity(productId: String):Int
    }

    interface ReponseCallback{
        fun onResponse(status: Int, message: String, product: Product?)
        fun onError(errorMessage: String)
    }
}