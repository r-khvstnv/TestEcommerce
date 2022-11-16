package com.rkhvstnv.testecommerce.details

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.viewpager2.widget.CompositePageTransformer
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.details.databinding.FragmentDetailsBinding
import com.rkhvstnv.testecommerce.details.di.DetailsComponentViewModule
import com.rkhvstnv.testecommerce.utils.loadImage
import javax.inject.Inject
import kotlin.math.abs

internal class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by navGraphViewModels<DetailsViewModel>(R.id.navigation_details) { viewModelFactory }

    private val productImageAdapter by lazy { ProductImageAdapter(requireContext()) }

    override fun onAttach(context: Context) {
        ViewModelProvider(this)
            .get<DetailsComponentViewModule>()
            .detailsComponent.inject(this)
        super.onAttach(context)

        /*
        * Some code using safeArgs or Bundle to get id from extras
        *
        * */
        val id = (0..100).random()
        viewModel.requestPhoneDataById(id = id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductImagesViewPager()
        val adapter = DetailsTabPagerAdapter(this, 3)
        binding.viewPagerTabs.adapter = adapter

        binding.ibDetailsCart.setOnClickListener { navigateToCart() }
        binding.ibDetailsBack.setOnClickListener { findNavController().popBackStack() }

        viewModel.phone.observe(viewLifecycleOwner){
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
                    with(binding){
                        result.data.let {
                            productImageAdapter.submitList(it.images)

                            tvDetailsProductName.text = it.title
                            rbProductRating.rating = it.rating.toFloat()

                            if (it.isFavorites)
                                ibDetailsFavourite.loadImage(com.rkhvstnv.testecommerce.core.R.drawable.ic_favourite_solid)
                            else
                                ibDetailsFavourite.loadImage(com.rkhvstnv.testecommerce.core.R.drawable.ic_favourite)
                        }
                    }
                }
            }
        }
    }


    private fun navigateToCart(){
        val uri = Uri.parse(getString(com.rkhvstnv.testecommerce.core.R.string.deep_link_cart_url))
        findNavController().navigate(uri)
    }

    private fun setupProductImagesViewPager(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            val scaleFactor = 0.85f + r * 0.14f
            page.scaleY = scaleFactor
            page.scaleX = scaleFactor
        }

        binding.vpProductImages.apply {
            adapter = productImageAdapter
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            setPageTransformer(transformer)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}