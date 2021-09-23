package org.techtown.imagesearchmvvm.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.techtown.imagesearchmvvm.MainSearchRecyclerViewAdapter
import org.techtown.imagesearchmvvm.R
import org.techtown.imagesearchmvvm.ViewModel.MainViewModel
import org.techtown.imagesearchmvvm.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding , MainViewModel>(){

    //해당변수 참조하면 get() 안에 있는 값으로 참조됨 , get()사용하면 해당속성을 참고할때 실제 게터가 자동으로 호출됨
    //공부 추가로 필요
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    //override val viewModel: MainViewModel by viewModel()
    override val viewModel : MainViewModel by viewModel()

    private val mainSearchRecyclerViewAdapter : MainSearchRecyclerViewAdapter by inject()

    //이부분 다시 공부 run , apply , StaggerGridLayoutManager, setHasFixedSize
    override fun initStartView() {
        viewDataBinding.mainActivitySearchRecyclerView.run{
            adapter = mainSearchRecyclerViewAdapter
            layoutManager = StaggeredGridLayoutManager(3,1).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                orientation = StaggeredGridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        //LiveData 바뀌면 observe 호출
        viewModel.imageSearchResponseLiveData.observe(this, Observer {
            //이전에 검색했던 내용들 클리어
            mainSearchRecyclerViewAdapter.clearItems()
            it.documents.forEach { document ->
                mainSearchRecyclerViewAdapter.addItem(document.image_url , document.doc_url)
            }
            mainSearchRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        //버튼 누르면 text에 있는 값 검색
        viewDataBinding.mainActivitySearchButton.setOnClickListener {
            viewModel.getImageSearch(viewDataBinding.mainActivitySearchTextView.text.toString() , 1, 80)
        }
    }


}