package com.raythinks.poesia.listener

import android.support.v7.widget.RecyclerView

/**
 * 功能：<br></br>
 * 作者：zh<br></br>
 * 时间： 2017/10/27 0027<br></br>.
 * 版本：1.2.0
 */

class RecycleScrollCallback : RecyclerView.OnScrollListener(){
    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

    }
}