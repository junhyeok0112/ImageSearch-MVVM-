package org.techtown.imagesearchmvvm.Interface

import io.reactivex.Single
import org.techtown.imagesearchmvvm.ImageSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface KakaoSearchService {
    @GET("/v2/search/image")
    fun searchImage(    //유저 인증 토큰이 필요한 경우, Retrofit에 header 추가해서 인증이 가능하다. -> 서버가 헤더를 인식하게 했을 경우
        @Header("Authorization") auth:String,
        @Query("query") query:String,
        @Query("sort") sort:String,
        @Query("page") page:Int,
        @Query("size") size:Int) : Single<ImageSearchResponse>   //single은 데이터를 한번만 뿌린다는 뜻
}