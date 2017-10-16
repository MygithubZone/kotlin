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

interface PoesiaApiService {
    @GET("/api/mingju/Default.aspx")
    fun getRefranesList(@Query("p") p: Int, @Query("c") c: String = "", @Query("t") t: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<RefranesListModel>

    @GET("/api/shiwen/type.aspx")
    fun getPoesiaList(@Query("p") p: Int, @Query("c") c: String = "", @Query("t") t: String = "", @Query("x") x: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<PoesiaListModel>

    @GET("/api/shiwen/view.aspx")
    fun getPoesiaDetail(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<PoesiaDetailModel>
    @GET("/api/guwen/Default.aspx")
    fun getLibrosList(@Query("p") p: Int, @Query("type") type: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<LibrosListModel>
    @GET("/api/guwen/book.aspx")
    fun getLibrosDetial(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<GuWenDetialModel>

    @GET("/api/author/Default.aspx")
    fun getAuthorList(@Query("p") p: Int, @Query("c") c: String = "", @Query("pwd") pwd: String = "", @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<AuthorListMoel>

    @GET("/api/author/author.aspx")
    fun getAuthorMore(@Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<AuthorMoreInfoMoel>

    @GET("/api/author/authorsw.aspx")
    fun getAuthorPoesia(@Query("p") p: Int, @Query("id") id: String = "0", @Query("token") token: String = "gswapi"): Observable<AuthorPoesiaModel>

    companion object Factory {
        fun create() = RetrofitService().getApiService("http://app.gushiwen.org", PoesiaApiService::class.java)
    }
}
