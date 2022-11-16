package com.rkhvstnv.testecommerce.details

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.navGraphViewModels
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.details.databinding.FragmentShopBinding
import com.rkhvstnv.testecommerce.details.di.DetailsComponentViewModule
import com.rkhvstnv.testecommerce.utils.formatToTwoDecimals
import com.rkhvstnv.testecommerce.utils.setBackgroundTintColor
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.phone.observe(viewLifecycleOwner){
            result ->
            if (result is MyResult.Success){
                with(binding){
                    result.data.let {
                        tvShopCpuName.text = it.cpu
                        tvShopCameraName.text = it.camera
                        tvShopRamSize.text = it.ram
                        tvShopSdCapacity.text = it.sd
                        tvShopCost.text =
                            getString(com.rkhvstnv.testecommerce.utils.R.string.currency_us) + it.price.formatToTwoDecimals()

                        if (it.color.size >= 2){
                            chBoxShopColor1.setBackgroundTintColor(it.color[0])
                            chBoxShopColor2.setBackgroundTintColor(it.color[1])
                        }

                        if (it.capacity.size >= 2){
                            chBoxShopCapacity1.text = it.capacity[0] + Char(32) + getString(R.string.size_gb)
                            chBoxShopCapacity2.text = it.capacity[1] + Char(32) + getString(R.string.size_gb)
                        }
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}