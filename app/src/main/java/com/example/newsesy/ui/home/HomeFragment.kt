package com.example.newsesy.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.example.Articles
import com.example.example.NewsModal
import com.example.newsesy.NewsActivity
import com.example.newsesy.R
import com.example.newsesy.adapter.AdapterStragged
import com.example.newsesy.adapter.Myadapter
import com.example.newsesy.viewmodel.NewsViewModelProviderFactory
import com.example.newsesy.viewmodel.ViewModel

class HomeFragment : Fragment(R.layout.fragment_home),AdapterStragged.OnNewsItemClickListener,Myadapter.OnNewsItemClickListener {
    var viewModel: ViewModel?=null
    lateinit var newsAdapter: AdapterStragged
    lateinit var list:MutableList<Articles>
    lateinit var rv:RecyclerView
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
         rv=root.findViewById(R.id.recycler_view)
         list= arrayListOf()
        val title: TextView? = activity?.findViewById(R.id.toolbar_text)
        if (title != null) {
            title.setText("TOP NEWS")
        };
        val newsViewModelProviderFactory= activity?.let { NewsViewModelProviderFactory(it.baseContext) }
        viewModel= newsViewModelProviderFactory?.let {
            ViewModelProvider(this,
                it
            ).get(ViewModel::class.java)
        }
        viewModel!!.getNews("in").observe(viewLifecycleOwner,androidx.lifecycle.Observer
        {
            if(it.isSuccessful())
            {
                var data:NewsModal?=it.body()
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
                    Toast.makeText(context,"ERROR",Toast.LENGTH_LONG).show();
                }
                setupRecyclerView()
            }
            else
            {
                Toast.makeText(context,"ERROR",Toast.LENGTH_LONG).show();
            }
        })
        return root;
    }
    private fun setupRecyclerView() {
        newsAdapter = AdapterStragged(list as ArrayList<Articles>, requireActivity().applicationContext,this)
       rv.apply {
           layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
           adapter = newsAdapter
       }
        }
    override  fun onitemClick(pos:Int)
    {
      var url=list.get(pos).url
      var imageurl=list.get(pos).urlToImage
      var title=list.get(pos).title
      var content=list.get(pos).content
      var description=list.get(pos).description
      var i:Intent =Intent(activity,NewsActivity::class.java)
        i.putExtra("url",url)
        i.putExtra("imageurl",imageurl)
        i.putExtra("title",title)
        i.putExtra("content",content)
        i.putExtra("description",description)
        i.putExtra("object",list.get(pos))
       startActivity(i)
    }

}



