package com.example.emarket.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.emarket.databinding.FragmentCheckoutBinding
import com.example.emarket.model.local.entity.Order
import com.example.emarket.presenter.CheckoutContract
import com.example.emarket.view.adapter.CheckoutPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class CheckoutFragment : Fragment(), CheckoutContract.View {
    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var adapter: CheckoutPagerAdapter
    private lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        order = arguments?.getParcelable<Order>(ARG_ORDER)!!
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutBinding.inflate(layoutInflater, container, false)
        manageViewPager()
        return binding.root
    }

    override fun manageViewPager() {
        binding.viewPagerCheckout.offscreenPageLimit = 4
        val fragmentList = arrayListOf(
            CheckoutCartFragment.newInstance(order),
            CheckoutDeliveryFragment(),
            CheckoutPaymentFragment(),
            CheckoutSummaryFragment())
        adapter = CheckoutPagerAdapter(childFragmentManager, lifecycle, fragmentList)
        binding.viewPagerCheckout.adapter = adapter
        TabLayoutMediator(binding.tabLayoutCheckout, binding.viewPagerCheckout) { tab, position ->
            tab.text = getTabTitles(position)
        }.attach()
    }

    override fun getTabTitles(position: Int): String {
        return when (position) {
            0 -> "Cart Items"
            1 -> "Delivery"
            2 -> "Payment"
            3 -> "Summary"
            else -> ""
        }
    }

    companion object {
        private const val ARG_ORDER = "order"
        fun newInstance(order: Order): CheckoutFragment {
            val fragment = CheckoutFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_ORDER, order)
            fragment.arguments = bundle
            return fragment
        }
    }
}