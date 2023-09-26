package com.gontharuk.teladocassignment.twistandshout.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gontharuk.teladocassignment.databinding.TwistItemBinding
import com.gontharuk.teladocassignment.twistandshout.presentation.entity.TwistItem

class TwistAdapter : RecyclerView.Adapter<TwistAdapter.ViewHolder>() {

    private val items: ArrayList<TwistItem> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<TwistItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TwistItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.albumName.text = items[position].albumName
    }

    inner class ViewHolder(
        binding: TwistItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val albumName: TextView = binding.tvAlbumName
    }
}