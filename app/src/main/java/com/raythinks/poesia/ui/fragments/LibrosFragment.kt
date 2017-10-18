package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kogitune.activity_transition.ActivityTransitionLauncher
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.ui.activitys.LibrosDetialActivity
import com.raythinks.poesia.ui.adapter.LibrosAdapter
import com.raythinks.poesia.ui.viewmodel.LibrosViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_libros.*
import kotlinx.android.synthetic.main.item_libros.view.*

/**
 * Created by zh on 2017/9/20.
 */
class LibrosFragment : BaseVMFragment<LibrosViewModel>(), OnRefreshListener, OnLoadmoreListener, TabLayout.OnTabSelectedListener, OnItemClickListener {
    override fun onItemClick(position: Int, itemView: View) {
        var intent = Intent(_mActivity, LibrosDetialActivity::class.java)
        intent.putExtra("id", adapter.data[position].id)
        intent.putExtra("nameStr", adapter.data[position].nameStr)
        ActivityTransitionLauncher.with(_mActivity).from(itemView.tv_libros_brief).launch(intent);
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
        adapter = LibrosAdapter(viewModel, this)
        recyclerview.setAdapter(adapter)
        libros_type_Strs = resources.getStringArray(R.array.arrayt_libros_type)
        TUtils.setTab(_mActivity, libros_type_Strs, tbs_libros_type)
        tbs_libros_type.addOnTabSelectedListener(this)
        refreshLayout.setOnRefreshListener {
            initData()
            refreshLayout.finishRefresh(2000)
        }
        refreshLayout.setOnLoadmoreListener {
            viewModel.updateLibrosList(currentP+1, "", "")
            refreshLayout.finishLoadmore(2000)
        }
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