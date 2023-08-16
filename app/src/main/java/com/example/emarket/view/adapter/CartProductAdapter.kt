package com.example.emarket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemProductCartBinding
import com.example.emarket.model.local.entity.Product
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants
import com.squareup.picasso.Picasso

class CartProductAdapter(
    private val context: Context,
    private val products: MutableList<Product>,
    private val cartProductClickListener: CartProductAdapter.CartProductClickListener
) : RecyclerView.Adapter<CartProductAdapter.ViewHolder>(){

    interface CartProductClickListener {
        fun onCartProductClick()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartProductAdapter.ViewHolder {
        val binding = ItemProductCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartProductAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount() = products.size

    inner class ViewHolder(private val binding: ItemProductCartBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.tvName.text = product.product_name
            binding.tvPrice.text = product.price
            Picasso.get().load("${ViewConstants.BASE_IMAGE_URL}${product.product_image_url}").into(binding.ivImage)
            binding.tvDescription.text = product.description
            val quantity = AppUtils.getSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, product.product_id)
            binding.tvQuantity.text = quantity.toString()
            val totalPrice = product.price.toInt() * quantity
            binding.tvTotalPrice.text = totalPrice.toString()
            binding.btnPlus.setOnClickListener {
                val quantity = AppUtils.getSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, product.product_id) + 1
                val totalPrice = product.price.toInt() * quantity
                binding.tvTotalPrice.text = totalPrice.toString()
                binding.tvQuantity.text = quantity.toString()
                AppUtils.setSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, product.product_id, quantity)
                cartProductClickListener.onCartProductClick()
            }
            binding.btnMinus.setOnClickListener {
                var quantity = AppUtils.getSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, product.product_id)
                if (quantity > 1) {
                    quantity -= 1
                    val totalPrice = product.price.toInt() * quantity
                    binding.tvTotalPrice.text = totalPrice.toString()
                    binding.tvQuantity.text = quantity.toString()
                    AppUtils.setSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, product.product_id, quantity)
                    cartProductClickListener.onCartProductClick()
                } else {
                    AppUtils.setSharedPrefsInt(context, ViewConstants.CART_PREFERENCE, product.product_id, quantity-1)
                    cartProductClickListener.onCartProductClick()
                    products.remove(product)
                    notifyItemRemoved(position)
                }

            }


        }
    }
}