package com.raythinks.poesia.ui.activitys

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView
import com.raythinks.base.BaseActivity
import com.raythinks.poesia.R
import com.raythinks.shiwen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.support.v7.widget.SearchView
import com.raythinks.poesia.ui.fragments.LibrosFragment
import com.raythinks.poesia.ui.fragments.MainFragment
import com.raythinks.poesia.ui.fragments.PoesiaFragment
import com.raythinks.poesia.ui.fragments.RefranesFragment
import com.raythinks.shiwen.ui.fragment.AuthorListFragment
import me.yokeyword.fragmentation.SupportFragment
import java.util.ArrayList

/**
 * 功能：主界面
 * 作者：
 *
 */
class MainActivity : BaseActivity<MainViewModel>() , MainFragment.OnBackToFirstListener {
    override fun onBackToFirstFragment() {
    }

    lateinit var typePoesia: Array<String>;
    lateinit var mFragments: ArrayList<SupportFragment>
    override fun initView() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        mFragments = ArrayList<SupportFragment>()
        mFragments.add(MainFragment<RefranesFragment>())
        mFragments.add(MainFragment<PoesiaFragment>())
        mFragments.add(MainFragment<AuthorListFragment>())
        mFragments.add(MainFragment<LibrosFragment>())
        loadMultipleRootFragment(R.id.cl_content, 0, mFragments[0], mFragments[1], mFragments[2], mFragments[3])
        onNavigationItemSelected();

    }

    override fun initData() {
        typePoesia = resources.getStringArray(R.array.arrayt_type)
        setTitle(typePoesia[0])
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
                    setTitle(typePoesia[0])
                    showHideFragment(mFragments[0])
                }
                R.id.nav_poesia -> {//诗文
                    setTitle(typePoesia[1])
                    showHideFragment(mFragments[1])
                }
                R.id.nav_author -> {//作者
                    setTitle(typePoesia[2])
                    showHideFragment(mFragments[2])
                }
                R.id.nav_libros -> {//古籍
                    setTitle(typePoesia[3])
                    showHideFragment(mFragments[3])
                }
                R.id.nav_share -> {

                }
                R.id.nav_send -> {

                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }

    }
}
