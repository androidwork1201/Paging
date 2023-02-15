package com.example.pagingdemo.network

import com.example.pagingdemo.model.RepoResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubServices {

    @GET("search/repositories?sort=stars&amp;q=Android")
    suspend fun searchRepo(
        @Query("page") page:Int,
        @Query("per_page") perPage: Int
    ): RepoResponse

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): GitHubServices {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubServices::class.java)
        }
    }
}