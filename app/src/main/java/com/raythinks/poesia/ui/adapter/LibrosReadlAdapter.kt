package com.raythinks.poesia.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.raythinks.poesia.R
import com.raythinks.poesia.ui.fragments.AuthorBrefFragment
import com.raythinks.poesia.ui.fragments.AuthorMoreInfoFragment
import com.raythinks.poesia.ui.fragments.AuthorPoesiaFragment
import com.raythinks.poesia.ui.fragments.LibrosReadItemFragment
import com.raythinks.poesia.ui.model.BookviewsItem

/**
 * 文 件 名：AuthorDetailAdapter
 * 功    能：主界面适配器
 * 作    者：zh
 * 时    间：2016/7/11
 */

class LibrosReadlAdapter(mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var data = ArrayList<BookviewsItem>()
    override fun getItem(position: Int): Fragment? {
        var fragment = LibrosReadItemFragment()
        var bundle = Bundle()
        bundle.putString("id", data[position].id.toString())
        fragment.arguments = bundle
        return fragment
    }

    fun updateData(newData: ArrayList<BookviewsItem>) {
        this.data = newData
        notifyDataSetChanged()
    }

    override fun getCount() = data.size
}
