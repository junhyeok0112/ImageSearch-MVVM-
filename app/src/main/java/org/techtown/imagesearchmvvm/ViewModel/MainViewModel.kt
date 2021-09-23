package org.techtown.imagesearchmvvm.ViewModel

import android.media.Image
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.techtown.imagesearchmvvm.ImageSearchResponse
import org.techtown.imagesearchmvvm.Model.DataModel
import org.techtown.imagesearchmvvm.Model.KakaoSearchSortEnum



class MainViewModel(private val model: DataModel) : BaseViewModel() {
    //뷰모델에서 데이터 불러옴

    //외부에서 LiveData 변경하지 못하고 내부에서만 변경 가능하도록 _가 존재하는 2개의 변수로 구현
    //ViewModel안에서 _가 있는 변수만 갱신가능하고 _가 없는 변수를 외부에서 참조해서 외부에서 변경은 불가능
    private val _imageSearchResponseLiveData = MutableLiveData<ImageSearchResponse>()
    val imageSearchResponseLiveData :LiveData<ImageSearchResponse> get() = _imageSearchResponseLiveData

    //rxjava 사용할때는 subscribe로 데이터 처리
    //bserving을 계속하면 메모리 누수가 생기기 때문에 데이터의 구독을 시작하면서 compositeDosposable에 추가하고,
    //Observing을 그만두게 될 때(뷰모델이 사라질 때 == 뷰가 사라질 때) compositeDisposable을 비워줌으로서
    // 메모리 누수를 방지하는 작업입니다.
    fun getImageSearch(query:String , page:Int, size:Int){
        addDisposable(model.getData(query , KakaoSearchSortEnum.Accuracy , page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run{
                    if(this.documents.size > 0){
                        Log.d("MainViewModel","documents : $documents")
                        _imageSearchResponseLiveData.postValue(this)
                    }

                    Log.d("MainViewModel" ,"meta : $meta")
                }
            }, {
                Log.d("MainViewModel", "응답 에러 : ${it.message}")
            }))
    }

    //만약 UI스레드가 아닐경우, setValue로 세팅한 값은 UI를 변경하지 못합니다.
    //대신 postValue로 세팅한 값은 해당 값을 UI스레드로 post해주기 때문에 UI스레드가 아니라도 UI를 변경할 수 있게 됩니다.


}