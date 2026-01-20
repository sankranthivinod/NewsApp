package com.sankranthivinod.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sankranthivinod.newsapp.data.dto.Article
import com.sankranthivinod.newsapp.databinding.ItemNewsBinding

class NewsAdapter(
    private var articles: List<Article>,
    private val loadImage: (ImageView, String?) -> Unit,
    private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.apply {
            title.text = article.title
            description.text = article.description ?: ""
            loadImage(imageView, article.urlToImage)
            root.setOnClickListener { onItemClick(article) }
        }
    }

    override fun getItemCount() = articles.size

    fun updateList(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }
}
