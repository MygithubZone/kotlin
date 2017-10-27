package com.raythinks.poesia.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raythinks.poesia.net.PoesiaApiService

/**
 * Created by zh on 2017/8/20.
 */
abstract class BaseViewModel : ViewModel() {
    companion object {
        lateinit var apiService: PoesiaApiService

    }

    var onError: MutableLiveData<NetError> = MutableLiveData<NetError>();
    fun onFinishError(): LiveData<NetError> {
        return onError;
    }

    init {
        apiService = PoesiaApiService.create()
    }

}