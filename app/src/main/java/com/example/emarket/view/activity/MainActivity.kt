package com.example.emarket.view.activity

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

        presenter = MainPresenter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.activateSplashOnDestroy(this)
    }




}