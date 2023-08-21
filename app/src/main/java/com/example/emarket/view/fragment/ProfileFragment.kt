package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.emarket.R
import com.example.emarket.databinding.FragmentProfileBinding
import com.example.emarket.model.local.entity.User
import com.example.emarket.presenter.ProfileContract
import com.example.emarket.presenter.ProfilePresenter
import com.example.emarket.utils.AppUtils

class ProfileFragment : Fragment(), ProfileContract.View {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var presenter: ProfilePresenter
    private var user: User? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle("Profile")
        presenter = ProfilePresenter(this, requireContext())
        user = presenter.getUser()
        user?.let { displayUser(it) }
        navigateToOrders()
    }

    override fun displayUser(user: User) {
        with(binding){
            tvEmail.text = user.email
            tvName.text = user.fullName
            tvPhone.text = user.mobileNo
        }
    }

    override fun navigateToOrders() {
        binding.btnOrders.setOnClickListener {
            AppUtils.navigateToFragment(
                requireActivity() as AppCompatActivity,
                R.id.main_fragment_container,
                OrdersFragment()
            )
        }
    }


}