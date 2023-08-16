package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentCheckoutDeliveryBinding
import com.example.emarket.model.local.entity.Addresse
import com.example.emarket.model.local.entity.Order
import com.example.emarket.presenter.CheckoutDeliveryContract
import com.example.emarket.presenter.CheckoutDeliveryPresenter
import com.example.emarket.view.adapter.AddressAdapter

class CheckoutDeliveryFragment : Fragment(), CheckoutDeliveryContract.View {
    private lateinit var binding: FragmentCheckoutDeliveryBinding
    private lateinit var adapter: AddressAdapter
    private lateinit var presenter: CheckoutDeliveryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutDeliveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CheckoutDeliveryPresenter(this, requireContext())
        val userId = presenter.getUserId()
        presenter.getAddressesRemote(userId)
    }

    override fun displayAddresses(addresses: List<Addresse>) {
        adapter = AddressAdapter(addresses)
        binding.rvAddresses.adapter = adapter
        binding.rvAddresses.layoutManager = LinearLayoutManager(requireContext())
    }
}