package com.raythinks.poesia.ui.activitys

import android.support.v4.view.ViewPager
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMActivity
import com.raythinks.poesia.ui.adapter.AuthorDetailAdapter
import com.raythinks.poesia.ui.anim.ZoomOutPagerAnim
import com.raythinks.poesia.ui.model.AuthorsItem
import com.raythinks.poesia.ui.viewmodel.AuthorDetialViewModel
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.activity_author_detail.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/26 0026<br>.
 * 版本：1.2.0
 */
class AuthorDetialActivity : BaseVMActivity<AuthorDetialViewModel>(), ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

    lateinit var array_author_detail_tab: Array<String>
    lateinit var authorItem: AuthorsItem
    override fun initView() {
        TUtils.initToolbarNav(toolbar, this)
        array_author_detail_tab = resources.getStringArray(R.array.array_author_detail_tab)
        var authorAdapter = AuthorDetailAdapter(mContext, supportFragmentManager)
        vp_author_detail.adapter = authorAdapter
        vp_author_detail.setPageTransformer(true, ZoomOutPagerAnim())
        vp_author_detail.addOnPageChangeListener(this)
        vp_author_detail.offscreenPageLimit = 3
        tbs_author_detail.setupWithViewPager(vp_author_detail)
        TUtils.setTab(this, array_author_detail_tab, tbs_author_detail)
    }
    override fun initData() {
        authorItem = intent.getParcelableExtra<AuthorsItem>("author")
//        ImageUtils.loadPoesiaPic(this, authorItem.pic, civ_author_header)
        toolbar.setTitle(authorItem.nameStr)
        viewModel.updateAuthorMore("${authorItem.id}")
    }
    override fun getLayoutId(): Int = R.layout.activity_author_detail
}