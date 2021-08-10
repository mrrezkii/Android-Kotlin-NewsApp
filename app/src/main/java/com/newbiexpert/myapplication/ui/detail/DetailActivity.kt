package com.newbiexpert.myapplication.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.newbiexpert.myapplication.R
import com.newbiexpert.myapplication.databinding.ActivityDetailBinding
import com.newbiexpert.myapplication.databinding.CustomToolbarBinding
import com.newbiexpert.myapplication.source.news.ArticleModel

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var bindingToolbar: CustomToolbarBinding
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
            Toast.makeText(applicationContext, "add bookmark", Toast.LENGTH_SHORT).show()
            menuBookmark.setIcon(R.drawable.ic_check)
            true
        }
        return super.onCreateOptionsMenu(menu)
    }
}