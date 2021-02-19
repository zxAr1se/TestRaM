package com.example.testram.ui.adapter

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testram.R
import com.example.testram.data.Character
import com.example.testram.databinding.ItemListBinding
import com.example.testram.ui.activity.CharacterActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.*


class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var list: MutableList<Character> = LinkedList()

    fun setData(data: MutableList<Character>){
        val oldList = list
        val diffResult = DiffUtil.calculateDiff(
                CharacterDiffCallback(
                        oldList,
                        data
                )
        )

        diffResult.dispatchUpdatesTo(this)
        list = data
        notifyDataSetChanged()
    }

    fun deleteData(){
        list.clear()

        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(
            private val binding: ItemListBinding
            ): RecyclerView.ViewHolder(binding.root){

                fun bind(character: Character){
                    binding.nameCharacter.text = character.name
                    Glide.with(binding.root.context)
                        .load(character.image)
                        .into(binding.avatarCharacter)

                    binding.constraintLayout.setOnClickListener {

                        val intent = Intent(binding.root.context, CharacterActivity::class.java)

                        intent.putExtra("character", list[adapterPosition])

                        binding.root.context.startActivity(intent)
                    }

                }
            }


    class CharacterDiffCallback(
            val oldListCharacter: MutableList<Character>,
            val newListCharacter: MutableList<Character>
    ): DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldListCharacter.size

        override fun getNewListSize(): Int = newListCharacter.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldListCharacter[oldItemPosition] == newListCharacter[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldListCharacter[oldItemPosition] == newListCharacter[newItemPosition]
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
                ItemListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        holder.apply {
            bind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

}