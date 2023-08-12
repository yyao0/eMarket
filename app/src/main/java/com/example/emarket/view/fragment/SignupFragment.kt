package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.emarket.R
import com.example.emarket.databinding.FragmentSignupBinding
import com.example.emarket.presenter.SignupContract
import com.example.emarket.presenter.SignupPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.activity.MainActivity


class SignupFragment : Fragment(), SignupContract.View {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var presenter: SignupPresenter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        presenter = SignupPresenter(view=this, context=requireContext())
        return binding.root
    }



    override fun navigateToProfile() {
        AppUtils.navigateToActivity(requireContext(), MainActivity::class.java)
    }


}