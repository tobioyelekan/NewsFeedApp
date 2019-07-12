package com.example.android.newsfeed.data.network

import com.example.android.newsfeed.data.model.FeedResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://35.226.14.35:8080/api/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface FeedService {
    @GET("posts/newsfeed")
    fun getFeeds(): Deferred<List<FeedResponse>>
}

object FeedApi {
    val retrofitService: FeedService by lazy {
        retrofit.create(FeedService::class.java)
    }
}
