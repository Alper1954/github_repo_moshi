package com.example.githubrepolist.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://api.github.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GitHubApiService {
    @GET("/users/{user}/repos")
    suspend fun reposForUser(@Path("user") user: String): List<GitHubRepo>
}

object GitHubApi {
    val retrofitService : GitHubApiService by lazy {
        retrofit.create(GitHubApiService::class.java) }
}
