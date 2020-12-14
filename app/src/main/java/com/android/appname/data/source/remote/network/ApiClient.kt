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

open class ApiClient private constructor(private val url: String) {

    companion object {
        private val instances = mutableMapOf<String?, ApiClient>()

        internal fun getInstance(url: String = BuildConfig.BASE_API_URL) = instances.getOrPut(url, {
            ApiClient(url)
        })

        internal fun clearInstance() {
            instances.clear()
        }
    }

    private var token: String? = "8dcc1542a16daaa11c0ed5c3b7add287fdb28ec1"

    val service by lazy {
        createService()
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
            .baseUrl(url)
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}
