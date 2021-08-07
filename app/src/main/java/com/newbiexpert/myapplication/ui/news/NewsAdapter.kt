package com.newbiexpert.myapplication.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newbiexpert.myapplication.databinding.AdapterNewsBinding
import com.newbiexpert.myapplication.source.news.ArticleModel
import com.newbiexpert.myapplication.util.DateUtil

class NewsAdapter(
    val articles: ArrayList<ArticleModel>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val binding: AdapterNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleModel) {
            binding.article = article
            binding.format = DateUtil()
        }
    }

    interface OnAdapterListener {
        fun onClick(category: ArticleModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
            listener.onClick(article)
        }
    }

    override fun getItemCount() = articles.size

    fun add(data: List<ArticleModel>) {
        articles.clear()
        articles.addAll(data)
        notifyDataSetChanged()
    }

}