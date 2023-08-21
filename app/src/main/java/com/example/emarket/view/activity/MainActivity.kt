package com.example.emarket.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import com.example.emarket.R
import com.example.emarket.databinding.ActivityMainBinding
import com.example.emarket.databinding.NavigationDrawerHeaderBinding
import com.example.emarket.model.local.DatabaseHelper
import com.example.emarket.model.local.entity.User
import com.example.emarket.presenter.MainContract
import com.example.emarket.presenter.MainPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.fragment.CartFragment
import com.example.emarket.view.fragment.CategoryFragment
import com.example.emarket.view.fragment.OrdersFragment
import com.example.emarket.view.fragment.ProfileFragment

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataHelper: DatabaseHelper
    private lateinit var presenter: MainPresenter
    private lateinit var user: User
    private var isDarkTheme = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toggleTheme()
        initNavigationDrawer()
        presenter = MainPresenter(view = this, context = this)
        user = presenter.getUserPreference()
        val headerBinding = NavigationDrawerHeaderBinding.bind(binding.nvView.getHeaderView(0))
        if (this::user.isInitialized){
            headerBinding.tvHeaderName.text = user.fullName
            headerBinding.tvHeaderEmail.text = user.email
            headerBinding.tvHeaderMobile.text = user.mobileNo
        }
        initDatabase()
        AppUtils.navigateToFragment(this, R.id.main_fragment_container, CategoryFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else{
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.activateSplashOnDestroy()
    }

    override fun initNavigationDrawer() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        binding.nvView.setNavigationItemSelectedListener { menuItems->
            menuItems.isChecked=true
            when (menuItems.itemId){
                R.id.it_profile -> AppUtils.navigateToFragment(this, R.id.main_fragment_container, ProfileFragment())
                R.id.it_cart -> AppUtils.navigateToFragment(this, R.id.main_fragment_container, CartFragment())
                R.id.it_order -> AppUtils.navigateToFragment(this, R.id.main_fragment_container, OrdersFragment())
                R.id.it_home -> AppUtils.navigateToFragment(this, R.id.main_fragment_container, CategoryFragment())
                R.id.it_logout -> presenter.logoutRemote(user.email)
            }
            true
        }

        val headerBinding = NavigationDrawerHeaderBinding.bind(binding.nvView.getHeaderView(0))
        headerBinding.switchTheme.setOnCheckedChangeListener { buttonView, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
            if (isChecked){
                AppCompatDelegate.MODE_NIGHT_YES
            } else{
                AppCompatDelegate.MODE_NIGHT_NO
            })
        }
    }

    override fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toggleTheme() {
        if (isDarkTheme) {
            setTheme(R.style.ThemeDark)
        } else {
            setTheme(R.style.ThemeLight)
        }
    }
    private fun initDatabase(){
        dataHelper = DatabaseHelper(this)
    }
}