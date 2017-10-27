package com.raythinks.base.net

import com.raythinks.base.AppConfig
import com.raythinks.base.BaseApp
import com.raythinks.base.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.cuieney.videolife.kotlin.common.okhttp.CacheInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate

/**
 * 功能：<br>
 * 作者：zh<br>
 * 时间： 2017/9/19 0019<br>.
 * 版本：1.2.0
 */
class RetrofitService() {
    fun <T> getApiService(baseUrl: String, client: OkHttpClient, clz: Class<T>): T {
        var retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        return retrofit.create(clz)
    }

    fun <T> getApiService(baseUrl: String, clz: Class<T>): T {

        return getApiService(baseUrl, provideOkhttpClient(), clz)
    }

    /**
     *
     */
    class XtmImp : javax.net.ssl.X509TrustManager {
        override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
        }

        override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            val x509Certificates: Array<X509Certificate> = arrayOf()
            return x509Certificates
        }

    }

    /**
     *
     */
    class DO_NOT_VERIFY_IMP : javax.net.ssl.HostnameVerifier {
        override fun verify(p0: String?, p1: javax.net.ssl.SSLSession?): Boolean {
            return true
        }

    }

    fun provideOkhttpClient(): okhttp3.OkHttpClient {
        return provideOkhttpClient(okhttp3.Cache(BaseApp.mContext.getExternalFilesDir(AppConfig.DEFAULT_JOSN_CACHE), AppConfig.DEFAULT_CACHE_SIZE), CacheInterceptor(BaseApp.mContext))
    }

    fun provideOkhttpClient(cache: Cache, cacheInterceptor: CacheInterceptor): okhttp3.OkHttpClient {
        val xtm: XtmImp = XtmImp()
        val sslContext = javax.net.ssl.SSLContext.getInstance("SSL")
        try {
            sslContext.init(null, arrayOf<javax.net.ssl.TrustManager>(xtm), java.security.SecureRandom())
        } catch (e: java.security.NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: java.security.KeyManagementException) {
            e.printStackTrace()
        }
        val DO_NOT_VERIFY = DO_NOT_VERIFY_IMP()

        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            // 测试环境打印日志
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            // 线上环境不打印日志
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return okhttp3.OkHttpClient().newBuilder()
                .cache(cache)//添加缓存
                .addInterceptor(loggingInterceptor)
                .addInterceptor(cacheInterceptor)
                .sslSocketFactory(sslContext.getSocketFactory())
                .hostnameVerifier(DO_NOT_VERIFY)
                .build();
    }
}