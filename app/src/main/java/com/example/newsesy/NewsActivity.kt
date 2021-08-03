package com.example.newsesy
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.example.Articles
import com.example.example.Source
import com.example.newsesy.viewmodel.NewsViewModelProviderFactory
import com.example.newsesy.viewmodel.ViewModel

class NewsActivity : AppCompatActivity()
{
    var viewModel: ViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        var close_button=findViewById<ImageView>(R.id.close_button)
        var save_button=findViewById<Button>(R.id.save_article)
        var visit_button=findViewById<Button>(R.id.visit_article)
        var url =getIntent().getStringExtra("url")
        var imageurl=getIntent().getStringExtra("imageurl")
        var title=getIntent().getStringExtra("title")
        var content=getIntent().getStringExtra("content")
        var des=getIntent().getStringExtra("description")
        val newsViewModelProviderFactory= NewsViewModelProviderFactory(applicationContext)
        viewModel= ViewModelProvider(this,newsViewModelProviderFactory).get(ViewModel::class.java)
        Glide.with(applicationContext).load(imageurl).into(findViewById(R.id.newsimage))
        findViewById<TextView>(R.id.newstitle).text=title
        findViewById<TextView>(R.id.newscontent).text=content
        findViewById<TextView>(R.id.newsdescription).text=des
        close_button.setOnClickListener(View.OnClickListener {
                onBackPressed()
        })

        save_button.setOnClickListener(View.OnClickListener {
            var obj:Articles= getIntent().getSerializableExtra("object") as Articles
            if(obj.author==null)
                obj.author="anonymous"
            if(obj.content==null)
                obj.content="No content available"
            if(obj.description==null)
                obj.description="No des available"
            if(obj.title==null)
                obj.title="No title available"
            viewModel!!.upsert(obj)
            Toast.makeText(applicationContext,"ARTICLE SAVED",Toast.LENGTH_LONG).show()
        })

        visit_button.setOnClickListener(View.OnClickListener {
           val intent=Intent(Intent.ACTION_VIEW)
           intent.data= Uri.parse(url)
           startActivity(intent)
        })


    }


    override fun onBackPressed() {
        super.onBackPressed()
    }
}


