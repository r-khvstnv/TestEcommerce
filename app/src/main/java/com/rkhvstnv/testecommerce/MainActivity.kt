package com.rkhvstnv.testecommerce

import android.os.Bundle
import android.util.Log
import android.view.View
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


    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as TestEcommerceApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /**NavigationView*/
        val navView: BottomNavigationView = binding.navView
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container_app) as NavHostFragment
        val navController = navHostFragment.findNavController()
        navView.setupWithNavController(navController)

        binding.mHome.setOnClickListener {
            navController.navigate(com.rkhvstnv.testecommerce.home.R.id.navigation_home)
        }

        navController.addOnDestinationChangedListener{
            _, destination, _ ->
            when(destination.id){
                com.rkhvstnv.testecommerce.details.R.id.detailsFragment -> hideNavView()
                com.rkhvstnv.testecommerce.cart.R.id.cartFragment -> hideNavView()
                else -> showNavView()
            }
        }
    }

    private fun showNavView(){
        binding.bottomViewLayout.visibility = View.VISIBLE
    }

    private fun hideNavView(){
        binding.bottomViewLayout.visibility = View.GONE
    }
}