package com.newbiexpert.myapplication.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newbiexpert.myapplication.databinding.AdapterHeadlineBinding
import com.newbiexpert.myapplication.databinding.AdapterNewsBinding
import com.newbiexpert.myapplication.source.news.ArticleModel
import com.newbiexpert.myapplication.util.DateUtil

private const val HEADLINES = 1
private const val NEWS = 2

class NewsAdapter(
    val articles: ArrayList<ArticleModel>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        var VIEW_TYPES = HEADLINES
    }

    class ViewHolderNews(val binding: AdapterNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleModel) {
            binding.article = article
            binding.format = DateUtil()
        }
    }

    class ViewHolderHeadlines(val binding: AdapterHeadlineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleModel) {
            binding.article = article
            binding.format = DateUtil()
        }
    }

    interface OnAdapterListener {
        fun onClick(category: ArticleModel)
    }

    override fun getItemViewType(position: Int) = VIEW_TYPES

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADLINES) {
            ViewHolderHeadlines(
                AdapterHeadlineBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else ViewHolderNews(
            AdapterNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = articles[position]
        if (VIEW_TYPES == HEADLINES) (holder as ViewHolderHeadlines).bind(article)
        else (holder as ViewHolderNews).bind(article)
        holder.itemView.setOnClickListener {
            listener.onClick(article)
        }
    }

    override fun getItemCount() = articles.size

    fun add(data: List<ArticleModel>) {
        articles.addAll(data)
        notifyItemRangeInserted((articles.size - data.size), data.size)
    }

    fun clear() {
        articles.clear()
        notifyDataSetChanged()
    }

}