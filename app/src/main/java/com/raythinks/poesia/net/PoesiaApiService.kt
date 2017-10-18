package com.raythinks.poesia.net

import com.raythinks.base.net.RetrofitService
import com.raythinks.poesia.ui.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by 赵海 on 2017/10/6.
 */
const val ApiRefranesList = "/api/mingju/Default.aspx"
const val ApiPoesiaList = "/api/shiwen/type.aspx"
const val ApiPoesiaDetail = "/api/shiwen/view.aspx"
const val ApiLibrosList = "/api/guwen/Default.aspx"
const val ApiLibrosDetial = "/api/guwen/book.aspx"
const val ApiAuthorList = "/api/author/Default.aspx"
const val ApiAuthorMore = "/api/author/author.aspx"
const val ApiAuthorPoesia = "/api/author/authorsw.aspx"
const val ApiBaseGuShiWenURL = "http://app.gushiwen.org"

interface PoesiaApiService {
    @GET(ApiRefranesList)
    fun getRefranesList(@Query("p") p: Int, @Query("c") c: String = "", @Query("t") t: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<RefranesListModel>

    @GET(ApiPoesiaList)
    fun getPoesiaList(@Query("p") p: Int, @Query("c") c: String = "", @Query("t") t: String = "", @Query("x") x: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<PoesiaListModel>

    @GET(ApiPoesiaDetail)
    fun getPoesiaDetail(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<PoesiaDetailModel>

    @GET(ApiLibrosList)
    fun getLibrosList(@Query("p") p: Int, @Query("type") type: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<LibrosListModel>

    @GET(ApiLibrosDetial)
    fun getLibrosDetial(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<GuWenDetialModel>

    @GET(ApiAuthorList)
    fun getAuthorList(@Query("p") p: Int, @Query("c") c: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<AuthorListMoel>

    @GET(ApiAuthorMore)
    fun getAuthorMore(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<AuthorMoreInfoMoel>

    @GET(ApiAuthorPoesia)
    fun getAuthorPoesia(@Query("p") p: Int, @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<AuthorPoesiaModel>

    companion object Factory {
        fun create() = RetrofitService().getApiService(ApiBaseGuShiWenURL, PoesiaApiService::class.java)
    }
}
