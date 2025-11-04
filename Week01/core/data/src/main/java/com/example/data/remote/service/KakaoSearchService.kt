package com.example.data.remote.service

import com.example.data.BuildConfig
import com.example.data.remote.model.SearchImageDocument
import com.example.data.remote.model.SearchResponse
import com.example.data.remote.model.SearchVClipDocument
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoSearchService {
    /**
     * 이미지 검색 API 요청 파라미터
     *
     * @property query  검색을 원하는 질의어 (**필수**)
     * @property sort   결과 정렬 방식: `accuracy`(정확도순) 또는 `recency`(최신순), 기본값은 `accuracy`
     * @property page   결과 페이지 번호 (1~50), 기본값은 `1`
     * @property size   한 페이지에 보여질 문서 수 (1~80), 기본값은 `80`
     */
    @GET("v2/search/image")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Header("Authorization") apiKey: String = BuildConfig.KAKAO_API_KEY,
    ): Response<SearchResponse<SearchImageDocument>>

    /**
     * 동영상 검색 API 요청 파라미터
     *
     * @property query  검색을 원하는 질의어 (**필수**)
     * @property sort   결과 정렬 방식: `accuracy`(정확도순) 또는 `recency`(최신순), 기본값은 `accuracy`
     * @property page   결과 페이지 번호 (1~15), 기본값은 `1`
     * @property size   한 페이지에 보여질 문서 수 (1~30), 기본값은 `15`
     */
    @GET("v2/search/vclip")
    suspend fun searchVClip(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Header("Authorization") apiKey: String = BuildConfig.KAKAO_API_KEY,
    ): Response<SearchResponse<SearchVClipDocument>>
}
