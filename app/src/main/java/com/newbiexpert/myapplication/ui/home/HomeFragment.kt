package com.newbiexpert.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.newbiexpert.myapplication.databinding.CustomToolbarBinding
import com.newbiexpert.myapplication.databinding.FragmentHomeBinding
import com.newbiexpert.myapplication.source.news.CategoryModel
import com.newbiexpert.myapplication.ui.news.CategoryAdapter
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
        bindingToolbar.textTitle.text = viewModel.title
        binding.listCategory.adapter = categoryAdapter
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.categories, object : CategoryAdapter.OnAdapterListener {
            override fun onClick(category: CategoryModel) {
                Timber.e(category.id)
            }

        })
    }

}