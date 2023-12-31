package com.example.emarket.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.emarket.R
import com.example.emarket.databinding.FragmentCheckoutPaymentBinding
import com.example.emarket.presenter.CheckoutPaymentContract
import com.example.emarket.presenter.CheckoutPaymentPresenter
import com.example.emarket.utils.AppUtils

class CheckoutPaymentFragment : Fragment(), CheckoutPaymentContract.View {
    private lateinit var binding: FragmentCheckoutPaymentBinding
    private lateinit var presenter: CheckoutPaymentPresenter
    private lateinit var selectedButton: RadioButton
    private var selectedPaymentId: Int = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutPaymentBinding.inflate(inflater, container, false)
        binding.rbCash.isChecked = true
        selectedButton = binding.rbCash
        selectedPaymentId = binding.rbCash.id
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CheckoutPaymentPresenter(this, requireContext())
        selectPayment()
        navigateToNext()
    }

    override fun selectPayment() {
        binding.rbCash.setOnClickListener { handlePaymentOptionSelection(binding.rbCash) }
        binding.rbBank.setOnClickListener { handlePaymentOptionSelection(binding.rbBank) }
        binding.rbCard.setOnClickListener { handlePaymentOptionSelection(binding.rbCard) }
        binding.rbPaypal.setOnClickListener { handlePaymentOptionSelection(binding.rbPaypal) }
    }

    private fun handlePaymentOptionSelection(selected: RadioButton) {
        selectedButton?.isChecked = false
        selectedButton = selected
        selectedPaymentId = selectedButton.id
    }

    override fun navigateToNext() {
        binding.btnNext.setOnClickListener {
            when (selectedPaymentId) {
                R.id.rb_cash -> {
                    presenter.savePaymentOption("Cash On Delivery")
                }
                R.id.rb_bank -> {
                    presenter.savePaymentOption("Internet Banking")
                }
                R.id.rb_card -> {
                    presenter.savePaymentOption("Debit Card / Credit Card")
                }
                R.id.rb_paypal -> {
                    presenter.savePaymentOption("PayPal")
                }
                else -> {
                    presenter.savePaymentOption("")
                }
            }
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager_checkout)
            viewPager.currentItem = 3
        }
    }
}