package com.raythinks.poesia.ui.activitys

import android.graphics.Color
import android.support.v4.view.ViewPager
import android.view.Gravity
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMActivity
import com.raythinks.poesia.ui.adapter.AuthorDetailAdapter
import com.raythinks.poesia.ui.adapter.PoesiaDetailAdapter
import com.raythinks.poesia.ui.anim.ZoomOutPagerAnim
import com.raythinks.poesia.ui.viewmodel.PoesiaDetialViewModel
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.activity_poesia_detail.*

/**
 * 功能：诗文详情<br>
 * 作者：zh<br>
 * 时间： 2017/10/10 0010<br>.
 * 版本：1.2.0
 */
class PoesiaDetialActivity : BaseVMActivity<PoesiaDetialViewModel>(), ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        blpv_selecttab.selectIndex(position)
    }

    override fun initView() {
        TUtils.initToolbarNav(toolbar, this)
        toolbar_layout.setTitle("诗文详情诗文详情")
        var poesiaAdapter = PoesiaDetailAdapter(mContext, supportFragmentManager)
        vp_posia_detail.adapter = poesiaAdapter
        vp_posia_detail.setPageTransformer(true, ZoomOutPagerAnim())
//        vp_posia_detail.addOnPageChangeListener(this)
        vp_posia_detail.offscreenPageLimit = 3
        vp_posia_detail.addOnPageChangeListener(this)
    }

    override fun initData() {
    }

    override fun getLayoutId() = R.layout.activity_poesia_detail
}