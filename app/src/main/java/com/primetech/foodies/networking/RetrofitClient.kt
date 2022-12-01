package com.instances.resturent_finder.networking


import com.google.gson.GsonBuilder
import com.instances.resturent_finder.App
import com.instances.resturent_finder.utils.Constants.Companion.BASE_URL
import com.instances.resturent_finder.utils.PrefManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private val clientBuilder = OkHttpClient.Builder()
    private val okHttpClient = buildClient()
    private val retrofitBuilder = Retrofit.Builder()
    private val retrofit = buildRetrofit()

    /**
     * build and return okHttpClient
     */

    // this is setting up of client request which things we need to send or get  from api
    private fun buildClient(): OkHttpClient {
        var manager: PrefManager = PrefManager(App.getAppContext()!!)

        clientBuilder.addInterceptor(getLogginInterceptor())
            .addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                        //if api require login tokken
                    .addHeader("Authorization", "Bearer ${manager.accessToken}")
                    .addHeader("Accept","application/json")
                    .addHeader("Content-Type","application/json")
                    .build( )
                chain.proceed(newRequest)
            }.writeTimeout(60, TimeUnit.SECONDS).connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        return clientBuilder.build();
    }

    // this is logging intercemptor which gives error and response in log
    private fun getLogginInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /**
     * build and return retrofit instance
     */
    private fun buildRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return retrofitBuilder
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /**
     * return the implementation of webservice interface
     */
    fun getApiEndpointImpl(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    fun <T> executeApi(call: Call<T>, apiListener: ApiListener<T>) {
        val callback = object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                if (call.isCanceled)
                    apiListener.onCancel()
                else
                    apiListener.onFailure(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (call.isCanceled) {
                    apiListener.onCancel()
                    return
                }
                if (response.isSuccessful) {
                    apiListener.onSuccess(response.body())
                } else {
                    val reason = DefaultErrorHandler(response).handleError()
                    apiListener.onFailure(reason)
                }
            }
        }

        call.enqueue(callback)
    }
}