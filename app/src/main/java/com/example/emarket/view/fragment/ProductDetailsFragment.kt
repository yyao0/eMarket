package com.example.emarket.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emarket.databinding.FragmentProductDetailsBinding
import com.example.emarket.model.local.dao.CartDao
import com.example.emarket.model.local.entity.Product
import com.example.emarket.presenter.ProductDetailsContract
import com.example.emarket.presenter.ProductDetailsPresenter
import com.example.emarket.view.CartWidgetView
import com.example.emarket.view.adapter.ImagePagerAdapter
import com.example.emarket.view.adapter.ReviewAdapter
import com.example.emarket.view.adapter.SpecificationAdapter
import com.google.android.material.tabs.TabLayout

class ProductDetailsFragment : Fragment(), ProductDetailsContract.View, CartWidgetView.OnQuantityChangeListener {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var presenter: ProductDetailsPresenter
    private lateinit var imageAdapter: ImagePagerAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var specificationAdapter: SpecificationAdapter
    private lateinit var product: Product
    private lateinit var cartDao: CartDao
    private lateinit var cartWidgetView: CartWidgetView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getParcelable<Product>(ARG_PRODUCT)!!
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
        cartDao = CartDao(requireContext())
        presenter.getProductDetailsRemote(product.product_id)
        cartWidgetView  = binding.cartCounter
        cartWidgetView.setOnQuantityChangeListener(this)
        addProductToCart()
    }

    override fun displayProductDetails(product: Product) {
        this.product = product
        val imageUrls = product.images.map { it.image }
        imageAdapter = ImagePagerAdapter(requireContext(), imageUrls)
        binding.vpImage.adapter = imageAdapter
        binding.circleIndicator.setViewPager(binding.vpImage)
        binding.tvPrice.text = "$ ${product.price}"
        binding.tabsDetails.addTab(binding.tabsDetails.newTab().setText("Specifications"))
        binding.tabsDetails.addTab(binding.tabsDetails.newTab().setText("Reviews"))
        val reviews = product.reviews
        reviewAdapter = ReviewAdapter(reviews)
        binding.rvReviews.adapter = reviewAdapter
        binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())
        val specifications = product.specifications
        specificationAdapter = SpecificationAdapter(specifications)
        binding.rvSpecifications.adapter = specificationAdapter
        binding.rvSpecifications.layoutManager = LinearLayoutManager(requireContext())
        binding.tabsDetails.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position){
                    0 -> {
                        binding.rvSpecifications.visibility = View.VISIBLE
                        binding.rvReviews.visibility = View.GONE
                    }
                    1 -> {
                        binding.rvSpecifications.visibility = View.GONE
                        binding.rvReviews.visibility = View.VISIBLE
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun addProductToCart() {
        binding.cartCounter.setProduct(product.product_id)
        binding.cartCounter.updateQuantityText()
        val quantity = presenter.getProductQuantity(product.product_id)
        if (quantity == 0) {
            binding.tvAddCart.visibility = View.VISIBLE
            binding.cartCounter.visibility = View.GONE
        } else {
            binding.tvAddCart.visibility = View.GONE
            binding.cartCounter.visibility = View.VISIBLE
        }
        binding.tvAddCart.setOnClickListener {
            cartDao.insertOrUpdateCartItem(product, 1)
            binding.cartCounter.updateQuantityText()
            binding.tvAddCart.visibility = View.GONE
            binding.cartCounter.visibility = View.VISIBLE
        }
    }

    override fun onQuantityChanged(newQuantity: Int) {
        Log.i("tag", newQuantity.toString())
        if (newQuantity == 0) {
            binding.tvAddCart.visibility = View.VISIBLE
            binding.cartCounter.visibility = View.GONE
        } else {
            binding.tvAddCart.visibility = View.GONE
            binding.cartCounter.visibility = View.VISIBLE
        }
    }

    companion object {

        private const val ARG_PRODUCT = "product"

        fun newInstance(product: Product): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle()
            args.putParcelable(ARG_PRODUCT, product)
            fragment.arguments = args
            return fragment
        }
    }
}