package com.raythinks.shiwen.ui.fragment

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.huxq17.swipecardsview.SwipeCardsView
import com.mrzk.transitioncontroller.controller.animationUtils.TransitionController
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.ui.adapter.AuthorListAdapter
import com.raythinks.poesia.ui.activitys.AuthorDetialActivity
import com.raythinks.poesia.ui.adapter.AuthorAdapter
import com.raythinks.poesia.ui.model.AuthorListMoel
import com.raythinks.poesia.ui.viewmodel.AuthorListViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.fragment_author.*
import kotlinx.android.synthetic.main.fragment_authorlist.*
import kotlinx.android.synthetic.main.item_authorlist.view.*


/**
 * 功能：作者列表<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/9/19 0019<br></br>.
 * 版本：1.2.0
 */

class AuthorListFragment : BaseVMFragment<AuthorListViewModel>(), TabLayout.OnTabSelectedListener, SwipeCardsView.CardsSlideListener, OnItemClickListener {
    override fun onItemClick(position: Int, itemView: View) {//跳转到详情
//        var intent = Intent(_mActivity, AuthorDetialActivity::class.java)
//        intent.putExtra("author", adapter.mData!![position])
//        TransitionController.getInstance().startActivity(_mActivity, intent, itemView.civ_author_header, R.id.civ_author_header)
    }

    override fun onCardVanish(index: Int, type: SwipeCardsView.SlideType?) {
        when (type) {
            SwipeCardsView.SlideType.LEFT -> TUtils.showToast("左边" + index)
            SwipeCardsView.SlideType.RIGHT -> TUtils.showToast("右边" + index)
        }
    }

    override fun onItemClick(cardImageView: View?, index: Int) {

    }

    override fun onShow(index: Int) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
    }

    lateinit var author_times_Strs: Array<String>
    lateinit var adapter: AuthorListAdapter
    lateinit var authorAdapter: AuthorAdapter
    var isShowGrid = true
    override fun initView() {
        if (isShowGrid) {
            initGridView()
        } else {
            initCardView()
        }
    }

    fun initCardView() {
        initTab(ll_tab, tbs_borntimes)
        adapter = AuthorListAdapter(context = _mActivity, viewModel = viewModel, onItemClickListener = this)
        scv_author.setAdapter(adapter)
        scv_author.setCardsSlideListener(this);
    }

    fun initTab(ll_tab: View, tbs_author_borntimes: TabLayout) {
        AnimUtils.loadAmin(_mActivity, ll_tab, R.anim.fade_scape01)
        author_times_Strs = resources.getStringArray(R.array.array_author_borntimes)
        TUtils.setTab(_mActivity, author_times_Strs, tbs_author_borntimes)
        tbs_author_borntimes.addOnTabSelectedListener(this)
    }

    fun initGridView() {
        initTab(ll_author_tab, tbs_author_borntimes)
        authorAdapter = AuthorAdapter(viewModel, this)
        var gridManager = GridLayoutManager(_mActivity, 2)
        recyclerview.setLayoutManager(gridManager)
        recyclerview.adapter = authorAdapter
        recyclerview.setItemAnimator(DefaultItemAnimator())
    }

    val currentP: Int = 1
    override fun initData() {
        viewModel.updateAuthorList(currentP, "").observe(this, Observer {
            it?.let {
                if (isShowGrid) {
                    updateGridView(it)
                } else {
                    updateCardView(it)
                }


            }
        })
    }

    override fun getLayoutId(): Int {
        if (isShowGrid) {
            return R.layout.fragment_author
        } else {
            return R.layout.fragment_authorlist
        }
    }

    fun updateCardView(it: AuthorListMoel) {
        adapter = AuthorListAdapter(context = _mActivity, viewModel = viewModel, data = it.authors, onItemClickListener = this)
        scv_author.setAdapter(adapter)
        tv_curentpage.text = "${it.currentPage}/${it.sumPage}"
    }

    fun updateGridView(item: AuthorListMoel) {
        item.authors?.let {
            authorAdapter.updateData(item.currentPage == 1, it)
        }

    }
}
