package org.techtown.imagesearchmvvm

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.imagesearchmvvm.databinding.ItemMainImageBinding

class MainSearchRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    data class ImageItem(var imageUrl:String , var documentUrl:String)

    private val imageItemList = ArrayList<ImageItem>()

    //뷰 홀더에 맞게 인플레이트 하는거라 생각 -> 이게 binding 해서도 가능
    //binding 대신 inflate 해서 넘겨줌
    //왜 ? binding을 써야 접근 가능?
    class ImageHolder(binding : ItemMainImageBinding):RecyclerView.ViewHolder(binding.root){
        val _binding = binding
        fun onBind(item:ImageItem){
            //ViewHolder가 가지고 있는 itemView의 context넘겨줌
            itemView.run {
                Glide.with(context)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .into(_binding.itemMainImageView)
                _binding.itemMainImageView.setOnClickListener {
                    ContextCompat.startActivity(context , Intent(Intent.ACTION_VIEW , Uri.parse(item.documentUrl)), null)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMainImageBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ImageHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ImageHolder)?.onBind(imageItemList[position])
    }

    override fun getItemCount() = imageItemList.size

    fun addItem(imgaeUrl : String ,documentUrl : String){
        imageItemList.add(ImageItem(imgaeUrl , documentUrl))
    }

    fun clearItems(){
        imageItemList.clear()
    }
}