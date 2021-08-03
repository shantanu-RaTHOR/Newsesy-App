package com.example.newsesy.ui.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Articles
import com.example.example.NewsModal
import com.example.newsesy.NewsActivity
import com.example.newsesy.R
import com.example.newsesy.adapter.Myadapter
import com.example.newsesy.viewmodel.NewsViewModelProviderFactory
import com.example.newsesy.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(), Myadapter.OnNewsItemClickListener {
    var viewModel: ViewModel?=null
    lateinit var newsAdapter: Myadapter
    lateinit var list:MutableList<Articles>
    lateinit var rv: RecyclerView
    lateinit var etSearch:SearchView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        etSearch=root.findViewById(R.id.etSearch)
        val title: TextView? = activity?.findViewById(R.id.toolbar_text)
        if (title != null) {
            title.setText("SEARCH NEWS")
        };
        list= arrayListOf()
        val newsViewModelProviderFactory= activity?.let { NewsViewModelProviderFactory(it.applicationContext) }
        viewModel= newsViewModelProviderFactory?.let {
            ViewModelProvider(this,
                it
            ).get(ViewModel::class.java)
        }
        rv=root.findViewById(R.id.rvSearchNews)
        var job: Job? = null
        etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                job?.cancel()
                job = MainScope().launch {
                    delay(1000)
                    newText?.let {
                        if(newText.isNotEmpty()) {
                            viewModel!!.searchNews(newText)
                        }
                    }
                }
                return false
            }
        })
        viewModel!!.searchNews.observe(viewLifecycleOwner,androidx.lifecycle.Observer
        {
            if(it.isSuccessful())
            {
                var data: NewsModal?=it.body()
                if(data!=null)
                {
                    for(articles in data.articles)
                    {
                        if(articles.urlToImage!=null)
                        {
                            list.add(articles)
                        }
                    }
                }
                else
                {
                    Toast.makeText(context,"ERROR", Toast.LENGTH_LONG).show();
                }
                setupRecyclerView()
            }
            else
            {
                Toast.makeText(context,"ERROR", Toast.LENGTH_LONG).show();
            }
        })
        return root
    }
    private fun setupRecyclerView() {
        newsAdapter = Myadapter(list as ArrayList<Articles>, requireActivity().applicationContext,this)
        rv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
        }
    }
    override fun onitemClick(pos: Int)
    {
        var url=list.get(pos).url
        var imageurl=list.get(pos).urlToImage
        var title=list.get(pos).title
        var content=list.get(pos).content
        var description=list.get(pos).description
        var i:Intent =Intent(activity, NewsActivity::class.java)
        i.putExtra("url",url)
        i.putExtra("imageurl",imageurl)
        i.putExtra("title",title)
        i.putExtra("content",content)
        i.putExtra("description",description)
        i.putExtra("object",list.get(pos))
        startActivity(i)
    }
}

