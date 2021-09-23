package org.techtown.imagesearchmvvm.DI

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.techtown.imagesearchmvvm.ImageSearchResponse
import org.techtown.imagesearchmvvm.Interface.KakaoSearchService
import org.techtown.imagesearchmvvm.MainSearchRecyclerViewAdapter
import org.techtown.imagesearchmvvm.Model.DataModel
import org.techtown.imagesearchmvvm.Model.DataModelImpl
import org.techtown.imagesearchmvvm.ViewModel.MainViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//의존성 주입에 사용할 모듈 구현  Module은 객체를 제공하는 명세를 의미

//Koin을 통해 DI 해주면서 retrofit 호출하는 부분 ->Retrofit 객체 생성 ?
//이렇게 하면 다른곳에서 get() 이나 by inject()를 통해 원하는 서비스를 얻어올 수 있음
//DataModelImpl에서 생성자의 인자로 서비스를 얻어옵니다.
var retrofitPart = module{
    single<KakaoSearchService>{
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoSearchService::class.java)
    }
}

var adapterPart = module{
    factory {
        MainSearchRecyclerViewAdapter()
    }
}


//https://medium.com/harrythegreat/kotlin%EC%97%90%EC%84%9C-dagger2-%EC%93%B0%EA%B8%B0-%ED%9E%98%EB%93%9C%EB%8B%88-%EA%B7%B8%EB%9F%BC-%EB%84%8C-koin%EC%9D%B4%EC%95%BC-e9e42ec1288e
//module자리에 applicationContext 들어감 -> context를 주입함
var modelPart = module{
    factory<DataModel>{ //DataModelImpl 클래스 만들어줌 -> 다른 클래스에서 해당부분 필요하면 get()을 해주면 팩토리로 만든 클래스가 들어감
        DataModelImpl(get())
    }
}

//뷰모델을 만듬 액티비티에서 by viewModel() 통해서 얻을 수 있음
val viewModelPart = module {
    viewModel{
        MainViewModel(get())
    }
}

var myDiModule = listOf(retrofitPart, adapterPart , modelPart, viewModelPart)