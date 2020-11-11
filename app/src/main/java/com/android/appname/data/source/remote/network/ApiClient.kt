package com.android.appname.data.source.remote.network

import com.android.appname.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*

open class ApiClient private constructor(url: String? = null) {

    private var token: String? = "ac408a11f1472afcfa1101fc9d7ac854ed6ca96a"
    private val baseUrl: String = if (url.isNullOrBlank()) BuildConfig.BASE_API_URL else url

    companion object : SingletonHolder<ApiClient, String>(::ApiClient)

    val service: ApiService
        get() {
            return createService()
        }

    private fun createService(): ApiService {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.interceptors().add(Interceptor { chain ->
            val original = chain.request()
            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
            if (!token.isNullOrBlank()) {
                requestBuilder.addHeader("Authorization", "token $token")
            }
            val request = requestBuilder.build()
            chain.proceed(request)
        })
        if (BuildConfig.DEBUG) {
            val log = HttpLoggingInterceptor()
            log.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(log)
        }

        val client = httpClientBuilder
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .build()

        val gson = GsonBuilder()
            .disableHtmlEscaping()
            .create()

        val nullOnEmptyConverterFactory = object : Converter.Factory() {
            fun converterFactory() = this
            override fun responseBodyConverter(
                type: Type,
                annotations: Array<out Annotation>,
                retrofit: Retrofit
            ) = object : Converter<ResponseBody, Any?> {
                val nextResponseBodyConverter =
                    retrofit.nextResponseBodyConverter<Any?>(
                        converterFactory(),
                        type,
                        annotations
                    )

                override fun convert(value: ResponseBody) =
                    if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
            }
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(CustomCallAdapterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}

/**
 * Use this class to create singleton object with argument
 */
open class SingletonHolder<out T, in A>(private var creator: (A?) -> T) {
    @kotlin.jvm.Volatile
    private var instance: T? = null

    /**
     * Generate instance for T class with argument A
     */
    fun getInstance(arg: A?): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator(arg)
                instance = created
                created
            }
        }
    }

    /**
     * Clear current instance
     */
    fun clearInstance() {
        instance = null
    }
}
