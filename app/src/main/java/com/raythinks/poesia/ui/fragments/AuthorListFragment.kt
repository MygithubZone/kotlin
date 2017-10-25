package com.raythinks.shiwen.ui.fragment

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kogitune.activity_transition.ActivityTransitionLauncher
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.finishRefershOrLoadMore
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.net.ApiAuthorList
import com.raythinks.poesia.ui.activitys.AuthorDetialActivity
import com.raythinks.poesia.ui.adapter.AuthorAdapter
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
import com.raythinks.shiwen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_author.*
import kotlinx.android.synthetic.main.item_author.view.*


/**
 * 功能：作者列表<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/9/19 0019<br></br>.
 * 版本：1.2.0
 */

class AuthorListFragment : BaseVMFragment<MainViewModel>(), TabLayout.OnTabSelectedListener, OnItemClickListener {
    override fun onItemClick(position: Int, itemView: View) {//跳转到详情
        var intent = Intent(_mActivity, AuthorDetialActivity::class.java)
        intent.putExtra("author", authorAdapter.data!![position])
        ActivityTransitionLauncher.with(_mActivity).from(itemView.civ_author_header).launch(intent);
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        c = author_times_Strs[tab!!.position]
        authorAdapter.clearData()
        currentP = 1
        sumP = 1
        stl.showLoading()
        viewModel.updateAuthorList(1, c)
    }

    lateinit var author_times_Strs: Array<String>
    lateinit var authorAdapter: AuthorAdapter
    override fun initView() {
        initTab(ll_author_tab, tbs_author_borntimes)
        authorAdapter = AuthorAdapter(viewModel, this)
        var gridManager = LinearLayoutManager(_mActivity)
        recyclerview.setLayoutManager(gridManager)
        recyclerview.adapter = authorAdapter
        recyclerview.setItemAnimator(DefaultItemAnimator())
        refreshLayout.setOnRefreshListener {
            isInitRefresh = true
            viewModel.updateAuthorList(1, c)
        }
        refreshLayout.setOnLoadmoreListener {
            isInitRefresh = false
            viewModel.updateAuthorList(currentP + 1, c)
        }
    }

    fun initTab(ll_tab: View, tbs_author_borntimes: TabLayout) {
        AnimUtils.loadAmin(_mActivity, ll_tab, R.anim.fade_scape01)
        author_times_Strs = resources.getStringArray(R.array.array_author_borntimes)
        TUtils.setTab(_mActivity, author_times_Strs, tbs_author_borntimes)
        tbs_author_borntimes.addOnTabSelectedListener(this)
    }

    var c = ""
    var currentP: Int = 1
    var sumP: Int = 1
    override fun initData() {
        stl.showLoading()
        viewModel.updateAuthorList(currentP, c).observe(this, Observer {
            var authors = it?.authors
            currentP = it?.currentPage ?: 1
            sumP = it?.sumPage ?: 1
            if (authors == null || authors.size == 0) {//返回列表为空
                if (it?.currentPage == 1) {//返回列表为空,且第一页
                    stl.showEmpty(ERROR_MEG_DATANULL, { initData() })
                }
            } else {//返回列表不为空
                if (it?.currentPage == 1) {//第一页
                    stl.showContent()
                }
                refreshLayout.setEnableLoadmore(currentP < sumP)
                authorAdapter.updateData(isInitRefresh, authors)
            }
            refreshLayout.finishRefershOrLoadMore(currentP == 1)
        })
        viewModel.onFinishError().observe(this, Observer {
            when (it?.fromApi) {
                ApiAuthorList -> {
                    TUtils.showToast(it?.msg ?: "aaaa")
                    if (currentP == 1) {
                        stl.showError(it.msg, { initData() })
                    }
                    refreshLayout.finishRefershOrLoadMore(currentP == 1)
                    return@Observer
                }
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_author
    }
}
