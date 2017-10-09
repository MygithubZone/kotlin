package com.raythinks.shiwen.ui.fragment

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.design.widget.TabLayout
import android.view.View
import com.huxq17.swipecardsview.SwipeCardsView
import com.mrzk.transitioncontroller.controller.animationUtils.TransitionController
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.ui.adapter.AuthorListAdapter
import com.raythinks.poesia.ui.activitys.AuthorDetialActivity
import com.raythinks.poesia.ui.viewmodel.AuthorListViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
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
        var intent = Intent(_mActivity, AuthorDetialActivity::class.java)
        intent.putExtra("author", adapter.mData!![position])
        TransitionController.getInstance().startActivity(_mActivity, intent, itemView.civ_author_header, R.id.civ_author_header)
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
    override fun initView() {
        AnimUtils.loadAmin(_mActivity, ll_tab, R.anim.fade_scape01)
        adapter = AuthorListAdapter(context = _mActivity, viewModel = viewModel, onItemClickListener = this)
        scv_author.setAdapter(adapter)
        author_times_Strs = resources.getStringArray(R.array.array_author_borntimes)
        TUtils.setTab(_mActivity, author_times_Strs, tbs_author_borntimes)
        tbs_author_borntimes.addOnTabSelectedListener(this)
        scv_author.setCardsSlideListener(this);
    }

    val currentP: Int = 1
    override fun initData() {
        viewModel.updateAuthorList(currentP, "").observe(this, Observer {
            it?.let {
                adapter = AuthorListAdapter(context = _mActivity, viewModel = viewModel, data = it.authors, onItemClickListener = this)
                scv_author.setAdapter(adapter)
                tv_curentpage.text = "${it.currentPage}/${it.sumPage}"
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_authorlist

}
