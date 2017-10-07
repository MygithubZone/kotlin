package com.raythinks.poesia.ui.model
data class AuthorListMoel (val sumCount: Int = 0,
val sumPage: Int = 0,
val pageTitle: String? = null,
val currentPage: Int = 0,
val keyStr: String = "",
val authors: ArrayList<AuthorsItem>? = null,
val masterTitle: String = "")