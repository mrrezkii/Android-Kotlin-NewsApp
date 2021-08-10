package com.newbiexpert.myapplication.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.newbiexpert.myapplication.R
import com.newbiexpert.myapplication.databinding.ActivityDetailBinding
import com.newbiexpert.myapplication.databinding.CustomToolbarBinding
import com.newbiexpert.myapplication.source.news.ArticleModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory { DetailActivity() }
}

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: DetailViewModel by viewModel()
    private val detail by lazy {
        intent.getSerializableExtra("intent_detail") as ArticleModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingToolbar = binding.toolbar
        setContentView(binding.root)

        setSupportActionBar(bindingToolbar.container)
        supportActionBar!!.apply {
            title = " "
            setDisplayHomeAsUpEnabled(true)
        }

        detail.let {
            viewModel.find(it)
            val web = binding.webView
            web.loadUrl(it.url!!)
            web.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressTop.visibility = View.GONE
                }
            }
            val settings = binding.webView.settings
            settings.javaScriptCanOpenWindowsAutomatically = false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_boomark, menu)
        val menuBookmark = menu!!.findItem(R.id.action_bookmark)
        menuBookmark.setOnMenuItemClickListener {
            viewModel.bookMark(detail)
            true
        }
        viewModel.isBookmark.observe(this, Observer {
            if (it == 0) menuBookmark.setIcon(R.drawable.ic_add)
            else menuBookmark.setIcon(R.drawable.ic_check)
        })
        return super.onCreateOptionsMenu(menu)
    }
}