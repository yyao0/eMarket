package com.example.emarket.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.emarket.R
import com.example.emarket.databinding.ActivityMainBinding
import com.example.emarket.presenter.MainContract
import com.example.emarket.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(view = this, context = this)

        binding.btnLogout.setOnClickListener {
            presenter.logout()
            navigateToLogin()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.activateSplashOnDestroy()
    }

    override fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


}