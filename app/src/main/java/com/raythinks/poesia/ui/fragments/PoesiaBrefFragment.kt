package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.text.Editable
import android.text.Html
import android.text.TextUtils
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.fragment_poesia_bref.*

/**
 * 功能：诗文简介<br>
 * 作者：zh<br>
 * 时间： 2017/10/11 0011<br>.
 * 版本：1.2.0
 */
class PoesiaBrefFragment : BaseVMFragment<PoesiaDetialViewModel>() {
    var mingju = ""
    override fun initView() {
        mingju = _mActivity.intent.getStringExtra("mingju")
    }

    override fun initData() {
        viewModel.getGuShiWen().observe(this, Observer {
            if (it != null) {
                if (TextUtils.isEmpty(mingju)) {

                    tv_poesia_content.text = Html.fromHtml(it!!.cont) as Editable
                } else {
                    tv_poesia_content.text = Html.fromHtml(it!!.cont.replace(mingju, "<font color= '#C68350'>${mingju}</font>")) as Editable
                }
                TUtils.copyText(_mActivity, tv_poesia_content)
                if (it!!.yizhuIspass) {
                    tv_yi.visibility = View.VISIBLE
                    tv_zhu.visibility = View.VISIBLE
                } else {
                    tv_zhu.visibility = View.VISIBLE
                    tv_yi.visibility = View.GONE
                }
                if (it!!.shangIspass) {
                    tv_shang.visibility = View.VISIBLE
                } else {
                    tv_shang.visibility = View.VISIBLE
                }
                TUtils.setFromBottomViewVisible(cv_poesia_bref, View.VISIBLE, null)
            }
        })
    }

    override fun getLayoutId() = R.layout.fragment_poesia_bref
}