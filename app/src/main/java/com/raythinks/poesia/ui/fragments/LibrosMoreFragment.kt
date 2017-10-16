package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.LibrosAdapter
import com.raythinks.poesia.ui.adapter.LibrosMoreAdapter
import com.raythinks.poesia.ui.viewmodel.LibrosDetailViewModel
import com.raythinks.poesia.ui.viewmodel.LibrosViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by zh on 2017/9/20.
 */
class LibrosMoreFragment : BaseVMFragment<LibrosDetailViewModel>() {

    lateinit var adapter: LibrosMoreAdapter
    override fun initView() {
        recyclerview.setLayoutManager(GridLayoutManager(_mActivity,3))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = LibrosMoreAdapter(viewModel)
        recyclerview.setAdapter(adapter)
        recyclerview.setBackgroundColor(ContextCompat.getColor(_mActivity,R.color.white))
        recyclerview.addItemDecoration(StickyRecyclerHeadersDecoration(adapter));
    }
    override fun initData() {
        viewModel.getBookViews().observe(this, Observer {
            it?.let {
                it.bookviews.let {
                    adapter.updateData(it)
                }
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_list
}