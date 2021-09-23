package org.techtown.imagesearchmvvm.View

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.techtown.imagesearchmvvm.ViewModel.BaseViewModel

//기본이 될 BaseView 액티비티 , 모든 액티비티는 이 베이스 액티비티를 구현하게 됩니다
//<> 안에 있는거는 제네릭
abstract class BaseActivity<T : ViewDataBinding , R : BaseViewModel> : AppCompatActivity() {

    lateinit var viewDataBinding: T

    //setContentView로 호출할 Layout의 리소스 ID
    abstract val layoutResourceId: Int
    //val layoutResourceId : Int get() = R.layout. ~ 로 사용

    //viewModel로 쓰일 변수 , 스낵바 옵저빙을 위해서 뷰모델 클래스도 제네릭으로 줌
    abstract val viewModel : R

    //레이아웃을 띄운 직후 호추 , 뷰나 액티비티의 속성등을 초기화
    //ex) 리싸이클러뷰 , 툴바 , 드로어뷰
    abstract fun initStartView()

    //두번째로 호출 , 데이터 바인딩 및 rxjava 설정
    //ex) rxjava observe , databinding observe..
    abstract fun initDataBinding()

    //바인딩 후 할 일으 여기에 구현 , 그외에 설정할 것이 있으면 이곳에서 설정
    // 클릭 리스너도 이곳에서 설정
    abstract fun initAfterBinding()

    private var isSetBackButtonValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)

        initStartView()
        initDataBinding()
        initAfterBinding()

    }
}