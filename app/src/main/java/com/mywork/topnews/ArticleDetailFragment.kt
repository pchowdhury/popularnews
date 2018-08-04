package com.mywork.topnews

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import kotlinx.android.synthetic.main.feed_detail.view.*

/**
 * A fragment representing a single Feed detail screen.
 * This fragment is either contained in a [PopularNewsActivity]
 * in two-pane mode (on tablets) or a [ArticleDetailActivity]
 * on handsets.
 */
class ArticleDetailFragment : Fragment() {

    private var url: String? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        arguments?.let {
            if (it.containsKey(ARG_ITEM_URL)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                url = it.getString(ARG_ITEM_URL)
                title = it.getString(ARG_ITEM_TITLE)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.feed_detail, container, false)
        val webView = rootView.webview
        configureWebView(webView)
        webView.loadUrl(url)
        return rootView
    }

    private fun configureWebView(webview: WebView) {
        webview.settings.javaScriptEnabled =true
    }

    companion object {
        const val ARG_ITEM_URL = "item_url"
        const val ARG_ITEM_TITLE = "item_title"
    }
}
