package com.raythinks.poesia.utils

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import com.kogitune.activity_transition.ActivityTransitionLauncher
import com.raythinks.poesia.ui.activitys.LibrosDetialActivity
import com.raythinks.poesia.ui.activitys.PoesiaDetialActivity
import kotlinx.android.synthetic.main.item_libros.view.*

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/10/25 0025<br>.
 * 版本：1.2.0
 */
object ActivityRouterUtils {
    /**
     * 跳转到诗文详情
     */
    fun startPoesiaDetailActivity(context: Context, typeFrom: Int = 1, id: Int?, nameStr: String, author: String? = "", mingju: String? = "") {
        var intent = Intent(context, PoesiaDetialActivity::class.java)
        intent.putExtra("typeFrom", typeFrom)
        intent.putExtra("id", id)
        intent.putExtra("nameStr", nameStr)
        intent.putExtra("author", author)
        intent.putExtra("mingju", mingju)
        var compat = ActivityOptionsCompat.makeCustomAnimation(context, android.R.anim.fade_in, android.R.anim.fade_out);
        ActivityCompat.startActivity(context, intent, compat.toBundle());
    }

    /**
     * 跳转到古籍界面
     */
    fun startLibrosDetailActivity(context: Context, id: Int?, nameStr: String) {
        var intent = Intent(context, LibrosDetialActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("nameStr", nameStr)
        var compat = ActivityOptionsCompat.makeCustomAnimation(context, android.R.anim.fade_in, android.R.anim.fade_out);
        ActivityCompat.startActivity(context, intent, compat.toBundle());
    }
}