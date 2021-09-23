package org.techtown.imagesearchmvvm.Model

import io.reactivex.Single
import org.techtown.imagesearchmvvm.ImageSearchResponse
import org.techtown.imagesearchmvvm.Interface.KakaoSearchService


class DataModelImpl(private val service: KakaoSearchService): DataModel{

    private val KAKAO_APP_KEY = "71593e65d0e9648b11d6983b18acad45"

    //retrofit 객체 리턴 시킨다고 보면됨
    override fun getData(query:String, sort:KakaoSearchSortEnum, page:Int, size:Int): Single<ImageSearchResponse> {
        return service.searchImage(auth = "KakaoAK $KAKAO_APP_KEY", query = query, sort = sort.sort, page = page, size = size)
    }
}