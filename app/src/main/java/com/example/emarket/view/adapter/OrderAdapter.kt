package com.example.emarket.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemOrderBinding
import com.example.emarket.model.local.entity.Order

class OrderAdapter(
    private val orders: List<Order>,
    private val orderClickListener: OrderClickListener
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>(){

    interface OrderClickListener {
        fun onOrderClick(order: Order)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    override fun getItemCount() = orders.size

    inner class ViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(order: Order){
            with(binding){
                tvOrderId.text = "Order ID: #${order.order_id}"
                tvBill.text = "$ ${order.bill_amount}"
                tvDate.text = order.order_date
                tvStatus.text = order.order_status
                btnDetails.setOnClickListener {
                    orderClickListener.onOrderClick(order)
                }
            }
        }
    }
}