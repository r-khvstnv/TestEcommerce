package com.rkhvstnv.testecommerce.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.home.databinding.FragmentHomeBinding
import com.rkhvstnv.testecommerce.home.di.HomeComponentViewModule
import javax.inject.Inject


internal class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    private lateinit var categoryAdapter: CategoryAdapter
    private val hotSaleAdapter by lazy { HotSaleAdapter(requireContext()) }

    override fun onAttach(context: Context) {
        ViewModelProvider(this)
            .get<HomeComponentViewModule>()
            .homeComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()

        viewModel.allCategories.observe(viewLifecycleOwner){
            list ->
            categoryAdapter.submitList(list)
        }
        viewModel.hotSalesResult.observe(viewLifecycleOwner){
            result ->
            when(result){
                is MyResult.Error -> {
                    Toast.makeText(requireContext(), "${result.code} \n ${result.message}", Toast.LENGTH_LONG).show()
                }
                is MyResult.Exception -> {
                    result.e.printStackTrace()
                    Toast.makeText(requireContext(), result.e.toString(), Toast.LENGTH_LONG).show()
                }
                is MyResult.Success -> hotSaleAdapter.submitList(result.data)
            }
        }

        binding.ivFilter.setOnClickListener {
            BottomSheetFilterFragment().show(parentFragmentManager, "tag")
        }
        binding.ivQrScanner.setOnClickListener {
            navigateToDetails(id = 7)
        }
    }


    private fun navigateToDetails(id: Int){
        val link = getString(com.rkhvstnv.testecommerce.core.R.string.deep_link_details_base)
        val uri = Uri.parse(link + id)
        findNavController().navigate(uri)
    }

    private fun setupRecyclerViews(){
        categoryAdapter = CategoryAdapter(
            requireContext(),
            object : CategoryAdapterCallback{
                override fun onClick(id: Int) {
                    viewModel.requestCategoryById(id = id)
                }

            }
        )

        binding.rvCategories.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        binding.rvHotSales.apply {
            adapter = hotSaleAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}