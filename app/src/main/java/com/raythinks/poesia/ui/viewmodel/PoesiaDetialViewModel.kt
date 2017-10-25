package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.ERROR_STATUS_DATANULL
import com.raythinks.poesia.base.NetError
import com.raythinks.poesia.net.ApiPoesiaDetail
import com.raythinks.poesia.net.ApiRefranesList
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
                    if (result != null) {
                        result.tb_author.let {
                            authorItem.value = it
                        }
                        result.tb_gushiwen.let { gushiwenItem.value = it }
                        result.tb_fanyis.let { fanyiModel.value = it }
                        result.tb_shangxis.let { shangxinModel.value = it }
                    } else {

                        onError.value = NetError(ERROR_STATUS_DATANULL, "诗文详情暂无数据哟，请重试", fromApi = ApiPoesiaDetail, error = null)
                    }
                    return@subscribe

                }, { error ->
                    onError.value = NetError(fromApi = ApiPoesiaDetail, error = error)
                    error.printStackTrace()

                })
    }

    fun getFanYi() = fanyiModel
    fun getShangXi() = shangxinModel
}