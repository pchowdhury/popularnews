package com.mywork.topnews

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mywork.topnews.model.Article
import kotlinx.android.synthetic.main.feed_list_content.view.*


/**
 * A fragment representing a single Feed detail screen.
 * This fragment is either contained in a [PopularNewsActivity]
 * in two-pane mode (on tablets) or a [ArticleDetailActivity]
 * on handsets.
 */
class ArticleListFragment : Fragment() {

    companion object {
        val ARG_SELECTED_INDEX = "selectedIndex"
    }
    var listener: AppCommunicator? = null
    var recyclerView: RecyclerView? = null
    var selctedItemIndex = -1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.feed_list, container, false)
        recyclerView = rootView.findViewById(R.id.feed_list)
        val manager = LinearLayoutManager(context)
        recyclerView?.layoutManager = manager
        val dividerItemDecoration = DividerItemDecoration(context,
                manager.orientation)
        recyclerView?.addItemDecoration(dividerItemDecoration)
        if (arguments != null) {
            selctedItemIndex = arguments!!.getInt(ARG_SELECTED_INDEX, -1)
        }
        loadList()
        return rootView
    }

    fun loadList() {
        if (listener != null && recyclerView != null) {
            setupRecyclerView(recyclerView!!, listener!!.getArticles())
            recyclerView?.postDelayed({showDefault()},200)
        }
    }

    private fun showDefault() {
        if (listener!!.hasTwoPane() && selctedItemIndex == -1 && recyclerView!!.childCount > 0) {
            selctedItemIndex = 0
        }
        if (selctedItemIndex != -1) {
            recyclerView?.getChildAt(selctedItemIndex)?.performClick()
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, list: List<Article>) {
        if (activity != null) {
            recyclerView.adapter = SimpleItemRecyclerViewAdapter(list)
        }
    }

    inner class SimpleItemRecyclerViewAdapter(private val values: List<Article>) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Article
                listener!!.showDetails(item.title, item.url)
                selctedItemIndex = values.indexOf(item)
                notifyDataSetChanged()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.feed_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.titleView.text = item.title
            holder.sourceView.text = item.source
            holder.dateView.text = item.published_date
            if (item.media.size > 0 && item.media[0].type != null
                    && item.media[0].type.equals("image"))
                if (item.media[0].mediaMetaData.size > 0)
                    Glide.with(activity)
                            .load(item.media[0].mediaMetaData[0].url)
                            .placeholder(R.drawable.ic_add_a_photo)
                            .into(holder.imageView)
            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
                holder.itemView.setBackgroundColor(when (position == selctedItemIndex) { true -> {
                    Color.LTGRAY
                }
                    false -> {
                        Color.TRANSPARENT
                    }
                })
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imageView: ImageView = view.img_view
            val titleView: TextView = view.txt_title
            val sourceView: TextView = view.txt_source
            val dateView: TextView = view.txt_date
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = context as AppCommunicator
        } catch (e: Exception) {

        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
