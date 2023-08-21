package com.example.emarket.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentCheckoutCartBinding
import com.example.emarket.databinding.FragmentCheckoutSummaryBinding
import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.model.local.entity.Order
import com.example.emarket.presenter.CheckoutSummaryContract
import com.example.emarket.presenter.CheckoutSummaryPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.adapter.CheckoutItemAdapter


class CheckoutSummaryFragment : Fragment(), CheckoutSummaryContract.View {
    private lateinit var binding: FragmentCheckoutSummaryBinding
    private lateinit var adapter: CheckoutItemAdapter
    private lateinit var presenter: CheckoutSummaryPresenter
    private lateinit var cartDao: CartDao
    private lateinit var order: Order

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartDao = CartDao(requireContext())
        presenter = CheckoutSummaryPresenter(this, requireContext())
        order = presenter.finalizeOrder(cartDao)
        displayOrder(order)
        userPlaceOrder()
    }

    override fun displayOrder(order: Order) {
        val items = order.items
        adapter = CheckoutItemAdapter(items)
        binding.rvCheckoutCart.adapter = adapter
        binding.rvCheckoutCart.layoutManager = LinearLayoutManager(requireContext())
        binding.tvTotalBill.text = order.bill_amount
        binding.tvAddress.text = order.address
        binding.tvAddressTitle.text = order.address_title
        binding.tvPayment.text = order.payment_method
    }

    override fun userPlaceOrder() {
        binding.btnNext.setOnClickListener {
            val orderJson = presenter.createOrderJson(order)
            presenter.placeOrder(orderJson)
            cartDao.clearAllEntries()
        }
    }

    override fun handleOrderResult(orderId: Int) {
        Log.i("tag", orderId.toString())
        AppUtils.navigateToFragment(requireActivity() as AppCompatActivity, R.id.main_fragment_container, OrderDetailsFragment.newInstance(orderId.toString()))
    }
}