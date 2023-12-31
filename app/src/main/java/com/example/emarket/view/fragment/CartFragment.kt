package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentCartBinding
import com.example.emarket.databinding.FragmentCategoryBinding
import com.example.emarket.databinding.ItemProductCartBinding
import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.model.local.entity.Order
import com.example.emarket.model.local.entity.Product
import com.example.emarket.presenter.CartContract
import com.example.emarket.presenter.CartPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.adapter.CartProductAdapter


class CartFragment : Fragment(), CartContract.View, CartProductAdapter.CartProductClickListener {
    private lateinit var binding: FragmentCartBinding
    private lateinit var presenter: CartPresenter
    private lateinit var adapter: CartProductAdapter
    private lateinit var cartDao: CartDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle("Shopping Cart")
        presenter = CartPresenter(this, requireContext())
        cartDao = CartDao(requireContext())
        displayCartProducts()
        navigateToCheckout()
    }

    override fun displayCartProducts() {
        val items = cartDao.getAllItems()
        adapter = CartProductAdapter(items, cartDao, this)
        binding.rvCart.adapter = adapter
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.tvTotalBill.text = presenter.calculateTotalBill(cartDao).toString()
    }

    override fun navigateToCheckout() {
        binding.btnCheckout.setOnClickListener {
            val order = presenter.createOrder(cartDao)
            AppUtils.navigateToFragment(requireActivity() as AppCompatActivity, R.id.main_fragment_container, CheckoutFragment.newInstance(order))
    }}

    override fun onCartProductClick() {
        binding.tvTotalBill.text = presenter.calculateTotalBill(cartDao).toString()
    }
}