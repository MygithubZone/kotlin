package com.raythinks.poesia.ui.activitys

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.widget.TextView
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMActivity
import com.raythinks.poesia.ui.adapter.AuthorDetailAdapter
import com.raythinks.poesia.ui.adapter.LiborsDetailAdapter
import com.raythinks.poesia.ui.anim.ZoomOutPagerAnim
import com.raythinks.poesia.ui.model.AuthorsItem
import com.raythinks.poesia.ui.viewmodel.LibrosDetailViewModel
import com.raythinks.poesia.utils.AnimUtils
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.activity_libros_detail.*

/**
 * 功能：古文详情<br>
 * 作者：zh<br>
 * 时间： 2017/9/26 0026<br>.
 * 版本：1.2.0
 */
class LibrosDetialActivity : BaseVMActivity<LibrosDetailViewModel>(), ViewPager.OnPageChangeListener {
    var savedInstanceState: Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

    lateinit var array_libros_detail_tab: Array<String>
    override fun initView() {
        TUtils.initToolbarNav(toolbar, this)
        array_libros_detail_tab = resources.getStringArray(R.array.array_libros_detail_tab)
        var librosAdapter = LiborsDetailAdapter(mContext, supportFragmentManager)
        vp_libros_detail.adapter = librosAdapter
        vp_libros_detail.setPageTransformer(true, ZoomOutPagerAnim())
        vp_libros_detail.addOnPageChangeListener(this)
        vp_libros_detail.offscreenPageLimit = 2
        tbs_libros_detail.setupWithViewPager(vp_libros_detail)
        TUtils.setTab(this, array_libros_detail_tab, tbs_libros_detail)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.act_exit_fade)
    }

    override fun initData() {
        setTitle(intent.getStringExtra("nameStr"))
        viewModel.updateLiborsDetial("${intent.getIntExtra("id", 0)}")
    }

    override fun getLayoutId(): Int = R.layout.activity_libros_detail
}