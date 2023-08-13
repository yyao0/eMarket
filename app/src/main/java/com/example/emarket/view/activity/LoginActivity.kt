package com.example.emarket.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.emarket.R
import com.example.emarket.databinding.ActivityLoginBinding
import com.example.emarket.presenter.LoginContract
import com.example.emarket.presenter.LoginPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.fragment.SignupFragment

class LoginActivity : AppCompatActivity() , LoginContract.View {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = LoginPresenter(view = this, context = this)

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
            AppUtils.navigateToFragment(this, SignupFragment())
        }
    }

    override fun navigateToProfile() {
        AppUtils.navigateToActivity(this, MainActivity::class.java)
    }

}