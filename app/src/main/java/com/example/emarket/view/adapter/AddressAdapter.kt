package com.example.emarket.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemAddressBinding
import com.example.emarket.model.local.entity.Addresse

class AddressAdapter(private var addresses: MutableList<Addresse>, private val onAddressSelected: (Addresse) -> Unit) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapter.ViewHolder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressAdapter.ViewHolder, position: Int) {
        val address = addresses[position]
        holder.bind(address)
    }

    override fun getItemCount() = addresses.size

    fun insertAddress(address: Addresse){
        addresses.add(address)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAddressBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(address: Addresse){
            binding.tvAddressTitle.text = address.title
            binding.tvAddress.text = address.address
            binding.radioAddress.isChecked = selectedPosition == adapterPosition
            binding.radioAddress.setOnClickListener {
                selectedPosition = adapterPosition
                notifyDataSetChanged()
                onAddressSelected(address)
            }
            binding.root.setOnClickListener {
                selectedPosition = adapterPosition
                notifyDataSetChanged()
                onAddressSelected(address)
            }
        }
    }
}