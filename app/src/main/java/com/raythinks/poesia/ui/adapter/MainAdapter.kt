package com.raythinks.poesia.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.raythinks.poesia.R
import com.raythinks.poesia.utils.TUtils

/**
 * 文 件 名：MainPagerAdapter
 * 功    能：主界面适配器
 * 作    者：zh
 * 时    间：2016/7/11
 */

class MainAdapter(internal var mContext: Context, fm: FragmentManager, title: Array<String>) : FragmentPagerAdapter(fm) {
    var titleText: Array<String> //菜单栏Text

    init {
        titleText = mContext.resources.getStringArray(R.array.arrayt_type)
    }

    override fun getItem(position: Int): Fragment? {
        return TUtils.toMainFragment(position)
    }

    override fun getCount(): Int {
        return titleText.size
    }
}
