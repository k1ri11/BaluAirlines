package com.company.baluairlines.favorite_feature.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.company.baluairlines.favorite_feature.presentation.fragments.FavoriteBookingFragment
import com.company.baluairlines.favorite_feature.presentation.fragments.FavoriteRouteFragment

class FavoriteViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return FavoriteBookingFragment()
            }
            1 -> {
                return FavoriteRouteFragment()
            }
        }
        return FavoriteBookingFragment()
    }
}