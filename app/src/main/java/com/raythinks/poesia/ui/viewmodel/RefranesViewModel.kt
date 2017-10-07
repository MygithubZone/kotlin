package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.ui.model.MingjusItem
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
    var refranesListModel: MutableLiveData<RefranesListModel> = MutableLiveData<RefranesListModel>();
    var refranesList: MutableLiveData<ArrayList<MingjusItem>> = MutableLiveData();
    fun updateRefranesList(p: Int, c: String, t: String): LiveData<ArrayList<MingjusItem>> {
        apiService.getRefranesList(p, c, t).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                        result?.let {
                            //不爲空
                            result.mingjus?.let {
                                refranesList.value= result.mingjus
                            }
                            return@subscribe
                        }

                }, { error ->
                    error.printStackTrace()

                })
        return refranesList
    }
}