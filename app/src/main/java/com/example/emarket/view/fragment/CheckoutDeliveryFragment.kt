package com.example.emarket.view.fragment

import android.location.Address
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.emarket.R
import com.example.emarket.databinding.FragmentCheckoutDeliveryBinding
import com.example.emarket.model.local.entity.Addresse
import com.example.emarket.model.local.entity.Order
import com.example.emarket.presenter.CheckoutDeliveryContract
import com.example.emarket.presenter.CheckoutDeliveryPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.ViewConstants
import com.example.emarket.view.adapter.AddressAdapter

class CheckoutDeliveryFragment : Fragment(), CheckoutDeliveryContract.View, AddAddressFragment.OnAddressSavedListener {
    private lateinit var binding: FragmentCheckoutDeliveryBinding
    private lateinit var adapter: AddressAdapter
    private lateinit var presenter: CheckoutDeliveryPresenter
    private lateinit var selectedAddresse: Addresse
    private var userId = ""

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
        userId = presenter.getUserId()
        presenter.getAddressesRemote(userId)
        navigateToAddAddress()
        navigateToNext()
    }

    override fun displayAddresses(addresses: List<Addresse>) {
        adapter = AddressAdapter(addresses){address ->
            selectedAddresse = address
        }
        binding.rvAddresses.adapter = adapter
        binding.rvAddresses.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun navigateToAddAddress() {
        binding.btnAddAddress.setOnClickListener{
            val addAddressFragment = AddAddressFragment.newInstance()
            addAddressFragment.listener = this
            addAddressFragment.show(childFragmentManager, AddAddressFragment.TAG)
        }
    }

    override fun navigateToNext() {
        binding.btnNext.setOnClickListener {
            presenter.saveSelectedAddress(selectedAddresse)
            //AppUtils.navigateToFragment(requireActivity() as AppCompatActivity, R.id.viewPager_checkout, CheckoutPaymentFragment())
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager_checkout)
            viewPager.currentItem = 2
        }
    }

    override fun onAddressSaved(address: String, title: String) {
        presenter.addAddress(userId, title, address)
        presenter.getAddressesRemote(userId)
    }
}