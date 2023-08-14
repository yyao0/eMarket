package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.emarket.R
import com.example.emarket.databinding.FragmentCategoryBinding
import com.example.emarket.databinding.FragmentSubcategoryBinding
import com.example.emarket.model.local.entity.Category
import com.example.emarket.model.local.entity.Subcategory
import com.example.emarket.presenter.SubcategoryContract
import com.example.emarket.presenter.SubcategoryPresenter
import com.example.emarket.view.adapter.SubcategoryAdapter


class SubcategoryFragment : Fragment(), SubcategoryContract.View, SubcategoryAdapter.SubcategoryClickListener {

    private lateinit var binding: FragmentSubcategoryBinding
    private lateinit var category: Category
    private lateinit var presenter: SubcategoryPresenter
    private lateinit var adapter: SubcategoryAdapter


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

    override fun displaySubcategory(subcategories: List<Subcategory>) {
        adapter = SubcategoryAdapter(subcategories, this)
        binding.rvContainer.adapter = adapter
        binding.rvContainer.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun navigateToProduct(productFragment: Fragment) {
        TODO("Not yet implemented")
    }

    override fun onSubcategoryClick(subcategory: Subcategory) {
        TODO("Not yet implemented")
    }


}
