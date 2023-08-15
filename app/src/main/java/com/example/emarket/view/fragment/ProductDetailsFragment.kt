package com.example.emarket.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentProductDetailsBinding
import com.example.emarket.model.local.entity.Product
import com.example.emarket.model.local.entity.Subcategory
import com.example.emarket.presenter.ProductDetailsContract
import com.example.emarket.presenter.ProductDetailsPresenter
import com.example.emarket.view.adapter.ImagePagerAdapter
import com.example.emarket.view.adapter.ReviewAdapter
import com.example.emarket.view.adapter.SpecificationAdapter
import com.google.android.material.tabs.TabLayoutMediator


class ProductDetailsFragment : Fragment(), ProductDetailsContract.View {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var presenter: ProductDetailsPresenter
    private lateinit var imageAdapter: ImagePagerAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var specificationAdapter: SpecificationAdapter
    private var productId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = arguments?.getString(ARG_PRODUCT_ID).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ProductDetailsPresenter(this, requireContext())
        presenter.getProductDetailsRemote(productId)
        addProductToCart()
    }

    override fun displayProductDetails(product: Product) {
        val imageUrls = product.images.map { it.image }
        imageAdapter = ImagePagerAdapter(requireContext(), imageUrls)
        binding.vpImage.adapter = imageAdapter
        binding.circleIndicator.setViewPager(binding.vpImage)

        val reviews = product.reviews
        Log.i("tag", reviews.toString())
        reviewAdapter = ReviewAdapter(reviews)
        binding.rvReviews.adapter = reviewAdapter
        binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())

        val specifications = product.specifications
        specificationAdapter = SpecificationAdapter(specifications)
        binding.rvSpecifications.adapter = specificationAdapter
        binding.rvSpecifications.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun addProductToCart() {
        binding.cartCounter.setProduct(productId)
        binding.cartCounter.updateQuantityText()
        val quantity = presenter.getProductQuantity(productId)
        if (quantity == 0){
        binding.tvAddCart.setOnClickListener {
            binding.tvAddCart.visibility = View.GONE
            binding.cartCounter.visibility = View.VISIBLE

        }
        } else {
            binding.tvAddCart.visibility = View.GONE
            binding.cartCounter.visibility = View.VISIBLE
        }
    }

    companion object {

        private const val ARG_PRODUCT_ID = "productId"

        fun newInstance(productId: String): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle()
            args.putString(ARG_PRODUCT_ID, productId)
            fragment.arguments = args
            return fragment
        }
    }


}