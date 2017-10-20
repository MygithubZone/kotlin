package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.ERROR_STATUS_DATANULL
import com.raythinks.poesia.base.NetError
import com.raythinks.poesia.net.ApiPoesiaList
import com.raythinks.poesia.net.ApiRefranesList
import com.raythinks.poesia.ui.model.GushiwensItem
import com.raythinks.poesia.ui.model.MingjusItem
import com.raythinks.poesia.ui.model.PoesiaListModel
import com.raythinks.poesia.ui.model.RefranesListModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 功能：作者列表<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/8/21 0021<br></br>.
 * 版本：1.2.0
 */

class PoesiaViewModel : BasePoesiaViewModel() {
    var poesiaListModel: MutableLiveData<PoesiaListModel> = MutableLiveData<PoesiaListModel>();
    var poesiaList: MutableLiveData<ArrayList<GushiwensItem>> = MutableLiveData();
    fun updatePoesiaList(p: Int, c: String, t: String): LiveData<PoesiaListModel> {
        BaseViewModel.apiService.getPoesiaList(p, c, t).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    //不爲空
                    if (result != null) {
                        poesiaListModel.value = result
                    } else {
                        onError.value = NetError(ERROR_STATUS_DATANULL, ERROR_MEG_DATANULL, fromApi = ApiPoesiaList, error = null)
                    }
                    return@subscribe
                }, { error ->
                    onError.value = NetError(fromApi = ApiPoesiaList, error = error)
                    error.printStackTrace()
                })
        return poesiaListModel
    }
}