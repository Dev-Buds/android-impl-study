package com.example.week01.data.remote.service

import com.example.week01.data.remote.dto.ImageSearchResponse
import com.example.week01.data.remote.dto.VideoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/v2/search/image")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("sort") sort: String?,
        @Query("page") page: String?,
        @Query("size") size: String?,
    ): ImageSearchResponse

    @GET("/v2/search/vclip")
    suspend fun searchVideo(
        @Query("query") query: String,
        @Query("sort") sort: String?,
        @Query("page") page: String?,
        @Query("size") size: String?,
    ): VideoSearchResponse
}
