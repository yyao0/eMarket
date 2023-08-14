package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.emarket.databinding.FragmentSubcategoryBinding
import com.example.emarket.model.local.entity.Category
import com.example.emarket.model.local.entity.Subcategory
import com.example.emarket.presenter.SubcategoryContract
import com.example.emarket.presenter.SubcategoryPresenter
import com.example.emarket.view.adapter.SubcategoryPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class SubcategoryFragment : Fragment(), SubcategoryContract.View {

    private lateinit var binding: FragmentSubcategoryBinding
    private lateinit var category: Category
    private lateinit var presenter: SubcategoryPresenter
    private lateinit var adapter: SubcategoryPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getParcelable(ARG_CATEGORY) ?: Category("", "", "", "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubcategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SubcategoryPresenter(this, requireContext())
        presenter.getSubcategoryRemote(category.categoryId)
    }

    override fun displaySubcategoryProduct(subcategories: List<Subcategory>) {
        adapter = SubcategoryPagerAdapter(subcategories, childFragmentManager, lifecycle)
        binding.subcategoryPager.adapter = adapter
        TabLayoutMediator(binding.subcategoryTabs, binding.subcategoryPager) {tab, position ->
            val subcategory = subcategories[position]
            tab.text = subcategory.name
        }.attach()
    }

    override fun navigateToProduct(productFragment: Fragment) {
        TODO("Not yet implemented")
    }

    companion object {

        private const val ARG_CATEGORY = "category"

        fun newInstance(category: Category): SubcategoryFragment {
            val fragment = SubcategoryFragment()
            val args = Bundle()
            args.putParcelable(ARG_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }







}
