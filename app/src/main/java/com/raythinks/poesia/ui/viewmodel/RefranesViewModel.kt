package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.ERROR_STATUS_DATANULL
import com.raythinks.poesia.base.NetError
import com.raythinks.poesia.net.ApiRefranesList
import com.raythinks.poesia.ui.model.RefranesListModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 功能：作者列表<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/8/21 0021<br></br>.
 * 版本：1.2.0
 */

class RefranesViewModel : BaseViewModel() {
    var refranesList: MutableLiveData<RefranesListModel> = MutableLiveData();
    fun updateRefranesList(p: Int, c: String = "", t: String = ""): LiveData<RefranesListModel> {
        apiService.getRefranesList(p, c, t).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    if (result != null) {
                        refranesList.value = result
                    } else {
                        onError.value = NetError(ERROR_STATUS_DATANULL, ERROR_MEG_DATANULL, fromApi = ApiRefranesList, error = null)
                    }
                    return@subscribe

                }, { error ->
                    onError.value = NetError(fromApi = ApiRefranesList, error = error)
                    error.printStackTrace()
                })
        return refranesList
    }
}