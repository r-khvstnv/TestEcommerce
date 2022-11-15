package com.rkhvstnv.testecommerce

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetHotSalesUseCase
import com.rkhvstnv.testecommerce.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var getHotSalesUseCase: GetHotSalesUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as TestEcommerceApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO){
            when(val result = getHotSalesUseCase.invoke()){
                is MyResult.Success -> Log.i("RetrofitResponse", result.data.toString())
                is MyResult.Error -> Log.i("RetrofitResponse", result.code.toString() + result.message)
                is MyResult.Exception -> Log.i("RetrofitResponse", result.e.toString())
            }
        }

        /**NavigationView*/
        val navView: BottomNavigationView = binding.navView
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container_app) as NavHostFragment
        val navController = navHostFragment.findNavController()
        navView.setupWithNavController(navController)

        binding.mHome.setOnClickListener {
            navController.navigate(com.rkhvstnv.testecommerce.home.R.id.navigation_home)
        }
    }
}