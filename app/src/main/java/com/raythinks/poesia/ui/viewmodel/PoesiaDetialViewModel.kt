package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.ui.model.PoesiaDetailModel
import com.raythinks.poesia.ui.model.TbFanyis
import com.raythinks.poesia.ui.model.TbShangxis
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 功能：诗文详情viewModel<br>
 * 作者：zh<br>
 * 时间： 2017/10/10 0010<br>.
 * 版本：1.2.0
 */
class PoesiaDetialViewModel : BasePoesiaViewModel() {
    var shangxinModel: MutableLiveData<TbShangxis> = MutableLiveData<TbShangxis>();
    var fanyiModel: MutableLiveData<TbFanyis> = MutableLiveData<TbFanyis>();
    fun updatePoesiaDetial(id: String) {
        BaseViewModel.apiService.getPoesiaDetail(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    result?.let {
                        //不爲空
                        result.tb_author.let {
                            authorItem.value = it
                        }
                        result.tb_gushiwen.let { gushiwenItem.value = it }
                        result.tb_fanyis.let { fanyiModel.value = it }
                        result.tb_shangxis.let { shangxinModel.value = it }
                        return@subscribe
                    }

                }, { error ->
                    error.printStackTrace()

                })
    }

    fun getFanYi() = fanyiModel
    fun getShangXi() = shangxinModel
}