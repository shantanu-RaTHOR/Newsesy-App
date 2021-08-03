package com.example.newsesy.ui.saved

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.example.Articles
import com.example.newsesy.NewsActivity
import com.example.newsesy.R
import com.example.newsesy.adapter.AdapterStragged
import com.example.newsesy.adapter.Myadapter
import com.example.newsesy.viewmodel.NewsViewModelProviderFactory
import com.example.newsesy.viewmodel.ViewModel

class Saved : Fragment() ,Myadapter.OnNewsItemClickListener {
    var viewModel: ViewModel?=null
    lateinit var list:List<Articles>
    lateinit var rv: RecyclerView
    lateinit var newsAdapter: Myadapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_save, container, false)
        val title: TextView? = activity?.findViewById(R.id.toolbar_text)
        title?.setText("SAVED ITEMS")
        rv=root.findViewById(R.id.rv_saved)
        val newsViewModelProviderFactory= activity?.let { NewsViewModelProviderFactory(it.baseContext) }
        viewModel= newsViewModelProviderFactory?.let {
            ViewModelProvider(this,
                it
            ).get(ViewModel::class.java)
        }
        viewModel?.getSavedNews()
        viewModel?.savedNews?.observe(viewLifecycleOwner,androidx.lifecycle.Observer
        {
            list=it

            setupRecyclerView()
        })
        return root
    }
    private fun setupRecyclerView() {
        var newsAdapter: AdapterStragged=AdapterStragged(list as ArrayList<Articles>, requireActivity().applicationContext,this)
        rv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
        }
    }
    override fun onitemClick(pos: Int) {
        var url=list.get(pos).url
        var imageurl=list.get(pos).urlToImage
        var title=list.get(pos).title
        var content=list.get(pos).content
        var description=list.get(pos).description
        var i: Intent = Intent(activity, NewsActivity::class.java)
        i.putExtra("url",url)
        i.putExtra("imageurl",imageurl)
        i.putExtra("title",title)
        i.putExtra("content",content)
        i.putExtra("description",description)
        i.putExtra("object",list.get(pos))
        startActivity(i)
    }
}