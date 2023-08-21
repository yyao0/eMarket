package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentCategoryBinding
import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.model.local.entity.Category
import com.example.emarket.model.local.entity.Product
import com.example.emarket.presenter.CategoryContract
import com.example.emarket.presenter.CategoryPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.adapter.CategoryAdapter
import com.example.emarket.view.adapter.ProductAdapter

class CategoryFragment : Fragment(), CategoryContract.View, CategoryAdapter.CategoryClickListener, ProductAdapter.ProductClickListener{
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var presenter: CategoryPresenter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter
    val productLayoutParams = LinearLayoutCompat.LayoutParams(
        LinearLayoutCompat.LayoutParams.MATCH_PARENT,
        LinearLayoutCompat.LayoutParams.WRAP_CONTENT)
    val categoryLayoutParams = LinearLayoutCompat.LayoutParams(
        LinearLayoutCompat.LayoutParams.MATCH_PARENT,
        LinearLayoutCompat.LayoutParams.WRAP_CONTENT)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle("eMarket")
        presenter = CategoryPresenter(this, requireContext())
        presenter.getCategoryRemote()
        binding.searchBar.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null || newText == "") {
                    presenter.getCategoryRemote()
                }
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query != "") {
                    presenter.getSearchProductRemote(query)
                }
                else {
                    presenter.getCategoryRemote()
                }
                return true
            }
        })
    }

    override fun displayCategory(categories: List<Category>) {
        categoryAdapter = CategoryAdapter(categories, this)
        binding.rvContainer.layoutParams = categoryLayoutParams
        binding.rvContainer.adapter = categoryAdapter
        binding.rvContainer.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun navigateToSubcategory(subcategoryFragment: Fragment) {
        AppUtils.navigateToFragment(requireActivity() as AppCompatActivity, R.id.main_fragment_container, subcategoryFragment)
    }

    override fun displaySearchProduct(products: List<Product>) {
        productAdapter = ProductAdapter(products, CartDao(requireContext()), this)
        binding.rvContainer.layoutParams = productLayoutParams
        binding.rvContainer.adapter = productAdapter
        binding.rvContainer.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun navigateToProductDetails(productFragment: Fragment) {
        AppUtils.navigateToFragment(requireActivity() as AppCompatActivity, R.id.main_fragment_container, productFragment)
    }

    override fun onProductClick(product: Product) {
        val fragment = ProductDetailsFragment.newInstance(product)
        navigateToProductDetails(fragment)
    }

    override fun onCategoryClick(category: Category) {
        val fragment = SubcategoryFragment.newInstance(category)
        navigateToSubcategory(fragment)
    }
}