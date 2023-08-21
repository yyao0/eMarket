package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentOrderDetailsBinding
import com.example.emarket.model.local.entity.Order
import com.example.emarket.model.local.entity.Subcategory
import com.example.emarket.presenter.OrderDetailsContract
import com.example.emarket.presenter.OrderDetailsPresenter
import com.example.emarket.view.adapter.CheckoutItemAdapter

class OrderDetailsFragment : Fragment(), OrderDetailsContract.View {
    private lateinit var binding: FragmentOrderDetailsBinding
    private lateinit var presenter: OrderDetailsPresenter
    private lateinit var itemAdapter: CheckoutItemAdapter
    private var orderId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderId = arguments?.getString(ARG_ORDER_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = OrderDetailsPresenter(this, requireContext())
        orderId?.let { presenter.getOrderDetailsRemote(it) }
    }

    override fun displayOrderDetails(order: Order) {
        with(binding){
            tvOrderId.text = "#${orderId}"
            tvOrderStatus.text = order.order_status
            tvTotalBill.text = "$ ${order.bill_amount}"
            tvAddressTitle.text = order.address_title
            tvAddress.text = order.address
            var payment = order.payment_method
            if (payment == "COD") {
                payment = "Cash on Delivery"
            }
            tvPayment.text = payment
            val items = order.items
            itemAdapter = CheckoutItemAdapter(items)
            rvCheckoutCart.adapter = itemAdapter
            rvCheckoutCart.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    companion object {
        private const val ARG_ORDER_ID = "orderId"
        fun newInstance(orderId: String): OrderDetailsFragment {
            val fragment = OrderDetailsFragment()
            val args = Bundle()
            args.putString(ARG_ORDER_ID, orderId)
            fragment.arguments = args
            return fragment
        }
    }
}