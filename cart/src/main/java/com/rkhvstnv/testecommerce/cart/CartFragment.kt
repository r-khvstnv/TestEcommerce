package com.rkhvstnv.testecommerce.cart

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
import com.rkhvstnv.testecommerce.cart.databinding.FragmentCartBinding
import com.rkhvstnv.testecommerce.cart.di.CartComponentViewModule
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import javax.inject.Inject

internal class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CartViewModel> { viewModelFactory }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel._allProductsInCartResult.observe(viewLifecycleOwner){
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
                    Toast.makeText(requireContext(), result.data.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}