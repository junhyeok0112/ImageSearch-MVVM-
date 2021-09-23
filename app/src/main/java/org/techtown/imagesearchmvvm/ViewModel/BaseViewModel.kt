package org.techtown.imagesearchmvvm.ViewModel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    /*
    * RxJava 의 observing을 위한 부분
    * addDisposable을 이용하여 추가하기만 하면된다.
    * */
    private val compositeDisposable = CompositeDisposable()

    //Model에서 들어오는 Single<>과 같은 RxJava 객체들의 Observing을 위한 부분
    //RxJava의 Observable들은 compositeDisposable에 추가를 해주고 뷰모델이 없어질 때 추가했던 것들을 지워줘야함
    //이 부분은 그러한 동작을 수행하는 코드로 Observable들을 옵저빙할때 addDisposable()을 쓰게 됩니다.

    fun addDisposable(disposable : Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}