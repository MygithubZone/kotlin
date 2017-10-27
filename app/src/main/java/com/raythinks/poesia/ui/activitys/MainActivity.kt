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
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.raythinks.poesia.listener.OnItemClickListener
import com.raythinks.poesia.listener.OnSelectionItemClickListener
import com.raythinks.poesia.ui.adapter.SearchAdapter
import com.raythinks.poesia.ui.adapter.SearchHistoryAdapter
import com.raythinks.poesia.ui.model.PageModel
import com.raythinks.poesia.ui.widget.EditDialog
import com.raythinks.poesia.utils.*
import kotlinx.android.synthetic.main.empty_view.view.*
import kotlinx.android.synthetic.main.search_content.*


/**
 * 功能：主界面
 * 作者：zh
 *
 */
@Route("poesia://activity/mainActivity")
class MainActivity : BaseVMActivity<MainViewModel>(), MainFragment.OnBackToFirstListener, ViewPager.OnPageChangeListener, MenuItem.OnActionExpandListener, SearchView.OnQueryTextListener, OnSelectionItemClickListener, OnItemClickListener, EditDialog.onDlialogClickLisenter {

    override fun onClickEidtDialog(dialog: EditDialog, type: Int, content: String) {
        if (type == 1) {
            if (!TextUtils.isEmpty(content) && (content.toInt() <= pageModel?.sumP ?: 100000 && content.toInt() >=1)) {
                viewModel.skipPage(content.toInt(), pageModel?.fromPage ?: 0)
                dialog.dismiss()
            }
        } else {
            dialog.dismiss()
        }
    }

    override fun onItemClick(position: Int, itemView: View) {
        searchView?.setQuery(searchHistoryAdapter.data[position], false)
    }

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
        SearchHistoryUtils.saveSearchHistory(this, searchAdapter!!.headerAarry[selection][position])
        searchItem.collapseActionView()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false  //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (TextUtils.isEmpty(newText)) {
            showHistotryView()
        } else {
            viewModel.searchPoesia(newText!!).observe(this, Observer {

                if (!(searchAdapter?.updateData(newText, it!!) ?: false)) {
                    stl_search_result.showEmpty("未搜索到您要的内容哟", { initData() })
                    stl_search_result.btn_empty_retry.visibility = View.GONE
                } else {
                    stl_search_result.showContent()
                    stl_search_history.visibility = View.GONE
                    stl_search_result.visibility = View.VISIBLE
                }
            })
        }
        return false//To change body of created functions use File | Settings | File Templates.
    }

    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        showHistotryView()
        TUtils.setBottomViewVisible(fl_search, View.VISIBLE, null)
        return true
    }

    fun showHistotryView() {
        searchAdapter?.clearData()
        stl_search_history.visibility = View.VISIBLE
        stl_search_result.visibility = View.GONE
        var list = SearchHistoryUtils.initData(this)
        if (list.isEmpty()) {
            stl_search_history.showEmpty("暂无历史搜索记录哟", { initData() })
            stl_search_history.btn_empty_retry.visibility = View.GONE
        } else {
            stl_search_history.showContent()
            searchHistoryAdapter.updateData(list)
        }
    }

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        TUtils.setBottomViewVisible(fl_search, View.GONE, null)
        return true
    }

    override fun onBackToFirstFragment() {
    }

    lateinit var searchHistoryAdapter: SearchHistoryAdapter
    lateinit var typePoesiaId: Array<Int>
    lateinit var typePoesia: Array<String>
    var editDialog: EditDialog? = null
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
        onNavigationItemSelected();
        fab.setOnClickListener {
            if (pageModel?.currP != 0 && pageModel?.sumP != 0) {
                editDialog = DialogUtils.showEidtDialog(this, false, "提示", "您要跳转到", "${pageModel?.currP}/共${pageModel?.sumP}页", "跳转", "取消", this, inputTextLis())
            } else {
                TUtils.showToast("请刷新界面数据再跳转")
            }
        }
        fab_up.setOnClickListener {
            viewModel.skipPage(-1, pageModel?.fromPage ?: 0)
        }
    }

    inner class inputTextLis : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (TextUtils.isEmpty(p0)) {
                editDialog?.til_dialog_content?.setError("")
//                editDialog?.til_dialog_content?.isErrorEnabled=false
            } else {
                if (p0.toString().toInt() > pageModel?.sumP ?: 0 || p0.toString().toInt() < 1) {
//                    editDialog?.til_dialog_content?.isErrorEnabled=true
                    editDialog?.til_dialog_content?.setError("您输入的页码不符合规范")
                } else {
                    editDialog?.til_dialog_content?.setError("")
//                    editDialog?.til_dialog_content?.isErrorEnabled=false
                }
            }


        }

    }

    override fun isSetStatusBar() = false
    var pageModel: PageModel? = null
    override fun initData() {
        viewModel.getCurrentPage().observe(this, Observer {
            pageModel = it!!
            if (pageModel?.fromPage == 3) {
                if (!TextUtils.isEmpty(pageModel?.typeKey)) {
                    typePoesia[3] = typePoesia[3].split("(")[0] + "(${pageModel?.typeKey})"
                } else {
                    typePoesia[3] = typePoesia[3].split("(")[0]
                }
                setTitle(typePoesia[3])
            }
        })
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

    lateinit var searchView: SearchView
    lateinit var searchItem: MenuItem
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        //找到searchView
        searchItem = menu.findItem(R.id.action_search)
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
        searchHistoryAdapter = SearchHistoryAdapter(viewModel, this)
        recyclerview_search_history.layoutManager = LinearLayoutManager(this)
        recyclerview_search_history.setItemAnimator(DefaultItemAnimator())
        recyclerview_search_history.adapter = searchHistoryAdapter
        ll_clear_search.setOnClickListener {
            SearchHistoryUtils.clear(this)
            showHistotryView()
        }
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
