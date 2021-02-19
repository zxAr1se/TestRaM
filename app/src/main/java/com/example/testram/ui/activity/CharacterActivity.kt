package com.example.testram.ui.activity



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.testram.R
import com.example.testram.data.Character
import com.example.testram.databinding.ActivityCharacterBinding
import com.example.testram.ui.adapter.EpisodeAdapter



class CharacterActivity : AppCompatActivity() {

    private var _binding: ActivityCharacterBinding? = null
    private val binding get() = _binding!!

    private val mAdapter = EpisodeAdapter()
    private lateinit var layoutManager : LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        val intent = getIntent()
        val character = intent.getParcelableExtra<Character>("character")

        binding.name.text = character?.name
        binding.status.text = character?.status
        binding.species.text = character?.species
        binding.type.text = character?.type
        binding.gender.text = character?.gender
        val episode = character?.episode



        Glide.with(binding.root.context)
                .load(character?.image)
                .into(binding.avatar)

        when {
            character?.status.toString() == "Alive" -> {
                Glide.with(binding.root.context)
                    .load(R.drawable.frame_alive_status)
                    .into(binding.statusColor)
            }
            character?.status.toString() == "Dead" -> {
                Glide.with(binding.root.context)
                    .load(R.drawable.frame_dead_status)
                    .into(binding.statusColor)
            }
            else -> { Glide.with(binding.root.context)
                .load(R.drawable.frame_unknown_status)
                .into(binding.statusColor)
            }
        }

        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = mAdapter

        mAdapter.setData(episode!!)
    }
}