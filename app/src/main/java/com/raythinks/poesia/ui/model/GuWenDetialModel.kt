package com.raythinks.poesia.ui.model
import com.raythinks.poesia.ui.model.AuthorsItem
import com.raythinks.poesia.ui.model.BooksItem

data class GuWenDetialModel(
        val tb_book: BooksItem? = null,
        val tb_author: AuthorsItem? = null,
        val tb_bookviews: TbBookviews? = null)