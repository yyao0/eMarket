package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentAddressBinding
import com.example.emarket.databinding.FragmentCheckoutDeliveryBinding
import com.example.emarket.model.local.entity.Addresse
import com.example.emarket.presenter.AddressContract
import com.example.emarket.presenter.AddressPresenter
import com.example.emarket.presenter.CheckoutDeliveryPresenter
import com.example.emarket.view.adapter.AddressAdapter
import com.example.emarket.view.adapter.AddressProfileAdapter

class AddressFragment : Fragment(), AddressContract.View, AddAddressFragment.OnAddressSavedListener {
    private lateinit var binding: FragmentAddressBinding
    private lateinit var adapter: AddressProfileAdapter
    private lateinit var presenter: AddressPresenter
    private var userId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = AddressPresenter(this, requireContext())
        userId = presenter.getUserId()
        presenter.getAddressesRemote(userId)
        navigateToAddAddress()
    }

    override fun displayAddresses(addresses: List<Addresse>) {
        adapter = AddressProfileAdapter(addresses.toMutableList())
        binding.rvAddress.adapter = adapter
        binding.rvAddress.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun navigateToAddAddress() {
        binding.btnAddAddress.setOnClickListener{
            val addAddressFragment = AddAddressFragment.newInstance()
            addAddressFragment.listener = this
            addAddressFragment.show(childFragmentManager, AddAddressFragment.TAG)
        }
    }

    override fun onAddressSaved(title: String, address: String) {
        presenter.addAddress(userId, title, address)
        adapter.insertAddress(Addresse(title=title, address = address))
    }
}