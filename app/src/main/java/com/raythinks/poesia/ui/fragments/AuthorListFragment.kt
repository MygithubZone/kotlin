package com.raythinks.shiwen.ui.fragment

import android.support.design.widget.TabLayout
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.adapter.AuthorListAdapter
import com.raythinks.poesia.ui.viewmodel.AuthorListViewModel
import kotlinx.android.synthetic.main.fragment_authorlist.*
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.tab_textview_style.*
import kotlinx.android.synthetic.main.tab_textview_style.view.*


/**
 * 功能：作者列表<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/9/19 0019<br></br>.
 * 版本：1.2.0
 */

class AuthorListFragment : BaseVMFragment<AuthorListViewModel>(), TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
    }

    lateinit var author_times_Strs: Array<String>
    lateinit var adapter: AuthorListAdapter
    override fun initView() {
        adapter = AuthorListAdapter(_mActivity,viewModel)
        scv_author.setAdapter(adapter)
        author_times_Strs = resources.getStringArray(R.array.array_author_borntimes)
        TUtils.setTab(_mActivity,author_times_Strs,tbs_author_borntimes)
        tbs_author_borntimes.addOnTabSelectedListener(this)
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_authorlist

}
