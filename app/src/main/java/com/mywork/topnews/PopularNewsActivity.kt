package com.mywork.topnews

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.mywork.topnews.model.Article
import com.mywork.topnews.viewmodel.ArticleViewModel
import com.mywork.topnews.model.ModelResponse
import kotlinx.android.synthetic.main.activity_news.*


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ArticleDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class PopularNewsActivity : AppCompatActivity(), AppCommunicator {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private val TAG_LIST = "list"
    private val TAG_DETAIL = "detail"

    private var twoPane: Boolean = false
    lateinit var viewModel: ArticleViewModel
    var lastIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        resumeState(savedInstanceState)
        if (feed_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        viewModel.feedsResponse.observe(this, Observer { response -> onReceiveUpdate(response) })
        viewModel.loadArticles()
    }

    private fun resumeState(savedInstanceState: Bundle?){
        if (savedInstanceState != null) {
            lastIndex = savedInstanceState.getInt(ArticleListFragment.ARG_SELECTED_INDEX, -1)
        }
    }

    private fun onReceiveUpdate(response: ModelResponse?) {
        if (response != null) {
            when (response.status) {
                ModelResponse.Loading -> {
                    showProgress(true)
                }
                ModelResponse.Error -> {
                    showProgress(false)
                }
                ModelResponse.Success -> {
                    showProgress(false)
                    showList()
                }
            }
        }
    }

    fun showProgress(show: Boolean) {
        progress.visibility = when (show) {true -> View.VISIBLE
            else -> View.GONE
        }
    }

    override fun onBackPressed() {
        if(!hasTwoPane()) {
            showBackButton(!isShowingDetails())
        }
        super.onBackPressed()
    }

    private fun showBackButton(show:Boolean){
        if(!show){
            supportActionBar?.title = getString(R.string.app_name)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
        supportActionBar?.setDisplayShowHomeEnabled(show)
    }
    private fun isShowingDetails(): Boolean =
            supportFragmentManager.findFragmentByTag(TAG_DETAIL) !=null

    override fun hasTwoPane() = findViewById<View>(R.id.feed_detail_container) != null

    override fun showDetails(title:String?, link: String?) {
        val fragment = ArticleDetailFragment().apply {
            arguments = Bundle().apply {
                                putString(ArticleDetailFragment.ARG_ITEM_TITLE, title)
                putString(ArticleDetailFragment.ARG_ITEM_URL, link)
            }
        }
        if (hasTwoPane()) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.feed_detail_container, fragment, TAG_DETAIL)
                    .commit()
        } else {
            supportActionBar?.title = title
            showBackButton(true)
            supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.container, fragment, TAG_DETAIL)
                    .commit()
        }
    }

    private fun showList() {
        val fragment = ArticleListFragment().apply {
            arguments = Bundle().apply {
                putInt(ArticleListFragment.ARG_SELECTED_INDEX, lastIndex)
            }
        }
        supportActionBar?.title = getString(R.string.app_name)
        showBackButton(false)
        fragment.loadList()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment, TAG_LIST)
                .commit()
    }

    override fun getArticles(): List<Article> =
            if (viewModel.feedsResponse.value != null && viewModel.feedsResponse.value!!.status == ModelResponse.Success) {
                viewModel.feedsResponse.value!!.value
            } else {
                mutableListOf()
            }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        var lastIndex = -1
        val f = supportFragmentManager.findFragmentByTag(TAG_LIST)
        if(f!=null){
            lastIndex = (f as ArticleListFragment).selctedItemIndex
        }
        outState?.putInt(ArticleListFragment.ARG_SELECTED_INDEX, lastIndex)
    }
}
