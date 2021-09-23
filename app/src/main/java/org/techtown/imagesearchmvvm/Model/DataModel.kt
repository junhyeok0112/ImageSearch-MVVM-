package org.techtown.imagesearchmvvm.Model

import org.techtown.imagesearchmvvm.ImageSearchResponse
import io.reactivex.Single

interface DataModel{
    fun getData(query:String, sort:KakaoSearchSortEnum , page:Int , size:Int ): Single<ImageSearchResponse>
}
