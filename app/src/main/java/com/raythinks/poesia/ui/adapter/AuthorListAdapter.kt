package com.raythinks.poesia.ui.adapter

import android.view.View
import com.huxq17.swipecardsview.BaseCardAdapter
import com.raythinks.poesia.R
import com.raythinks.poesia.ui.viewmodel.AuthorListViewModel
import kotlinx.android.synthetic.main.item_authorlist.view.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/21 0021<br>.
 * 版本：1.2.0
 */
class AuthorListAdapter(var viewModel: AuthorListViewModel) : BaseCardAdapter<String>() {
    var pics: Array<Int>;

    init {
        pics = arrayOf(R.mipmap.libai, R.mipmap.baijuyi, R.mipmap.dufu)
    }

    override fun getCount(): Int = 10


    override fun onBindData(position: Int, cardview: View) {
        cardview.civ_author_header.setImageResource(pics[position % 3])
    }

    override fun getCardLayoutId(): Int = R.layout.item_authorlist
}