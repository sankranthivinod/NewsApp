package com.sankranthivinod.newsapp.ui


import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sankranthivinod.newsapp.databinding.ActivityNewsBinding
import kotlinx.coroutines.launch

class NewsActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var binding: ActivityNewsBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        observeViewModel()
        viewModel.loadNews()
    }
    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(articles = emptyList(), loadImage = {
            imageView, url ->
            Glide.with(this).load(url).into(imageView)
        }, onItemClick = { article ->
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            Toast.makeText(this, "on Click", Toast.LENGTH_SHORT).show()

        })
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = newsAdapter
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.news.collect {state ->
                    when(state){
                        is NewsUiState.Loading -> binding.progressBar.isVisible = true
                        is NewsUiState.Success -> {
                            binding.progressBar.isVisible = false
                            newsAdapter.updateList(state.news)
                        }
                        is NewsUiState.Error -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(this@NewsActivity, state.message, Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }
    }

}
