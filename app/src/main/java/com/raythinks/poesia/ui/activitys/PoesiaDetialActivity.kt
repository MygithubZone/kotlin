package com.raythinks.poesia.ui.activitys

import android.support.v4.view.ViewPager
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMActivity
import com.raythinks.poesia.ui.adapter.PoesiaDetailAdapter
import com.raythinks.poesia.ui.anim.ZoomOutPagerAnim
import com.raythinks.poesia.ui.model.GushiwensItem
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
    var titleArray = ArrayList<String>()
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        blpv_selecttab.selectIndex(position)
        tv_poesia_subtitle.text = titleArray[0]
    }

    override fun initView() {
        TUtils.initToolbarNav(toolbar, this)
        var poesiaAdapter = PoesiaDetailAdapter(mContext, supportFragmentManager)
        vp_posia_detail.adapter = poesiaAdapter
        vp_posia_detail.setPageTransformer(true, ZoomOutPagerAnim())
        vp_posia_detail.offscreenPageLimit = 4
    }

    override fun initData() {
        var gushiWenItem = intent.getParcelableExtra<GushiwensItem>("poesiaItem")
        toolbar_layout.setTitle(gushiWenItem.nameStr)
        titleArray.add("${gushiWenItem.chaodai}.${gushiWenItem.author}")
        titleArray.add("翻译及注释")
        titleArray.add("个人赏析")
        titleArray.add("诗文作者")
        tv_poesia_subtitle.text = titleArray[0]
        vp_posia_detail.addOnPageChangeListener(this)
        viewModel.updatePoesiaDetial("${gushiWenItem.id}")
    }

    override fun getLayoutId() = R.layout.activity_poesia_detail
}