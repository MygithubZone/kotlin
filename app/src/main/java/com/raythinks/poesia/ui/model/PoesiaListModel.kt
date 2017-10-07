package com.raythinks.poesia.ui.model
data class PoesiaListModel (val sumCount: Int = 0,
val sumPage: Int = 0,
val pageTitle: String = "",
val currentPage: Int = 0,
val keyStr: String = "",
val gushiwens: ArrayList<GushiwensItem>? = null,
val masterTitle: String = "")