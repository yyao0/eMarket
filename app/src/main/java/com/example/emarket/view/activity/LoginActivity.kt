package com.example.emarket.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.emarket.R
import com.example.emarket.databinding.ActivityLoginBinding
import com.example.emarket.view.fragment.LoginFragment

class LoginActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.login_fragment_container, LoginFragment())
            .addToBackStack(null)
            .commit()
    }
}