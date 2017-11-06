package com.raythinks.poesia.net

import com.raythinks.base.net.RetrofitService
import com.raythinks.poesia.ui.model.*
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

/**
 * Created by zh on 2017/10/6.
 */
const val ApiRefranesList = "/api/mingju/Default.aspx"
const val ApiPoesiaList = "/api/shiwen/type.aspx"
const val ApiPoesiaDetail = "/api/shiwen/view.aspx"
const val ApiLibrosList = "/api/guwen/Default.aspx"
const val ApiLibrosDetial = "/api/guwen/book.aspx"
const val ApiLibrosBookv = "api/guwen/bookv.aspx"
const val ApiAuthorList = "/api/author/Default.aspx"
const val ApiAuthorMore = "/api/author/author.aspx"
const val ApiAuthorPoesia = "/api/author/authorsw.aspx"
const val ApiSearchPoesia = "/api/ajaxSearch.aspx"
const val ApiPoesiaDetailByJu = "/api/mingju/ju.aspx"
const val ApiPoesiaContent = "/api/shiwen/ajaxshiwencont.aspx"
const val ApiBaseGuShiWenURL = "http://app.gushiwen.org"
interface PoesiaApiService {
    @POST(ApiRefranesList)
    fun getRefranesList(@Query("p") p: Int, @Query("c") c: String = "", @Query("t") t: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<RefranesListModel>

    @POST(ApiPoesiaList)
    fun getPoesiaList(@Query("p") p: Int, @Query("c") c: String = "", @Query("t") t: String = "", @Query("x") x: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<PoesiaListModel>

    @POST(ApiPoesiaDetail)
    fun getPoesiaDetail(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<PoesiaDetailModel>
    @POST(ApiPoesiaContent)
    fun getPoesiaContent(@Query("id") id: String = "0",@Query("value") value: String = "cont", @Query("token") token: String = "gswapi"): Observable<PoesiaYiZhuCont>
    @POST(ApiPoesiaDetailByJu)
    fun getPoesiaDetailByJu(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<PoesiaDetailModel>

    @POST(ApiLibrosList)
    fun getLibrosList(@Query("p") p: Int, @Query("type") type: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<LibrosListModel>

    @POST(ApiLibrosDetial)
    fun getLibrosDetial(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<GuWenDetialModel>

    @POST(ApiAuthorList)
    fun getAuthorList(@Query("p") p: Int, @Query("c") c: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<AuthorListMoel>

    @POST(ApiAuthorMore)
    fun getAuthorMore(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<AuthorMoreInfoMoel>

    @POST(ApiAuthorPoesia)
    fun getAuthorPoesia(@Query("page") p: Int, @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<AuthorPoesiaModel>

    @POST(ApiLibrosBookv)
    fun getLibrosBookv(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<LibrosReadModel>

    @POST(ApiSearchPoesia)
    fun searchPoesia(@Query("valuekey") valuekey: String = "", @Query("token") token: String = "gswapi"): Observable<SearchModel>

    companion object Factory {
        fun create() = RetrofitService().getApiService(ApiBaseGuShiWenURL, PoesiaApiService::class.java)
    }
}
