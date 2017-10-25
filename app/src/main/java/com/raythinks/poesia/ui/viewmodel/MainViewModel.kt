package com.raythinks.shiwen.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.base.ERROR_MEG_DATANULL
import com.raythinks.poesia.base.ERROR_STATUS_DATANULL
import com.raythinks.poesia.base.NetError
import com.raythinks.poesia.net.*
import com.raythinks.poesia.ui.model.*
import com.raythinks.poesia.ui.viewmodel.BasePoesiaViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * 功能：<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/8/21 0021<br></br>.
 * 版本：1.2.0
 */

open class MainViewModel : BasePoesiaViewModel() {
    var searchModel: MutableLiveData<SearchModel> = MutableLiveData();
    fun searchPoesia(valuekey: String): LiveData<SearchModel> {
        BaseViewModel.apiService.searchPoesia(valuekey).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    //不爲空
                    if (result != null) {
                        searchModel.value = result
                    } else {
                        onError.value = NetError(ERROR_STATUS_DATANULL, ERROR_MEG_DATANULL, fromApi = ApiSearchPoesia, error = null)
                    }
                    return@subscribe
                }, { error ->
                    onError.value = NetError(fromApi = ApiSearchPoesia, error = error)
                    error.printStackTrace()
                })
        return searchModel
    }

    var librosModel: MutableLiveData<LibrosListModel> = MutableLiveData();
    fun updateLibrosList(p: Int, t: String): LiveData<LibrosListModel> {
        apiService.getLibrosList(p = p, type = t).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    if (result != null) {
                        //不爲空
                        librosModel.value = result
                    } else {
                        onError.value = NetError(ERROR_STATUS_DATANULL, ERROR_MEG_DATANULL, fromApi = ApiLibrosList, error = null)
                    }
                    return@subscribe

                },
                        { error ->
                            onError.value = NetError(fromApi = ApiLibrosList, error = error)
                            error.printStackTrace()

                        })
        return librosModel
    }

    var poesiaListModel: MutableLiveData<PoesiaListModel> = MutableLiveData<PoesiaListModel>();
    fun updatePoesiaList(p: Int, t: String, c: String, x: String): LiveData<PoesiaListModel> {
        BaseViewModel.apiService.getPoesiaList(p, c, t, x).observeOn(AndroidSchedulers.mainThread())
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

    var refranesList: MutableLiveData<RefranesListModel> = MutableLiveData();
    fun updateRefranesList(p: Int, c: String, t: String): LiveData<RefranesListModel> {
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

    var searchTypeUpdate = MutableLiveData<SearchTypeUpdate>()
    fun updateSearchPoesia() = searchTypeUpdate
    fun setSearchPoesiaType(type: String) {
        var searchData = SearchTypeUpdate(type, 0)
        searchTypeUpdate.value = searchData
    }

    var authorListModel: MutableLiveData<AuthorListMoel> = MutableLiveData<AuthorListMoel>()
    fun updateAuthorList(p: Int, c: String): LiveData<AuthorListMoel> {
        BaseViewModel.apiService.getAuthorList(p, c).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    if (result != null) {
                        //不爲空
                        authorListModel.value = result
                    } else {
                        onError.value = NetError(ERROR_STATUS_DATANULL, ERROR_MEG_DATANULL, fromApi = ApiAuthorList, error = null)
                    }
                    return@subscribe

                }, { error ->
                    onError.value = NetError(fromApi = ApiAuthorList, error = error)
                    error.printStackTrace()

                })
        return authorListModel
    }
}