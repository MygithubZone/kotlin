package com.raythinks.poesia.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.raythinks.poesia.ui.model.BooksItem
import com.raythinks.poesia.ui.model.GuWenDetialModel
import com.raythinks.poesia.ui.model.LibrosListModel
import com.raythinks.poesia.ui.model.TbBookviews
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by zh on 2017/10/15.
 */

class LibrosDetailViewModel : LibrosViewModel() {
    var booksModel: MutableLiveData<BooksItem> = MutableLiveData<BooksItem>();
    fun getBooks() = booksModel
    var booksViewsModel: MutableLiveData<TbBookviews> = MutableLiveData<TbBookviews>();
    fun getBookViews() = booksViewsModel

    fun updateLiborsDetial(id: String) {
        apiService.getLibrosDetial(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    result?.let {
                        //不爲空
                        result.tb_book.let {
                            booksModel.value = it
                        }
                        result.tb_bookviews.let {
                            booksViewsModel.value = it
                        }
                        return@subscribe
                    }

                }, { error ->
                    error.printStackTrace()

                })
    }

}
