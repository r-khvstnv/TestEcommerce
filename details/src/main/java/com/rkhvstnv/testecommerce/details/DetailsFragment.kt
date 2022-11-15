package com.rkhvstnv.testecommerce.details

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rkhvstnv.testecommerce.details.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DetailsTabPagerAdapter(this, 3)
        binding.viewPagerTabs.adapter = adapter

        binding.ibDetailsCart.setOnClickListener { navigateToCart() }
    }


    private fun navigateToCart(){
        val uri = Uri.parse(getString(com.rkhvstnv.testecommerce.core.R.string.deep_link_cart_url))
        findNavController().navigate(uri)
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}