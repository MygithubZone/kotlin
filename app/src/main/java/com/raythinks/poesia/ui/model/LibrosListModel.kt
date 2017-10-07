package com.raythinks.poesia.ui.model
data class LibrosListModel(val sumCount: Int = 0,
                           val sumPage: Int = 0,
                           val books: ArrayList<BooksItem>? = null,
                           val pageTitle: String = "",
                           val currentPage: Int = 0,
                           val keyStr: String = "",
                           val masterTitle: String = "")