package com.example.testram.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testram.databinding.ItemListEpisodeBinding
import java.util.*
import kotlin.collections.ArrayList

class EpisodeAdapter: RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private var list : List<String> = LinkedList()

    fun setData(data: List<String>) {
        val oldList = list
        val diffResult = DiffUtil.calculateDiff(
                EpisodeAdapter.EpisodeDiffCallback(
                        oldList,
                        data
                )
        )

        diffResult.dispatchUpdatesTo(this)
        list = data
        notifyDataSetChanged()
    }


    inner class EpisodeViewHolder(
            private val binding: ItemListEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(episode: String) {
            binding.episode.text = episode
        }
    }

    class EpisodeDiffCallback(
            val oldListEpisode: List<String>,
            val newListEpisode: List<String>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldListEpisode.size

        override fun getNewListSize(): Int = newListEpisode.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldListEpisode[oldItemPosition] == newListEpisode[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldListEpisode[oldItemPosition] == newListEpisode[newItemPosition]
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
                ItemListEpisodeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = list[position]

        holder.apply {
            bind(episode)
        }
    }

    override fun getItemCount(): Int = list.size
}