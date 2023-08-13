package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.emarket.R
import com.example.emarket.databinding.FragmentLoginBinding
import com.example.emarket.presenter.LoginContract
import com.example.emarket.presenter.LoginPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.activity.MainActivity


class LoginFragment : Fragment(), LoginContract.View {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var presenter: LoginPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = LoginPresenter(view=this, context=requireContext())
        val loggedIn = presenter.getLoginPreference()
        if (loggedIn) {
            navigateToProfile()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            presenter.checkLoginRemote(email, password)
        }

        navigateToSignup()
    }

    override fun navigateToSignup() {
        binding.tvSignup.setOnClickListener {
            AppUtils.navigateToFragment(requireActivity() as AppCompatActivity, R.id.login_fragment_container, SignupFragment())
        }
    }

    override fun navigateToProfile() {
        AppUtils.navigateToActivity(requireContext(), MainActivity::class.java)
    }


}