package com.example.emarket.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.emarket.R
import com.example.emarket.databinding.ActivitySignupBinding
import com.example.emarket.presenter.SignupContract
import com.example.emarket.presenter.SignupPresenter
import com.example.emarket.utils.AppUtils

class SignupActivity : AppCompatActivity(), SignupContract.View {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var presenter: SignupPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = SignupPresenter(view=this, context=this)

        binding.btnSignup.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val password2 = binding.etPassword2.text.toString()
            presenter.checkSignup(username, password, password2)
        }
    }

    override fun navigateToProfile() {
        AppUtils.navigateToActivity(this, MainActivity::class.java)
    }
}