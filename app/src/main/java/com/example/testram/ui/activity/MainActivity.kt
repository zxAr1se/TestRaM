package com.example.testram.ui.activity


import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testram.R
import com.example.testram.data.Character
import com.example.testram.databinding.ActivityMainBinding
import com.example.testram.databinding.DialogInternetBinding
import com.example.testram.di.App
import com.example.testram.ui.adapter.CharacterAdapter
import com.example.testram.ui.presenter.CharacterListView
import com.example.testram.ui.presenter.CharacterPresenter
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : MvpAppCompatActivity(), CharacterListView {



    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenterProvider: Provider<CharacterPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }


    private val characterAdapter = CharacterAdapter()
    private lateinit var layoutManager : LinearLayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        App().component.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpAdapter()
        fetchAndScrollDown()
        refreshList()
        presenter.pushCharacters(refresh = false)
    }


    private fun setUpAdapter() {
        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = characterAdapter
    }

    private fun fetchAndScrollDown() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0){
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val mTotal = characterAdapter.itemCount

                    if ((visibleItemCount + pastVisibleItem) >= mTotal){
                        presenter.pushCharacters(refresh = false)
                    }
                }
            }
        })
    }

    private fun refreshList() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            characterAdapter.deleteData()

            presenter.pushCharacters(refresh = true)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun success(list: MutableList<Character>) {
        binding.progressBar.visibility = View.INVISIBLE
        binding.recyclerView.visibility = View.VISIBLE

        characterAdapter.setData(data = list)
    }

    override fun loading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.INVISIBLE
    }

    override fun error(msg: String) {
        Snackbar.make(
                binding.root,
                msg,
                Snackbar.LENGTH_LONG
        ).show()
    }

}