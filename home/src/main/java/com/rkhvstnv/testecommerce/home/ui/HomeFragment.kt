package com.rkhvstnv.testecommerce.home.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.models.BestSeller
import com.rkhvstnv.testecommerce.core_data.domain.models.HotSale
import com.rkhvstnv.testecommerce.home.ui.adapters.CategoryAdapter
import com.rkhvstnv.testecommerce.home.databinding.FragmentHomeBinding
import com.rkhvstnv.testecommerce.home.di.HomeComponentViewModule
import com.rkhvstnv.testecommerce.home.ui.adapters.HomeAdapterDelegator
import javax.inject.Inject


internal class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    private lateinit var categoryAdapter: CategoryAdapter
    private var hotSaleAdapter = HomeAdapterDelegator{}
    private lateinit var bestSellerAdapter: HomeAdapterDelegator


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
                is MyResult.Success -> {
                    hotSaleAdapter.items = result.data
                    hotSaleAdapter.notifyItemRangeChanged(0, result.data.size)
                }
            }
        }
        viewModel.bestSellersResult.observe(viewLifecycleOwner){
            result ->
            when(result){
                is MyResult.Error -> {
                    Toast.makeText(requireContext(), "${result.code} \n ${result.message}", Toast.LENGTH_LONG).show()
                }
                is MyResult.Exception -> {
                    result.e.printStackTrace()
                    Toast.makeText(requireContext(), result.e.toString(), Toast.LENGTH_LONG).show()
                }
                is MyResult.Success -> {
                    bestSellerAdapter.items = result.data
                    bestSellerAdapter.notifyItemRangeChanged(0, result.data.size)
                }
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
        categoryAdapter = CategoryAdapter(requireContext(), viewModel::requestCategoryById)
        hotSaleAdapter = HomeAdapterDelegator {}
        bestSellerAdapter = HomeAdapterDelegator(this::navigateToDetails)
        hotSaleAdapter.items = listOf(HotSale(
            id =  -1,
            is_buy = false,
            is_new = false,
            image = "",
            title = "",
            subtitle = ""
        ))
        bestSellerAdapter.items = listOf(
            BestSeller(
                id = -1,
                is_favorites = false,
                "",
                "",
                0,
                0
            )
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
        binding.rvBestSellers.apply {
            adapter = bestSellerAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }




    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
