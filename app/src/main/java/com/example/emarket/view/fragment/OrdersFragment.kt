package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentOrdersBinding
import com.example.emarket.model.local.entity.Order
import com.example.emarket.model.local.entity.User
import com.example.emarket.presenter.OrderPresenter
import com.example.emarket.presenter.OrdersContract
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.adapter.OrderAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class OrdersFragment : Fragment(), OrdersContract.View, OrderAdapter.OrderClickListener {
    private lateinit var binding: FragmentOrdersBinding
    private lateinit var presenter: OrderPresenter
    private lateinit var adapter: OrderAdapter
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle("Order History")
        presenter = OrderPresenter(this, requireContext())
        userId = AppUtils.getUserId(requireContext())
        presenter.getOrders(userId)
    }

    override fun displayOrders(orders: List<Order>) {
        val sortedOrders = orders.sortedByDescending { it.order_date }
        adapter = OrderAdapter(sortedOrders, this)
        binding.rvOrders.adapter = adapter
        binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun navigateToOrderDetails(orderId: String) {
        AppUtils.navigateToFragment(requireActivity() as AppCompatActivity, R.id.main_fragment_container, OrderDetailsFragment.newInstance(orderId))
    }

    override fun onOrderClick(order: Order) {
        navigateToOrderDetails(order.order_id)
    }
}