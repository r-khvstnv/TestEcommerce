package com.rkhvstnv.testecommerce.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.rkhvstnv.testecommerce.home.databinding.FragmentHomeBinding
import com.rkhvstnv.testecommerce.home.di.HomeComponentViewModule
import javax.inject.Inject


internal class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

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

        binding.ivFilter.setOnClickListener {
            navigateToDetails(id = 7)
        }
    }


    private fun navigateToDetails(id: Int){
        val link = getString(com.rkhvstnv.testecommerce.core.R.string.deep_link_details_base)
        val uri = Uri.parse(link + id)
        findNavController().navigate(uri)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}