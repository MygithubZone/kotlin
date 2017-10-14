package com.raythinks.poesia.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.raythinks.poesia.ui.fragments.*
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel

/**
 * 文 件 名：AuthorDetailAdapter
 * 功    能：诗歌详情适配器
 * 作    者：zh
 * 时    间：2016/7/11
 */

class PoesiaDetailAdapter(mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return@getItem PoesiaBrefFragment()
            1 -> return@getItem PoesiaTranslateFragment()
            2 -> return@getItem PoesiaShangfFragment()
            3 -> return@getItem PoesiaAuthorFragment()
        }
        return null
    }

    override fun getCount() = 4
}