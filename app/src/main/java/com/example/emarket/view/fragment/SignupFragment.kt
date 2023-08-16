package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.emarket.databinding.FragmentSignupBinding
import com.example.emarket.presenter.SignupContract
import com.example.emarket.presenter.SignupPresenter


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignup.setOnClickListener {
            val name = binding.etUsername.text.toString()
            val mobile = binding.etMobile.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            presenter.checkSignupRemote(name, mobile, email, password)
        }
    }

    override fun navigateToLogin() {
        requireActivity().supportFragmentManager.popBackStack()
    }
}