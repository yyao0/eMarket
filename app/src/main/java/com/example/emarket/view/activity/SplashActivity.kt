package com.example.emarket.view.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.emarket.R
import com.example.emarket.databinding.ActivitySplashBinding
import com.example.emarket.presenter.SplashContract
import com.example.emarket.presenter.SplashPresenter

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var presenter: SplashContract.Presenter
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = SplashPresenter(this)
        presenter.accessSplashPreference(this)
    }


    override fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}