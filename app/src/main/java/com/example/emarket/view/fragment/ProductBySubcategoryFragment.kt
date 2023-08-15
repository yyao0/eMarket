package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentProductBySubcategoryBinding
import com.example.emarket.model.local.entity.Category
import com.example.emarket.model.local.entity.Product
import com.example.emarket.model.local.entity.Subcategory
import com.example.emarket.presenter.ProductBySubcategoryContract
import com.example.emarket.presenter.ProductBySubcategoryPresenter
import com.example.emarket.utils.AppUtils
import com.example.emarket.view.adapter.ProductAdapter
import com.example.emarket.view.adapter.SubcategoryPagerAdapter


class ProductBySubcategoryFragment : Fragment(), ProductBySubcategoryContract.View, ProductAdapter.ProductClickListener {

    private lateinit var binding: FragmentProductBySubcategoryBinding
    private lateinit var presenter: ProductBySubcategoryPresenter
    private lateinit var subcategory: Subcategory
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subcategory = arguments?.getParcelable(ARG_SUBCATEGORY)!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBySubcategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ProductBySubcategoryPresenter(this, requireContext())
        presenter.getProductRemote(subcategory.subcategoryId)
    }

    companion object {
        private const val ARG_SUBCATEGORY = "subcategory"

        fun newInstance(subcategory: Subcategory): ProductBySubcategoryFragment {
            val fragment = ProductBySubcategoryFragment()
            val args = Bundle()
            args.putParcelable(ARG_SUBCATEGORY, subcategory)
            fragment.arguments = args
            return fragment
        }
    }

    override fun displayProduct(products: List<Product>) {
        adapter = ProductAdapter(requireContext(), products, this)
        binding.rvSubcategoryProduct.adapter = adapter
        binding.rvSubcategoryProduct.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun navigateToProduct(productFragment: Fragment) {
        AppUtils.navigateToFragment(requireActivity() as AppCompatActivity, R.id.main_fragment_container, productFragment)
    }

    override fun onProductClick(product: Product) {
        val fragment = ProductDetailsFragment.newInstance(product.product_id)
        navigateToProduct(fragment)
    }
}