package com.rkhvstnv.testecommerce.details.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rkhvstnv.testecommerce.details.ui.DetailsFragment
import com.rkhvstnv.testecommerce.details.ui.ShopFragment

internal class DetailsTabPagerAdapter(fragment: DetailsFragment, private val tabCount: Int): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment = ShopFragment()
}