package com.rkhvstnv.testecommerce.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.navGraphViewModels
import com.rkhvstnv.testecommerce.details.databinding.FragmentShopBinding
import com.rkhvstnv.testecommerce.details.di.DetailsComponentViewModule
import javax.inject.Inject


internal class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by navGraphViewModels<DetailsViewModel>(R.id.navigation_details) { viewModelFactory }

    override fun onAttach(context: Context) {
        ViewModelProvider(this)
            .get<DetailsComponentViewModule>()
            .detailsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.testValue.observe(viewLifecycleOwner){
            value ->
            value?.let {
                binding.tvShopCpuName.text = value
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}