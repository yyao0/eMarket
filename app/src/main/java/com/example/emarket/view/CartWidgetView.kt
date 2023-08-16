package com.example.emarket.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.emarket.databinding.QuantityWidgetBinding
import com.example.emarket.utils.AppUtils

class CartWidgetView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var productId = "99"
    private val binding  = QuantityWidgetBinding.inflate(LayoutInflater.from(context), this, true)
    private var quantity = AppUtils.getSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, productId)

    init {
        binding.btnMinus.setOnClickListener { decreaseQuantity() }
        binding.btnPlus.setOnClickListener { increaseQuantity() }
        updateQuantityText()
    }

    fun decreaseQuantity() {
        if (quantity > 0) {
            quantity--
            AppUtils.setSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, productId, quantity)
            updateQuantityText()
        }
    }

    fun increaseQuantity() {
        quantity++
        AppUtils.setSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, productId, quantity)
        updateQuantityText()
    }

    fun updateQuantityText() {
        binding.tvQuantity.text = AppUtils.getSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, productId).toString()
    }

    fun setProduct(productId: String){
        this.productId = productId
        quantity = AppUtils.getSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, productId)
    }
}
