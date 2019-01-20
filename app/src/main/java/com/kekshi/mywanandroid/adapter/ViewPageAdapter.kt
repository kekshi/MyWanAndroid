package com.kekshi.mywanandroid.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kekshi.library.base.BaseCompatFragment
import com.kekshi.mywanandroid.Constants.TITLE_FRAGMENTS

class ViewPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    var fragment = mutableListOf<BaseCompatFragment>()

    override fun getItem(p0: Int): Fragment {
        return fragment[p0]
    }

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TITLE_FRAGMENTS[position]
    }

    fun setFragments(fragments: MutableList<BaseCompatFragment>) {
        this.fragment = fragments
    }
}