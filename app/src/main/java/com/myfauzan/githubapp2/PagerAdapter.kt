package com.myfauzan.githubapp2

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity){

    var username: String? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        when (position) {
            0-> fragment = FollowersFragment().newInstance(username)
            1-> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }

}