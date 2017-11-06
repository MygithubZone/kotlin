package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.net.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.finishRefershOrLoadMore
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.net.ApiAuthorPoesia
import com.raythinks.poesia.ui.adapter.PoesiaAdapter
import com.raythinks.poesia.ui.model.AuthorsItem
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel
import com.raythinks.poesia.utils.ActivityRouterUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_author_poesia.*

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/20 0020<br>.
 * 版本：1.2.0
 */
class AuthorPoesiaFragment : BaseVMFragment<AuthorDetialViewModel>(), OnRefreshListener, OnLoadmoreListener, OnItemClickListener {
    override fun onItemClick(position: Int, itemView: View) {
        ActivityRouterUtils.startPoesiaDetailActivity(context = _mActivity, typeFrom = 1, id = adapter.data[position].id, nameStr = adapter.data[position].nameStr, author = adapter.data[position].author)
    }

    lateinit var adapter: PoesiaAdapter
    override fun onLoadmore(refreshlayout: RefreshLayout?) {
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
    }

    override fun initView() {
        authorId = _mActivity.intent.getParcelableExtra<AuthorsItem>("author").id
        recyclerview.setLayoutManager(LinearLayoutManager(_mActivity))
        recyclerview.setItemAnimator(DefaultItemAnimator())
        adapter = PoesiaAdapter(this, viewModel, this)
        recyclerview.setAdapter(adapter)
        refreshLayout.setOnRefreshListener {
            isInitRefresh = true
            viewModel.updateAuthorPoeisa(1, authorId)
        }
        refreshLayout.setOnLoadmoreListener {
            isInitRefresh = false
            viewModel.updateAuthorPoeisa(currentP + 1, authorId)
        }
    }

    var authorId: Int = 0//作者id
    var currentP: Int = 1
    var sumP: Int = 1
    override fun initData() {
        stl.showLoading()
        viewModel.updateAuthorPoeisa(1, authorId).observe(this, Observer {
            var gushiwens = it?.tb_gushiwens
            currentP = it?.currentPage ?: 1
            sumP = it?.sumPage ?: 1
            if (sumP > 500) {
                sumP = 500
            }
            if (gushiwens == null || gushiwens.size == 0) {//返回列表为空
                if (it?.currentPage == 1) {//返回列表为空,且第一页
                    stl.showEmpty(ERROR_MEG_DATANULL, { initData() })
                }
            } else {//返回列表不为空
                if (it?.currentPage == 1) {//第一页
                    stl.showContent()
                }
                refreshLayout.setEnableLoadmore(currentP < sumP)
                adapter.updateData(isInitRefresh, gushiwens)
            }
            refreshLayout.finishRefershOrLoadMore(currentP == 1)
        })
        viewModel.onFinishError().observe(this, Observer {
            when (it?.fromApi) {
                ApiAuthorPoesia -> {
                    if (currentP == 1) {
                        stl.showError(it.msg, { initData() })
                    }
                    refreshLayout.finishRefershOrLoadMore(currentP == 1)
                    return@Observer
                }
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_author_poesia
}