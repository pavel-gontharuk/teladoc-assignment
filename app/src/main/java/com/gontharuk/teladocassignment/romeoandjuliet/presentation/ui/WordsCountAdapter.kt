package com.gontharuk.teladocassignment.romeoandjuliet.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gontharuk.teladocassignment.databinding.WordsCountItemBinding
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountItem

class WordsCountAdapter : RecyclerView.Adapter<WordsCountAdapter.ViewHolder>() {

    private val items: ArrayList<WordsCountItem> = ArrayList()

    fun update(list: List<WordsCountItem>) {
        items.clear()
        items.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            WordsCountItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.getOrNull(position) ?: return
        holder.word.text = item.word
        holder.count.text = item.count.toString()
    }

    inner class ViewHolder(
        binding: WordsCountItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val word: TextView = binding.tvWord
        val count: TextView = binding.tvCount
    }
}