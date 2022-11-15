package com.rkhvstnv.testecommerce.details

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

internal class DetailsTabPagerAdapter(fragment: DetailsFragment, private val tabCount: Int): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment = ShopFragment()
}