package com.raythinks.poesia.ui.model

data class RefranesListModel(val sumCount: Int = 0,
                             val mingjus: ArrayList<MingjusItem>? = null,
                             val sumPage: Int = 0,
                             val pageTitle: String = "",
                             val currentPage: Int = 0,
                             val keyStr: String = "",
                             val masterTitle: String = "")