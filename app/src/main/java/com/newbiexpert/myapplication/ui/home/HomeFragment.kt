package com.newbiexpert.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.newbiexpert.myapplication.databinding.CustomToolbarBinding
import com.newbiexpert.myapplication.databinding.FragmentHomeBinding
import com.newbiexpert.myapplication.source.news.ArticleModel
import com.newbiexpert.myapplication.source.news.CategoryModel
import com.newbiexpert.myapplication.ui.news.CategoryAdapter
import com.newbiexpert.myapplication.ui.news.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import timber.log.Timber

val homeModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        bindingToolbar.title = viewModel.title

        binding.listCategory.adapter = categoryAdapter
        binding.listNews.adapter = newsAdapter

        viewModel.category.observe(viewLifecycleOwner, Observer {
            Timber.e(it)
            viewModel.fetch()
        })

        viewModel.news.observe(viewLifecycleOwner, Observer {
            Timber.e(it.articles.toString())
            binding.imageAlert.visibility = if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            binding.textAlert.visibility = if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            newsAdapter.add(it.articles)
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private val newsAdapter by lazy {
        NewsAdapter(arrayListOf(), object : NewsAdapter.OnAdapterListener {
            override fun onClick(category: ArticleModel) {

            }

        })
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.categories, object : CategoryAdapter.OnAdapterListener {
            override fun onClick(category: CategoryModel) {
                viewModel.category.postValue(category.id)
            }

        })
    }

}