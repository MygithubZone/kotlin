package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.net.ERROR_MEG_NET
import com.raythinks.poesia.net.ERROR_STATUS_DATANULL
import com.raythinks.poesia.net.NetError
import com.raythinks.poesia.net.ApiSearchPoesia
import com.raythinks.poesia.ui.model.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 功能：作者列表<br></br>
 * 作者：zh<br></br>
 * 时间： 2017/8/21 0021<br></br>.
 * 版本：1.2.0
 */

open class BasePoesiaViewModel : BaseViewModel() {
    var authorItem: MutableLiveData<AuthorsItem> = MutableLiveData<AuthorsItem>();
    fun getAuthor() = authorItem
    var gushiwenItem: MutableLiveData<GushiwensItem> = MutableLiveData<GushiwensItem>();
    fun getGuShiWen() = gushiwenItem
    var poesiaYiZhuCont: MutableLiveData<PoesiaYiZhuCont> = MutableLiveData<PoesiaYiZhuCont>();
    fun updatePoesiaContent(id: String, type: String): LiveData<PoesiaYiZhuCont> {
        BaseViewModel.apiService.getPoesiaContent(id, type).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    //不爲空
                    if (result != null) {
                        poesiaYiZhuCont.value = result
                    } else {
                        onError.value = NetError(ERROR_STATUS_DATANULL, ERROR_MEG_NET, fromApi = ApiSearchPoesia, error = null)
                    }
                    return@subscribe
                }, { error ->
                    onError.value = NetError(fromApi = ApiSearchPoesia, error = error)
                    error.printStackTrace()
                })
        return poesiaYiZhuCont
    }
}