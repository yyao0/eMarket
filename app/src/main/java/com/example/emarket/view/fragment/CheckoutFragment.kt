package com.example.emarket.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
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
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle("Checkout")
        setupStepView()
        manageViewPager()
        return binding.root
    }

    override fun manageViewPager() {
        binding.viewPagerCheckout.offscreenPageLimit = 6
        val fragmentList = arrayListOf(
            CheckoutCartFragment.newInstance(order),
            CheckoutDeliveryFragment(),
            CheckoutPaymentFragment(),
            CheckoutSummaryFragment())
        adapter = CheckoutPagerAdapter(childFragmentManager, lifecycle, fragmentList)
        binding.viewPagerCheckout.adapter = adapter
        binding.viewPagerCheckout.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.stepviewCheckout.go(position, true)
            }
        })
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

    private fun setupStepView(){
        binding.stepviewCheckout.state
            .steps(listOf("Cart", "Delivery", "Payment", "Summary"))
            .stepsNumber(4)
            .animationDuration(resources.getInteger(android.R.integer.config_shortAnimTime))
            .commit()

        binding.stepviewCheckout.setOnStepClickListener { position ->
            binding.viewPagerCheckout.setCurrentItem(position, false)
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