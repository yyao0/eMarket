package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.emarket.R
import com.example.emarket.databinding.FragmentCheckoutCartBinding
import com.example.emarket.model.local.entity.Order
import com.example.emarket.presenter.CheckoutCartContract
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.adapter.CheckoutItemAdapter

class CheckoutCartFragment : Fragment(), CheckoutCartContract.View {
    private lateinit var binding: FragmentCheckoutCartBinding
    private lateinit var adapter: CheckoutItemAdapter
    private lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        order = arguments?.getParcelable<Order>(ARG_ORDER)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutCartBinding.inflate(inflater, container, false)
        displayOrder()
        navigateToDelivery()
        return binding.root
    }

    override fun displayOrder() {
        with(binding) {
            tvTotalBill.text = order.bill_amount
            adapter = CheckoutItemAdapter(order.items)
            rvCheckoutCart.adapter = adapter
            rvCheckoutCart.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun navigateToDelivery() {
        binding.btnNext.setOnClickListener {
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager_checkout)
            viewPager.currentItem = 1
        }
    }

    companion object {
        private const val ARG_ORDER = "order"
        fun newInstance(order: Order): CheckoutCartFragment {
            val fragment = CheckoutCartFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_ORDER, order)
            fragment.arguments = bundle
            return fragment
        }
    }
}