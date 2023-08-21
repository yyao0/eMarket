package com.example.emarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.emarket.R
import com.example.emarket.databinding.FragmentAddAddressBinding
import com.example.emarket.model.local.entity.Addresse


class AddAddressFragment : DialogFragment() {
    lateinit var listener: OnAddressSavedListener
    private lateinit var binding: FragmentAddAddressBinding

    interface OnAddressSavedListener {
        fun onAddressSaved(address: String, title: String)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            val width = (resources.displayMetrics.widthPixels * 0.8).toInt()
            val height = (resources.displayMetrics.heightPixels * 0.8).toInt()
            setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val title = binding.etAddressTitle.text.toString()
            val address = binding.etAddress.text.toString()
            listener.onAddressSaved(title, address)
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
    companion object {
        const val TAG = "AddAddressFragment"

        fun newInstance(): AddAddressFragment {
            return AddAddressFragment()
        }
    }
}