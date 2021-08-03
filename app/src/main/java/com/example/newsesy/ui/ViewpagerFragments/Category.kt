package com.example.newsesy.ui.ViewpagerFragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Articles
import com.example.example.NewsModal
import com.example.newsesy.NewsActivity
import com.example.newsesy.R
import com.example.newsesy.adapter.AdapterStragged
import com.example.newsesy.adapter.Myadapter
import com.example.newsesy.viewmodel.NewsViewModelProviderFactory
import com.example.newsesy.viewmodel.ViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
class Category(var category:String): Fragment(), Myadapter.OnNewsItemClickListener,AdapterStragged.OnNewsItemClickListener{


    var viewModel: ViewModel?=null
    lateinit var newsAdapter: Myadapter
    lateinit var list:MutableList<Articles>
    lateinit var rv:RecyclerView
    var flag:Int=0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_category, container, false)
        rv=root.findViewById(R.id.recycler_view)
        list= arrayListOf()
        var fab: FloatingActionButton =root.findViewById(R.id.fab_id)
        val newsViewModelProviderFactory= activity?.let { NewsViewModelProviderFactory(it.applicationContext) }
        viewModel= newsViewModelProviderFactory?.let {
            ViewModelProvider(this,
                it
            ).get(ViewModel::class.java)
        }
        viewModel!!.getNewsCategoryWise(category).observe(viewLifecycleOwner,androidx.lifecycle.Observer
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

     fab.setOnClickListener(View.OnClickListener { it->
         flag=(flag+1)%2
         setupRecyclerView()
     })

        return root;
    }
    private fun setupRecyclerView() {
        newsAdapter = Myadapter(list as ArrayList<Articles>, requireActivity().applicationContext,this)
        var newsAdapter2: AdapterStragged=AdapterStragged(list as ArrayList<Articles>, requireActivity().applicationContext,this)
        var newsadapter3= AdapterStragged(list as ArrayList<Articles>, requireActivity().applicationContext,this)
        rv.apply {
            if(flag==1) {
                layoutManager = LinearLayoutManager(activity)
                adapter = newsAdapter
            }
            else
            {
                layoutManager = LinearLayoutManager(activity)
                adapter = newsAdapter2
            }

        }
    }
    override fun onitemClick(pos: Int) {
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
