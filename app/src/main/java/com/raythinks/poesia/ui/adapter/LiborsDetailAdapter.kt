package com.raythinks.poesia.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.raythinks.libros.ui.fragments.LibrosBrefFragment

import com.raythinks.poesia.R
import com.raythinks.poesia.ui.fragments.*
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel

/**
 * 文 件 名：LiborsDetailAdapter
 * 功    能：古籍详情面适配器
 * 作    者：zh
 * 时    间：2016/7/11
 */

class LiborsDetailAdapter(mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return@getItem LibrosBrefFragment()
            1 -> return@getItem LibrosMoreFragment()
        }
        return null
    }

    override fun getCount() = 2
}
