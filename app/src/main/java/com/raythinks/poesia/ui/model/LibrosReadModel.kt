package com.raythinks.poesia.ui.model

data class LibrosReadModel(
        val tb_bookview: BookviewsItem? = null,
        val nextId: Int = 0,
        val tb_shangxis: TbBookShangxis? = null,
        val prevId: Int = 0,
        val tb_author: AuthorsItem? = null,
        val tb_fanyis: TbBookFanyis? = null)