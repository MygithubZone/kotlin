package com.raythinks.poesia.ui.activitys

import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMActivity
import com.raythinks.poesia.ui.adapter.LibrosReadlAdapter
import com.raythinks.poesia.ui.model.BookviewsItem
import com.raythinks.poesia.ui.viewmodel.LibrosReadViewModel
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.activity_libros_read.*

/**
 * Created by zh on 2017/10/22.
 */
class LibrosReadActivity : BaseVMActivity<LibrosReadViewModel>(), ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        currentP = position
        setShowYY(adapter.data[position].nameStr)

    }

    private fun setShowYY(nameStr: String) {
        when (showType) {
            0 -> {
                tv_libros_subtitle.text = "${nameStr}(原文)"
                return
            }
            1 -> {
                tv_libros_subtitle.text = "${nameStr}(译文)"
                return
            }
            2 -> {
                tv_libros_subtitle.text = "${nameStr}(原译文)"
                return
            }
        }
    }

    override fun initView() {
        TUtils.initToolbarNav(toolbar, this)
    }

    var showType = 0;
    var currentP = 0;
    lateinit var adapter: LibrosReadlAdapter
    override fun initData() {
        currentP = intent.getIntExtra("postion", 0)
        adapter = LibrosReadlAdapter(this, supportFragmentManager)
        vp_libros_read.adapter = adapter
        adapter.updateData(intent.getParcelableArrayListExtra<BookviewsItem>("booklist"))
        vp_libros_read.addOnPageChangeListener(this)
        vp_libros_read.setCurrentItem(currentP, true)
        setShowYY(adapter.data[currentP].nameStr)
        ic_arrow_right.setOnClickListener {
            if (currentP < adapter.data.size - 1) {
                currentP = currentP + 1
                vp_libros_read.setCurrentItem(currentP, true)
            } else {
                TUtils.showToast("没有下一页了")
            }
        }
        ic_arrow_left.setOnClickListener {
            if (currentP > 0) {
                currentP = currentP - 1
                vp_libros_read.setCurrentItem(currentP, true)
            } else {
                TUtils.showToast("没有上一页了")
            }
        }
        var fenlei = adapter.data[0].fenlei
        if (TextUtils.isEmpty(fenlei)) {
            fenlei = ""
        } else {
            fenlei = "(${fenlei})"
        }
        setTitle(intent.getStringExtra("title") + fenlei)
        vp_libros_read.offscreenPageLimit=adapter.data.size
    }

    override fun getLayoutId() = R.layout.activity_libros_read

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_libros_read, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_swap -> {
                showType=(showType+1)%3
                viewModel.setShowType(showType)
                setShowYY(adapter.data[currentP].nameStr)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}