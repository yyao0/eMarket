package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentCategoryBinding
import com.example.emarket.model.local.entity.Category
import com.example.emarket.presenter.CategoryContract
import com.example.emarket.presenter.CategoryPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.adapter.CategoryAdapter

class CategoryFragment : Fragment(), CategoryContract.View, CategoryAdapter.CategoryClickListener{
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var presenter: CategoryPresenter
    private lateinit var adapter: CategoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CategoryPresenter(this, requireContext())
        presenter.getCategoryRemote()
    }


    override fun displayCategory(categories: List<Category>) {
        adapter = CategoryAdapter(categories, this)
        binding.rvContainer.adapter = adapter
        binding.rvContainer.layoutManager = GridLayoutManager(requireContext(), 2)

    }

    override fun navigateToSubcategory(subcategoryFragment: Fragment) {
        AppUtils.navigateToFragment(requireActivity() as AppCompatActivity, R.id.main_fragment_container, subcategoryFragment)
    }

    override fun onCategoryClick(category: Category) {
        val fragment = SubcategoryFragment.newInstance(category)
        navigateToSubcategory(fragment)
    }


}