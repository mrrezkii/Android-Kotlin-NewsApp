package com.newbiexpert.myapplication.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.newbiexpert.myapplication.R
import com.newbiexpert.myapplication.databinding.AdapterCategoryBinding
import com.newbiexpert.myapplication.source.news.CategoryModel

class CategoryAdapter(
    val categories: List<CategoryModel>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val items = arrayListOf<TextView>()

    class ViewHolder(val binding: AdapterCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onClick(category: CategoryModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.category.text = category.name
        items.add(holder.binding.category)
        holder.itemView.setOnClickListener {
            listener.onClick(category)
            setColor(holder.binding.category)
        }
        setColor(items[0])
    }

    override fun getItemCount() = categories.size

    private fun setColor(texView: TextView) {
        items.forEach {
            it.setBackgroundResource(R.color.white)
        }
        texView.setBackgroundResource(android.R.color.darker_gray)
    }

}