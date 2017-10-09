package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
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
fun updatePoesiaList(p: Int, c: String, t: String): LiveData<ArrayList<GushiwensItem>> {
    BaseViewModel.apiService.getPoesiaList(p, c, t).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                result ->
                result?.let {
                    //不爲空
                    result.gushiwens?.let {
                        poesiaList.value = result.gushiwens
                    }
                    return@subscribe
                }

            }, { error ->
                error.printStackTrace()

            })
    return poesiaList
}
}