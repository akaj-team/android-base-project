package com.android.appname.di

import com.android.appname.BuildConfig
import com.android.appname.data.source.remote.network.ApiService
import com.android.appname.data.source.util.IgnoreAuthentication
import com.android.appname.managers.PrefManager
import com.android.appname.managers.SharedPreferencesKeys
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Invocation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author at-hungtruong
 */
@Module
class NetworkModule {

    companion object {
        private const val HTTP_CLIENT_READ_TIMEOUT_SECONDS = 60L
        private const val HTTP_CLIENT_WRITE_TIMEOUT_SECONDS = 60L
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val AUTHORIZATION_PREFIX = "Bearer"

        private const val ACCESS_TOKEN_AUTHENTICATION_PROVIDER_NAMED =
            "ACCESS_TOKEN_AUTHENTICATION_PROVIDER"
        private const val BASE_URL_PROVIDER_NAMED = "BASE_URL_PROVIDER"
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideGsonParser(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setLenient()
        .serializeNulls()
        .create()

    @Provides
    @Singleton
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    @Named(ACCESS_TOKEN_AUTHENTICATION_PROVIDER_NAMED)
    fun provideBearerTokenAuthentication(prefManager: PrefManager) = Interceptor { chain ->
        val request = chain.request().newBuilder().apply {

            val token = prefManager.getString(SharedPreferencesKeys.ACCESS_TOKEN) ?: "e04d80a7ea31de47fa6dbb3663c73044835c5446"
            if (chain.request().tag(Invocation::class.java)?.method()
                    ?.getAnnotation(IgnoreAuthentication::class.java) == null
            ) {
                addHeader(AUTHORIZATION_HEADER, "$AUTHORIZATION_PREFIX $token")
            }
        }
        chain.proceed(request.build())
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @Named(ACCESS_TOKEN_AUTHENTICATION_PROVIDER_NAMED) accessTokenInterceptor: Interceptor
    ) =
        OkHttpClient.Builder().apply {
            readTimeout(HTTP_CLIENT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(HTTP_CLIENT_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            addInterceptor(accessTokenInterceptor)
        }.build()

    @Provides
    @Singleton
    @Named(BASE_URL_PROVIDER_NAMED)
    fun provideBaseUrl() = BuildConfig.BASE_API_URL

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        converter: GsonConverterFactory,
        @Named(BASE_URL_PROVIDER_NAMED) baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(converter)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create<ApiService>()
}
