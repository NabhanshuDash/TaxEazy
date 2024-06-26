package com.example.taxeazy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taxeazy.databinding.ItemImageBinding
import com.squareup.picasso.Picasso

class ImageAdapter(private var mList: List<String>) :
    RecyclerView.Adapter<ImageAdapter.ImagesViewHolder>() {

    inner class ImagesViewHolder(var binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        with(holder.binding){
            with(mList[position]){
                Picasso.get().load(this).into(imageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}