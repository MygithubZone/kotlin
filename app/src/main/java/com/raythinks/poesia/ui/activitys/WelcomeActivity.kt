package com.raythinks.poesia.ui.activitys

import android.content.Intent
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.chenenyu.router.Router
import com.raythinks.base.BaseActivity
import com.raythinks.poesia.R
import com.raythinks.poesia.base.BaseVMActivity
import com.raythinks.poesia.ui.viewmodel.WelcomeViewModel

/**
 * 功能：<br>
 * 作者：赵海<br>
 * 时间： 2017/9/25 0025<br>.
 * 版本：1.2.0
 */
class WelcomeActivity : BaseVMActivity<WelcomeViewModel>() {
    override fun initView() {
    }

    override fun initData() {
        viewModel.start()
        Handler().postDelayed(Runnable {
//            Router.build("poesia://activity/mainActivity")
////                    .anim(enter, exit)  // 添加跳转动画
//                    .go(this);
            startActivity(Intent(this,MainActivity::class.java))
        }, 3000)
    }

    override fun getLayoutId(): Int = R.layout.activity_welcome

    override fun setContentView(layoutResID: Int) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
        super.setContentView(layoutResID)

    }

}