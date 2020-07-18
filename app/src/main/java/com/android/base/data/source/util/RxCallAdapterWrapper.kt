package com.android.base.data.source.util

import com.android.base.data.source.remote.network.ApiException
import com.android.base.data.source.remote.network.CommonHttpError
import okhttp3.ResponseBody
import retrofit2.*
import java.lang.reflect.Type
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

class RxCallAdapterWrapper<R>(type: Type, retrofit: Retrofit, wrapped: CallAdapter<R, *>?) :
    BaseRxCallAdapterWrapper<R>(type, retrofit, wrapped) {

    override fun convertRetrofitExceptionToCustomException(
        throwable: Throwable,
        retrofit: Retrofit
    ): Throwable? {
        if (throwable is HttpException) {
            val converter: Converter<ResponseBody, ApiException> =
                retrofit.responseBodyConverter(
                    ApiException::class.java,
                    arrayOfNulls<Annotation>(0)
                )
            val response: Response<*>? = throwable.response()

            when (response?.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> response.errorBody()?.let {
                    return converter.convert(it)
                }

                HttpsURLConnection.HTTP_BAD_REQUEST -> response.errorBody()?.let {
                    return converter.convert(it)
                }

                HttpsURLConnection.HTTP_INTERNAL_ERROR -> response.errorBody()?.let {
                    return converter.convert(it)
                }

                HttpsURLConnection.HTTP_FORBIDDEN -> response.errorBody()?.let {
                    return converter.convert(it)
                }

                HttpsURLConnection.HTTP_NOT_FOUND -> response.errorBody()?.let {
                    return converter.convert(it)
                }

                HttpsURLConnection.HTTP_NOT_ACCEPTABLE -> response.errorBody()?.let {
                    return converter.convert(it)
                }

                HttpsURLConnection.HTTP_CONFLICT -> response.errorBody()?.let {
                    return converter.convert(it)
                }
            }
        }

        if (throwable is UnknownHostException) {
            return ApiException(
                CommonHttpError.NO_NETWORK_EXCEPTION.code,
                CommonHttpError.NO_NETWORK_EXCEPTION.msg
            )
        }

        if (throwable is SocketTimeoutException) {
            return ApiException(
                CommonHttpError.CONNECTION_TIMEOUT.code,
                CommonHttpError.CONNECTION_TIMEOUT.msg
            )
        }

        return throwable
    }

    override fun createExceptionForSuccessResponse(response: Any?): Throwable? =
        super.createExceptionForSuccessResponse(response)
}
