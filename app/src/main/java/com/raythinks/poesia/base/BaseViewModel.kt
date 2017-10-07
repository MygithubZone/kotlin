package com.raythinks.poesia.base

import android.arch.lifecycle.ViewModel
import com.raythinks.poesia.net.PoesiaApiService

/**
 * Created by 赵海 on 2017/8/20.
 */
abstract class BaseViewModel : ViewModel() {
    companion object {
        lateinit var apiService: PoesiaApiService
    }

    init {
        apiService = PoesiaApiService.create()
    }

}