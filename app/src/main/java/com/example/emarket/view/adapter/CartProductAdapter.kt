package com.example.emarket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemProductCartBinding
import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.model.local.entity.Item
import com.example.emarket.model.local.entity.Product
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants
import com.squareup.picasso.Picasso

class CartProductAdapter(
    private val items: MutableList<Item>,
    private val cartDao: CartDao,
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
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemProductCartBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item){
            binding.tvName.text = item.product_name
            binding.tvPrice.text = item.unit_price
            Picasso.get().load("${ViewConstants.BASE_IMAGE_URL}${item.product_image_url}").into(binding.ivImage)
            binding.tvDescription.text = item.description
            val quantity = cartDao.getQuantityForProduct(item.product_id)
            binding.tvQuantity.text = quantity.toString()
            val totalPrice = item.unit_price.toInt() * quantity
            binding.tvTotalPrice.text = totalPrice.toString()
            binding.btnPlus.setOnClickListener {
                cartDao.updateQuantityForProduct(item.product_id, 1)
                val quantity = cartDao.getQuantityForProduct(item.product_id)
                val totalPrice = item.unit_price.toInt() * quantity
                binding.tvTotalPrice.text = totalPrice.toString()
                binding.tvQuantity.text = quantity.toString()
                cartProductClickListener.onCartProductClick()
            }
            binding.btnMinus.setOnClickListener {
                var quantity = cartDao.getQuantityForProduct(item.product_id)
                if (quantity > 1) {
                    quantity -= 1
                    val totalPrice = item.unit_price.toInt() * quantity
                    binding.tvTotalPrice.text = totalPrice.toString()
                    binding.tvQuantity.text = quantity.toString()
                    cartDao.updateQuantityForProduct(item.product_id, -1)
                    cartProductClickListener.onCartProductClick()
                } else {
                    cartDao.deleteProduct(item.product_id)
                    cartProductClickListener.onCartProductClick()
                    items.remove(item)
                    notifyItemRemoved(position)
                }

            }
        }
    }
}