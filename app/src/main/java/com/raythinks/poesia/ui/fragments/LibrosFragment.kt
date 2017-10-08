package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.design.widget.TabLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.ui.adapter.LibrosAdapter
import com.raythinks.poesia.ui.viewmodel.LibrosViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_libros.*

/**
 * Created by zh on 2017/9/20.
 */
class LibrosFragment : BaseVMFragment<LibrosViewModel>(), OnRefreshListener, OnLoadmoreListener, TabLayout.OnTabSelectedListener, OnItemClickListener {
    override fun onItemClick(position: Int, itemView: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
    }

    lateinit var adapter: LibrosAdapter
    override fun onLoadmore(refreshlayout: RefreshLayout?) {
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
    }

    lateinit var libros_type_Strs: Array<String>
    override fun initView() {
        AnimUtils.loadAmin(_mActivity, ll_tab, R.anim.fade_scape01)
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = LibrosAdapter(viewModel)
//        adapter.onItemListener = this
        recyclerview.setAdapter(adapter)
        libros_type_Strs = resources.getStringArray(R.array.arrayt_libros_type)
        TUtils.setTab(_mActivity, libros_type_Strs, tbs_libros_type)
        tbs_libros_type.addOnTabSelectedListener(this)
    }
    val currentP: Int = 1
    override fun initData() {
        viewModel.updateLibrosList(currentP, "", "").observe(this, Observer {
            it?.let {
                adapter.updateData(currentP == 1, it)
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_libros
}