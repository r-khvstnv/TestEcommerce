package com.rkhvstnv.testecommerce.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rkhvstnv.testecommerce.home.databinding.FragmentBottomSheetFilterBinding

class BottomSheetFilterFragment: BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBottomSheetFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mockDropdownMenus()

        with(binding){
            ibClose.setOnClickListener { parentFragmentManager.popBackStack() }
            ibDone.setOnClickListener { parentFragmentManager.popBackStack() }
        }
    }


    private fun mockDropdownMenus(){
        val brands = arrayListOf("Samsung")
        val prices = arrayListOf("$300 - $500")
        val sizes = arrayListOf("4.5 to 5.5 inches")

        binding.spinnerBrand.apply{
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.spinner_item,
                brands
            )
            this.adapter = adapter
        }
        binding.spinnerPrice.apply{
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.spinner_item,
                prices
            )
            this.adapter = adapter
        }
        binding.spinnerSize.apply{
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.spinner_item,
                sizes
            )
            this.adapter = adapter
        }

    }



    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}