package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.base.BaseViewModel
import com.raythinks.poesia.ui.model.BooksItem
import com.raythinks.poesia.ui.model.LibrosListModel
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

open class LibrosViewModel : BaseViewModel() {
    var librosList: MutableLiveData<ArrayList<BooksItem>> = MutableLiveData();
    fun updateLibrosList(p: Int, c: String, t: String): LiveData<ArrayList<BooksItem>> {
        apiService.getLibrosList(p, c, t).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    result?.let {
                        //不爲空
                        result.books?.let {
                            librosList.value= result.books
                        }
                        return@subscribe
                    }

                }, { error ->
                    error.printStackTrace()

                })
        return librosList
    }
}