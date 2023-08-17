package com.example.emarket.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.emarket.databinding.QuantityWidgetBinding
import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.utils.AppUtils

class CartWidgetView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var productId = "99"
    private val cartDao = CartDao(context)
    private val binding  = QuantityWidgetBinding.inflate(LayoutInflater.from(context), this, true)
    private var quantity = cartDao.getQuantityForProduct(productId)
    private var onQuantityChangeListener: OnQuantityChangeListener? = null


    interface OnQuantityChangeListener {
        fun onQuantityChanged(newQuantity: Int)
    }

    init {
        binding.btnMinus.setOnClickListener { decreaseQuantity() }
        binding.btnPlus.setOnClickListener { increaseQuantity() }
        updateQuantityText()
    }

    fun setOnQuantityChangeListener(listener: OnQuantityChangeListener) {
        onQuantityChangeListener = listener
    }

    fun decreaseQuantity() {
        quantity = cartDao.getQuantityForProduct(productId)
        if (quantity > 0) {
            quantity--
            cartDao.updateQuantityForProduct(productId, -1)
            updateQuantityText()
        }
        onQuantityChangeListener?.onQuantityChanged(cartDao.getQuantityForProduct(productId))
    }

    fun increaseQuantity() {
        quantity = cartDao.getQuantityForProduct(productId) + 1
        cartDao.updateQuantityForProduct(productId, 1)
        updateQuantityText()
        onQuantityChangeListener?.onQuantityChanged(quantity)
    }

    fun updateQuantityText() {
        binding.tvQuantity.text = cartDao.getQuantityForProduct(productId).toString()
    }

    fun setProduct(productId: String){
        this.productId = productId
        quantity = cartDao.getQuantityForProduct(productId)
    }
}
