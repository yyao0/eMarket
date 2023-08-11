package com.example.emarket.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.emarket.R
import com.example.emarket.databinding.ActivityIntroBinding
import com.example.emarket.presenter.IntroContract
import com.example.emarket.presenter.IntroPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.adapter.IntroPagerAdapter
import com.example.emarket.view.fragment.IntroOneFragment
import com.example.emarket.view.fragment.IntroThreeFragment
import com.example.emarket.view.fragment.IntroTwoFragment
import com.google.android.material.tabs.TabLayoutMediator

class IntroActivity : AppCompatActivity(), IntroContract.View {
    private lateinit var binding: ActivityIntroBinding
    private lateinit var viewpagerAdapter: IntroPagerAdapter
    private lateinit var presenter: IntroPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = IntroPresenter(view=this, context=this)
        presenter.getIntroSharedPreference(this)

        displayTabs()


        binding.btnSkip.setOnClickListener {
            navigateToLogin()
        }
    }


    override fun displayTabs() {
        val fragments = listOf(IntroThreeFragment(), IntroOneFragment(), IntroTwoFragment())
        viewpagerAdapter = IntroPagerAdapter(
            fragments,
            this@IntroActivity
        )
        with(binding) {
            viewPager2.adapter = viewpagerAdapter
            TabLayoutMediator(tabLayout, viewPager2) { tab, _ ->
                tab.setIcon(R.drawable.tab_indicator)
            }.attach()

        }
    }

    override fun navigateToLogin() {
        AppUtils.navigateToActivity(this, LoginActivity::class.java)
    }


}