package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.net.ERROR_MEG_DATANULL
import com.raythinks.poesia.net.ERROR_STATUS_DATANULL
import com.raythinks.poesia.net.NetError
import com.raythinks.poesia.net.ApiLibrosBookv
import com.raythinks.poesia.ui.model.*
import com.raythinks.shiwen.viewmodel.MainViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by zh on 2017/10/15.
 */

class LibrosReadViewModel : MainViewModel() {
    var booksItemModel: MutableLiveData<LibrosReadModel> = MutableLiveData<LibrosReadModel>();
    fun getBookItem() = booksItemModel
    var booksItemShowType: MutableLiveData<Int> = MutableLiveData<Int>();
    fun setShowType(type: Int) {
        booksItemShowType.value = type
    }

    fun getShowType() = booksItemShowType
    fun updateLiborsItem(id: String): LiveData<LibrosReadModel> {
        apiService.getLibrosBookv(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    if (result != null) {
                        //不爲空
                        booksItemModel.value = result
                    } else {
                        onError.value = NetError(ERROR_STATUS_DATANULL, ERROR_MEG_DATANULL, fromApi = ApiLibrosBookv, error = null, extraData = id)
                    }
                    return@subscribe

                },
                        { error ->
                            onError.value = NetError(fromApi = ApiLibrosBookv, error = error, extraData = id)
                            error.printStackTrace()

                        })
        return booksItemModel
    }
}
