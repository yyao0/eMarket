package com.example.emarket.presenter

import androidx.fragment.app.Fragment
import com.example.emarket.model.local.entity.Product

interface CartContract {
    interface View {
        fun displayCartProducts(products: MutableList<Product>)
        fun navigateToProduct(productFragment: Fragment)
    }

    interface Presenter{
        fun getProductDetailsRemote(callback: (MutableList<Product>) -> Unit)
        fun calculateTotalBill(products: MutableList<Product>):Int
    }

}