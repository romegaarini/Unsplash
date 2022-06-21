package org.d3if0031.unsplash.data

import org.d3if0031.unsplash.BuildConfig
import org.d3if0031.unsplash.data.model.ImageResponse
import org.d3if0031.unsplash.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashAPI {

    @GET("/photos")
    @Headers(
        "Content-Type: application/json",
        "Authorization: Client-ID ${BuildConfig.UNSPLASH_API_KEY}}",
        "X-Ratelimit-Limit: 200",
        "X-Ratelimit-Remaining: 999"
    )
    suspend fun getFeed(
        @Query("page") page: Int = 1,
    ): List<ImageResponse>

    @GET("/search/photos")
    @Headers(
        "Content-Type: application/json",
        "Authorization: Client-ID ${BuildConfig.UNSPLASH_API_KEY}}",
        "X-Ratelimit-Limit: 200",
        "X-Ratelimit-Remaining: 999"
    )
    suspend fun search(
        @Query("page") page: Int,
        @Query("query") searchQuery: String
    ): SearchResponse

}