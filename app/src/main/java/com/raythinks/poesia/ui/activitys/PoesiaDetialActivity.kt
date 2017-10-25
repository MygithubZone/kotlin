package com.raythinks.poesia.ui.activitys

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMActivity
import com.raythinks.poesia.base.finishRefershOrLoadMore
import com.raythinks.poesia.net.ApiPoesiaDetail
import com.raythinks.poesia.net.ApiPoesiaDetailByJu
import com.raythinks.poesia.net.ApiPoesiaList
import com.raythinks.poesia.ui.adapter.PoesiaDetailAdapter
import com.raythinks.poesia.ui.anim.ZoomOutPagerAnim
import com.raythinks.poesia.ui.model.GushiwensItem
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.activity_poesia_detail.*
import kotlinx.android.synthetic.main.fragment_poesia.*

/**
 * 功能：诗文详情<br>
 * 作者：zh<br>
 * 时间： 2017/10/10 0010<br>.
 * 版本：1.2.0
 */
class PoesiaDetialActivity : BaseVMActivity<PoesiaDetialViewModel>(), ViewPager.OnPageChangeListener {
    var savedInstanceState: Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
    }

    var titleArray = ArrayList<String>()
    var currP = 0
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.act_exit_fade)
    }

    override fun onPageSelected(position: Int) {
        blpv_selecttab.selectIndex(position)
        tv_poesia_subtitle.text = titleArray[position]
        currP = position
    }

    override fun initView() {
        TUtils.initToolbarNav(toolbar, this)
        var poesiaAdapter = PoesiaDetailAdapter(mContext, supportFragmentManager)
        vp_posia_detail.adapter = poesiaAdapter
        vp_posia_detail.setPageTransformer(true, ZoomOutPagerAnim())
        vp_posia_detail.offscreenPageLimit = 4
    }

    override fun initData() {
        toolbar_layout.setTitle(intent.getStringExtra("nameStr"))
        titleArray.add("${"作者"}.${intent.getStringExtra("author")}")
        titleArray.add("翻译及注释")
        titleArray.add("背景及赏析")
        titleArray.add("${intent.getStringExtra("author")}简介")
        tv_poesia_subtitle.text = titleArray[0]
        vp_posia_detail.addOnPageChangeListener(this)
        when (intent.getIntExtra("typeFrom", 1)) {
            1 -> {
                viewModel.updatePoesiaDetial("${intent.getIntExtra("id", 0)}")
            }
            2 -> {
                viewModel.updatePoesiaDetialByJu("${intent.getIntExtra("id", 0)}")
            }
        }
        viewModel.getGuShiWen().observe(this, Observer {
            titleArray.set(0, "${it?.chaodai}.${intent.getStringExtra("author")}")
            if (currP == 0) {
                onPageSelected(0)
            }
        })
        viewModel.onFinishError().observe(this, Observer {
            when (it?.fromApi) {
                ApiPoesiaDetail -> {
                    stl.showError(it.msg, { initData() })
                    return@Observer
                }
                ApiPoesiaDetailByJu -> {
                    stl.showError(it.msg, { initData() })
                    return@Observer
                }
            }
        })
    }

    override fun getLayoutId() = R.layout.activity_poesia_detail
}