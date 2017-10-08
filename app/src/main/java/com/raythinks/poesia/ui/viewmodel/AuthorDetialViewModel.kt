package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.ui.model.AuthorListMoel
import com.raythinks.poesia.ui.model.AuthorMoreInfoMoel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 功能：作者列表<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/8/21 0021<br></br>.
 * 版本：1.2.0
 */

class AuthorDetialViewModel : BaseViewModel() {
    var authorMoreModel: MutableLiveData<AuthorMoreInfoMoel> = MutableLiveData<AuthorMoreInfoMoel>();
    fun updateAuthorMore(id: String): LiveData<AuthorMoreInfoMoel> {
        BaseViewModel.apiService.getAuthorMore(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    result?.let {
                        authorMoreModel.value = result
                        return@subscribe
                    }

                }, { error ->
                    error.printStackTrace()
                })
        return authorMoreModel
    }

    fun getAuthorMore() = authorMoreModel
}