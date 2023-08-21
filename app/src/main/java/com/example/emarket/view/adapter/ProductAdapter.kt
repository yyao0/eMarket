package com.example.emarket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemProductBinding
import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.model.local.entity.Product
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.CartWidgetView
import com.example.emarket.view.ViewConstants
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val products: List<Product>,
    private val cartDao: CartDao,
    private val productClickListener: ProductClickListener
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

    interface ProductClickListener {
        fun onProductClick(product: Product)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount() = products.size

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root), CartWidgetView.OnQuantityChangeListener{
        private val cartWidgetView: CartWidgetView = binding.cartCounter
        init {
            cartWidgetView.setOnQuantityChangeListener(this)
        }
        fun bind(product: Product){
            binding.tvName.text = product.product_name
            binding.tvPrice.text = "$ ${product.price}"
            binding.tvDescription.text = product.description
            binding.ratingBar.rating = product.average_rating.toFloat()
            Picasso.get().load("${ViewConstants.BASE_IMAGE_URL}${product.product_image_url}").into(binding.ivImage)
            val quantity = cartDao.getQuantityForProduct(product.product_id)
            binding.cartCounter.setProduct(product.product_id)
            binding.cartCounter.updateQuantityText()
            if (quantity == 0) {
                binding.tvAddCart.visibility = View.VISIBLE
                binding.cartCounter.visibility = View.GONE
            } else {
                binding.tvAddCart.visibility = View.GONE
                binding.cartCounter.visibility = View.VISIBLE
            }
            binding.tvAddCart.setOnClickListener {
                    cartDao.insertOrUpdateCartItem(product, 1)
                    binding.tvAddCart.visibility = View.GONE
                    binding.cartCounter.updateQuantityText()
                    binding.cartCounter.visibility = View.VISIBLE
                }

            binding.root.setOnClickListener {
                productClickListener.onProductClick(product)
            }
        }

        override fun onQuantityChanged(newQuantity: Int) {
            if (newQuantity == 0) {
                binding.tvAddCart.visibility = View.VISIBLE
                binding.cartCounter.visibility = View.GONE
            } else {
                binding.tvAddCart.visibility = View.GONE
                binding.cartCounter.visibility = View.VISIBLE
            }
    }
}
}