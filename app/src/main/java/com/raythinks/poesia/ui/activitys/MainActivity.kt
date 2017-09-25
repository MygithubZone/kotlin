package com.raythinks.poesia.ui.activitys

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.chenenyu.router.annotation.Route
import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMActivity
import com.raythinks.poesia.ui.adapter.MainAdapter
import com.raythinks.poesia.ui.anim.ZoomOutPagerAnim
import com.raythinks.poesia.ui.fragments.MainFragment
import com.raythinks.shiwen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * 功能：主界面
 * 作者：zh
 *
 */
@Route("poesia://activity/mainActivity")
class MainActivity : BaseVMActivity<MainViewModel>(), MainFragment.OnBackToFirstListener, ViewPager.OnPageChangeListener {

    override fun onBackToFirstFragment() {
    }

    lateinit var searchView: SearchView
    lateinit var typePoesiaId: Array<Int>;
    lateinit var typePoesia: Array<String>;
    override fun initView() {
        setSupportActionBar(toolbar)
        typePoesia = resources.getStringArray(R.array.arrayt_type)
        typePoesiaId = arrayOf(R.id.nav_refranes, R.id.nav_poesia, R.id.nav_author, R.id.nav_libros)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        var mainDapter = MainAdapter(mContext, supportFragmentManager, typePoesia)
        vp_maincontent.adapter = mainDapter
        vp_maincontent.setPageTransformer(true, ZoomOutPagerAnim())
        vp_maincontent.addOnPageChangeListener(this)
        onNavigationItemSelected();
    }

    override fun initData() {
        selectCurrentItem(0)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onBackPressedSupport() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedSupport()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        //找到searchView
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.getActionView() as SearchView
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * 选中分类
     */
    fun onNavigationItemSelected() {
        RxNavigationView.itemSelections(nav_view).subscribe {
            when (it.itemId) {
                R.id.nav_refranes -> {//名句
                    // Handle the camera action
                    selectCurrentItem(0)
                }
                R.id.nav_poesia -> {//诗文
                    selectCurrentItem(1)
                }
                R.id.nav_author -> {//作者
                    selectCurrentItem(2)
                }
                R.id.nav_libros -> {//古籍
                    selectCurrentItem(3)
                }
                R.id.nav_share -> {

                }
                R.id.nav_send -> {

                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }

    fun selectCurrentItem(position: Int) {
        setTitle(typePoesia[position])
        vp_maincontent.setCurrentItem(position, true)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        setTitle(typePoesia[position])
        nav_view.setCheckedItem(typePoesiaId[position])

    }

}
