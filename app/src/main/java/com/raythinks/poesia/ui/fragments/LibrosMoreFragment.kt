package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.listener.OnSelectionItemClickListener
import com.raythinks.poesia.ui.activitys.LibrosReadActivity
import com.raythinks.poesia.ui.adapter.LibrosMoreAdapter
import com.raythinks.poesia.ui.viewmodel.LibrosDetailViewModel
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup
import kotlinx.android.synthetic.main.fragment_libors_more.*

/**
 * Created by zh on 2017/9/20.
 */
class LibrosMoreFragment : BaseVMFragment<LibrosDetailViewModel>(), OnSelectionItemClickListener {
    override fun onItemClick(selection: Int, position: Int, itemView: View) {
        var intent = Intent(_mActivity, LibrosReadActivity::class.java)
        intent.putExtra("booklist", adapter.headerAarry[selection])
        intent.putExtra("postion", position)
        intent.putExtra("title", _mActivity.intent.getStringExtra("nameStr"))
        startActivity(intent)
    }

    lateinit var adapter: LibrosMoreAdapter
    lateinit var gridManager: GridLayoutManager
    override fun initView() {
        gridManager = GridLayoutManager(_mActivity, 3)
        adapter = LibrosMoreAdapter(viewModel, this)
        gridManager.spanSizeLookup = SectionedSpanSizeLookup(adapter, gridManager);
        recyclerview.setLayoutManager(gridManager)
        recyclerview.adapter = adapter
        recyclerview.setItemAnimator(DefaultItemAnimator())
        recyclerview.setBackgroundColor(ContextCompat.getColor(_mActivity, R.color.white))
    }

    override fun initData() {
        viewModel.getBookViews().observe(this, Observer {
            it?.let {
                it?.bookviews.let {
                    adapter.updateData(it)
                }
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_libors_more
}