package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.ui.model.AuthorListMoel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 功能：作者列表<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/8/21 0021<br></br>.
 * 版本：1.2.0
 */

class AuthorListViewModel : BaseViewModel() {
    var authorListModel: MutableLiveData<AuthorListMoel> = MutableLiveData<AuthorListMoel>();
    fun updateAuthorList(p: Int, c: String): LiveData<AuthorListMoel> {
        BaseViewModel.apiService.getAuthorList(p, c).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    result?.let {
                        //不爲空
                        authorListModel.value = result
                        if ((result.authors == null || result.authors.size == 0) && result.currentPage < result.sumPage) {
                            updateAuthorList(p + 1, c)
                        }
                        authorListModel.value = result
                        return@subscribe
                    }

                }, { error ->
                    error.printStackTrace()

                })
        return authorListModel
    }
}