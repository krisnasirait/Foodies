package com.primetech.foodies.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.primetech.foodies.R
import com.primetech.foodies.activities.DetailActivity
import com.primetech.foodies.databinding.ItemCafeBinding
import com.primetech.foodies.networking.response.Data
import com.primetech.foodies.utils.Constants.Companion.DATA

class Adapter(
    var imageList: ArrayList<Data>
): RecyclerView.Adapter<Adapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: ItemCafeBinding = ItemCafeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<Data>){
        imageList = list
        notifyDataSetChanged()
    }

    inner class Holder(
        var binding: ItemCafeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val image = imageList[position]

            Glide.with(itemView.context).load(image.url)
                .placeholder(R.drawable.img_profile_cover_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.ivBar)
            binding.tvName.text = image.name

            itemView.setOnClickListener {
                var intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DATA,image)
                itemView.context.startActivity(intent)
            }

        }
    }
}