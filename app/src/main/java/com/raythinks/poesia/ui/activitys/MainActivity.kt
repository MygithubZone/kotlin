package com.raythinks.poesia.ui.activitys

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
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
import android.widget.Toast
import android.support.v4.view.MenuItemCompat.setOnActionExpandListener
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.raythinks.poesia.listener.OnSelectionItemClickListener
import com.raythinks.poesia.ui.adapter.SearchAdapter
import com.raythinks.poesia.utils.ActivityRouterUtils
import com.raythinks.poesia.utils.TUtils
import kotlinx.android.synthetic.main.empty_view.view.*
import kotlinx.android.synthetic.main.search_content.*


/**
 * 功能：主界面
 * 作者：zh
 *
 */
@Route("poesia://activity/mainActivity")
class MainActivity : BaseVMActivity<MainViewModel>(), MainFragment.OnBackToFirstListener, ViewPager.OnPageChangeListener, MenuItem.OnActionExpandListener, SearchView.OnQueryTextListener, OnSelectionItemClickListener {
    override fun onItemClick(selection: Int, position: Int, itemView: View) {
        var type = searchAdapter!!.headerIndexs[selection]
        when (type) {
            "作者" -> {

            }
            "诗文" -> {
                ActivityRouterUtils.startPoesiaDetailActivity(context = this, typeFrom = 1, id = searchAdapter!!.data!!.gushiwens[position].id, nameStr = searchAdapter!!.data!!.gushiwens[position]!!.nameStr, author = searchAdapter!!.data!!.gushiwens[position].author)
            }
            "古籍" -> {
                ActivityRouterUtils.startLibrosDetailActivity(this, searchAdapter!!.data!!.books[position].id, searchAdapter!!.data!!.books[position].nameStr)
            }
            "类型" -> {
                vp_maincontent.setCurrentItem(0)
                viewModel.setSearchPoesiaType(searchAdapter!!.headerAarry.get(selection).get(position))
            }
            "名句" -> {
                ActivityRouterUtils.startPoesiaDetailActivity(context = this, typeFrom = 2, id = searchAdapter!!.data!!.mingjus[position].id, nameStr = "", author = "", mingju = searchAdapter!!.data!!.mingjus[position]!!.nameStr)
            }
        }
        searchView.onActionViewCollapsed()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false  //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (TextUtils.isEmpty(newText)) {
            stl_search_result.showContent()
            searchAdapter?.clearData()
        } else {
            viewModel.searchPoesia(newText!!).observe(this, Observer {

                if (!(searchAdapter?.updateData(newText, it!!) ?: false)) {
                    stl_search_result.showEmpty("未搜索到您要的内容哟", { initData() })
                    stl_search_result.btn_empty_retry.visibility = View.GONE
                }
            })
        }
        return false//To change body of created functions use File | Settings | File Templates.
    }

    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        searchAdapter?.clearData()
        TUtils.setBottomViewVisible(fl_search, View.VISIBLE, null)
        return true
    }

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        TUtils.setBottomViewVisible(fl_search, View.GONE, null)

        return true
    }

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
//        vp_maincontent.setPageTransformer(true, ZoomOutPagerAnim())
        vp_maincontent.addOnPageChangeListener(this)
        vp_maincontent.offscreenPageLimit = 4
        fab.setOnClickListener { }
        onNavigationItemSelected();
    }

    override fun isSetStatusBar() = false
    override fun initData() {
        selectCurrentItem(0)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

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
        searchView = searchItem.getActionView() as SearchView
        searchItem.setOnActionExpandListener(this@MainActivity)
        initSearch(searchView);
        return super.onCreateOptionsMenu(menu)
    }

    var searchAdapter: SearchAdapter? = null
    private fun initSearch(searchView: SearchView) {
        searchView.queryHint = "诗歌古籍等关键字"
        searchView.setOnQueryTextListener(this)
        recyclerview_search_result.setLayoutManager(LinearLayoutManager(this))
        recyclerview_search_result.setItemAnimator(DefaultItemAnimator())
        searchAdapter = SearchAdapter(viewModel, this)
        recyclerview_search_result.setAdapter(searchAdapter)
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
                R.id.nav_poesia -> {//诗文
                    // Handle the camera action
                    selectCurrentItem(0)
                }
                R.id.nav_refranes -> {//名句
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
