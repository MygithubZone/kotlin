package com.raythinks.poesia.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.fragments.PoesiaBrefFragment

/**
 * 文 件 名：AuthorDetailAdapter
 * 功    能：诗歌详情适配器
 * 作    者：zh
 * 时    间：2016/7/11
 */

class PoesiaDetailAdapter(mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var titleText: Array<String> //菜单栏Text

    init {
        titleText = mContext.resources.getStringArray(R.array.array_author_detail_tab)
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return@getItem PoesiaBrefFragment()
            1 -> return@getItem PoesiaBrefFragment()
            2 -> return@getItem PoesiaBrefFragment()
        }
        return null
    }

    override fun getCount() = titleText.size
}