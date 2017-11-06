package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.net.ERROR_STATUS_DATANULL
import com.raythinks.poesia.net.NetError
import com.raythinks.poesia.net.ApiAuthorPoesia
import com.raythinks.poesia.ui.model.AuthorMoreInfoMoel
import com.raythinks.poesia.ui.model.AuthorPoesiaModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 功能：作者列表<br></br>
 * 作者：zh<br></br>
 * 时间： 2017/8/21 0021<br></br>.
 * 版本：1.2.0
 */

class AuthorDetialViewModel : BasePoesiaViewModel() {
    var authorMoreModel: MutableLiveData<AuthorMoreInfoMoel> = MutableLiveData<AuthorMoreInfoMoel>();
    var authorPoeisaModel: MutableLiveData<AuthorPoesiaModel> = MutableLiveData<AuthorPoesiaModel>();
    fun updateAuthorMore(id: String): LiveData<AuthorMoreInfoMoel> {
        BaseViewModel.apiService.getAuthorMore(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    result?.let {
                        authorMoreModel.value = result
                        result.tb_author.let {
                            authorItem.value = it
                        }

                        return@subscribe
                    }

                }, { error ->
                    error.printStackTrace()
                })
        return authorMoreModel
    }

    var authorId = 0
    fun updateAuthorPoeisa(p: Int, id: Int = authorId): LiveData<AuthorPoesiaModel> {
        authorId = id
        BaseViewModel.apiService.getAuthorPoesia(p, "${id}").observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    if (result != null) {
                        authorPoeisaModel.value = result
                    } else {
                        onError.value = NetError(ERROR_STATUS_DATANULL, "暂无添加该诗人作品饿", fromApi = ApiAuthorPoesia, error = null)
                    }
                    return@subscribe

                },
                        { error ->
                            onError.value = NetError(fromApi = ApiAuthorPoesia, error = error)
                            error.printStackTrace()

                        })
        return authorPoeisaModel
    }

    fun getAuthorPoeisa() = authorPoeisaModel
    fun getAuthorMore() = authorMoreModel
}