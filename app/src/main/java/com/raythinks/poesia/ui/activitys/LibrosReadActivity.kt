package com.raythinks.poesia.ui.activitys

import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMActivity
import com.raythinks.poesia.ui.viewmodel.LibrosReadViewModel
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.activity_libros_read.*

/**
 * Created by zh on 2017/10/22.
 */
class LibrosReadActivity : BaseVMActivity<LibrosReadViewModel>() {
    override fun initView() {
        TUtils.initToolbarNav(toolbar, this)
    }

    override fun initData() {
    }

    override fun getLayoutId() = R.layout.activity_libros_read

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_libros_read, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_swap -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}