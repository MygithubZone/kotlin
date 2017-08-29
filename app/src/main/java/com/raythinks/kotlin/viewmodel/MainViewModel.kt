package com.raythinks.kotlin.viewmodel

import android.arch.lifecycle.LiveData
import com.raythinks.kotlin.base.BaseViewModel
import com.raythinks.kotlin.model.User


/**
 * 功能：<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/8/21 0021<br></br>.
 * 版本：1.2.0
 */

class MainViewModel : BaseViewModel() {
    var users: LiveData<List<User>>? = null
}
