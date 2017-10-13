package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.ui.model.PoesiaDetailModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 功能：诗文详情viewModel<br>
 * 作者：zh<br>
 * 时间： 2017/10/10 0010<br>.
 * 版本：1.2.0
 */
class PoesiaDetialViewModel : BasePoesiaViewModel() {
    var poesiaDetialModel: MutableLiveData<PoesiaDetailModel> = MutableLiveData<PoesiaDetailModel>();
    fun updatePoesiaDetial(id: String): LiveData<PoesiaDetailModel> {
        BaseViewModel.apiService.getPoesiaDetail(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    result?.let {
                        //不爲空
                        poesiaDetialModel.value = result
                        result.tb_author.let {
                            authorItem.value = it
                        }
                        return@subscribe
                    }

                }, { error ->
                    error.printStackTrace()

                })
        return poesiaDetialModel
    }

    fun getPoesiaDetail() = poesiaDetialModel
}