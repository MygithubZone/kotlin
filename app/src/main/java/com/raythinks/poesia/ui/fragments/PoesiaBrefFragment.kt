package com.raythinks.poesia.ui.fragments

import android.arch.lifecycle.Observer
import android.text.Editable
import android.text.Html
import android.view.View
import com.kogitune.activity_transition.ActivityTransition
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMFragment
import com.raythinks.poesia.listener.CopyActionCallBack
import com.raythinks.poesia.ui.activitys.AuthorDetialActivity
import com.raythinks.poesia.ui.activitys.PoesiaDetialActivity
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
import kotlinx.android.synthetic.main.fragment_poesia_bref.*

/**
 * 功能：诗文简介<br>
 * 作者：zh<br>
 * 时间： 2017/10/11 0011<br>.
 * 版本：1.2.0
 */
class PoesiaBrefFragment : BaseVMFragment<PoesiaDetialViewModel>() {
    override fun initView() {
    }

    override fun initData() {
        viewModel.getGuShiWen().observe(this, Observer {
            it.let {
                tv_poesia_content.text = Html.fromHtml(it!!.cont) as Editable
                tv_poesia_tag.text = it!!.tag
                tv_poesia_content.clearFocus()
                tv_poesia_content.setCustomActionMenuCallBack(CopyActionCallBack())
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
            }
        })
        ActivityTransition.with(activity.intent).to(tv_poesia_content).start((_mActivity as PoesiaDetialActivity).savedInstanceState);

    }

    override fun getLayoutId() = R.layout.fragment_poesia_bref
}