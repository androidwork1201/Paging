package com.example.pagingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingdemo.adapter.RepoAdapter
import com.example.pagingdemo.databinding.ActivityMainBinding
import com.example.pagingdemo.databinding.LayoutItemBinding
import com.example.pagingdemo.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    private val repoAdapter = RepoAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = repoAdapter

        lifecycleScope.launch {
            viewModel.getPagingData().collect() { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }

        repoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    binding.card.visibility = View.INVISIBLE
                    binding.recycler.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    binding.card.visibility = View.VISIBLE
                    binding.recycler.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    binding.card.visibility = View.INVISIBLE
                    Toast.makeText(this, "${state.error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}