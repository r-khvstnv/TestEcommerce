package com.rkhvstnv.testecommerce.cart

import android.annotation.SuppressLint
import android.content.Context
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
import com.rkhvstnv.testecommerce.cart.databinding.FragmentCartBinding
import com.rkhvstnv.testecommerce.cart.di.CartComponentViewModule
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.model.ProductInCart
import com.rkhvstnv.testecommerce.utils.format
import com.rkhvstnv.testecommerce.utils.formatToTwoDecimals
import javax.inject.Inject

internal class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CartViewModel> { viewModelFactory }

    private lateinit var productsInCartAdapter: ProductsInCartAdapter

    override fun onAttach(context: Context) {
        ViewModelProvider(this)
            .get<CartComponentViewModule>()
            .cartComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductInCartRecyclerView()

        viewModel.inCartResult.observe(viewLifecycleOwner){
            result ->
            when(result){
                is MyResult.Error -> {
                    Toast.makeText(requireContext(), "${result.code} \n ${result.message}", Toast.LENGTH_LONG).show()
                }
                is MyResult.Exception -> {
                    result.e.printStackTrace()
                    Toast.makeText(requireContext(), result.e.toString(), Toast.LENGTH_LONG).show()
                }
                else -> Unit
            }
        }
        viewModel.allProductsInCart.observe(viewLifecycleOwner){
            list ->
            productsInCartAdapter.submitList(list)
            viewModel.estimateTotalCost()
        }
        viewModel.totalCost.observe(viewLifecycleOwner){
            cost ->
            binding.tvCartTotalValue.text =
                getString(com.rkhvstnv.testecommerce.utils.R.string.currency_us) +
                        cost.format() +
                        getString(com.rkhvstnv.testecommerce.utils.R.string.currency_us_typed)
        }

        binding.ibCartBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun setupProductInCartRecyclerView(){
        productsInCartAdapter = ProductsInCartAdapter(
            requireContext(),
            object : ProductAdapterCallback{
                override fun onAmountChange(productInCart: ProductInCart, newValue: Int) {
                    viewModel.changeProductInCartAmount(productInCart = productInCart, amount = newValue)
                }


                override fun onDeleteClick(productInCart: ProductInCart) {
                    viewModel.removeProductFromCart(productInCart = productInCart)
                }

            }
        )

        binding.rvProductsInCart.apply {
            adapter = productsInCartAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onPause() {
        viewModel.updateProductsInCart()
        super.onPause()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}