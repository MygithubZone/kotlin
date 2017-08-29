package com.raythinks.kotlin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.chenenyu.router.annotation.Route
import kotlinx.android.synthetic.main.activity_main.*
import com.raythinks.kotlin.base.BaseActivity
import com.raythinks.kotlin.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel>(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(getString(R.string.title_home) + "${getText()}")
                return@onNavigationItemSelected true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@onNavigationItemSelected true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@onNavigationItemSelected true
            }
        }
        return@onNavigationItemSelected false
    }

    var list = { item: MenuItem ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(getString(R.string.title_home) + "${getText()}")
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
            }
        }
    }

    fun getText(): String {
        return "aaa"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
