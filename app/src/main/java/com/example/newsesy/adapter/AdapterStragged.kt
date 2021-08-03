package com.example.newsesy.adapter

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.Articles
import com.example.newsesy.R
import com.example.newsesy.ui.home.HomeFragment
class AdapterStragged(val list:ArrayList<Articles>, var context: Context, private val listner: Myadapter.OnNewsItemClickListener) : RecyclerView.Adapter<AdapterStragged.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterStragged.ViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_straggedlayout,parent,false);
        return ViewHolder(view);
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textview_news.text=list.get(position).title
        var s: String = list.get(position).publishedAt!!
        val t:String=s.substring(s.indexOf('T')+1,s.length-4)!!
         holder.textview_time.text=s.substring(s.indexOf('T')+1,s.length-4)
        if(list.get(position).source?.name ==null)
            holder.textview_sources.text="Anonymous"
        else
        holder.textview_sources.text= list.get(position).source!!.name
        Glide.with(context).load(list.get(position).urlToImage).into(holder.image)
        holder.textview_news.setOnClickListener(View.OnClickListener { it->
            listner.onitemClick(position)
        })
        holder.card.setOnClickListener(View.OnClickListener { it->
            if(position!=RecyclerView.NO_POSITION)
                listner.onitemClick(position)
        })
    }
    override fun getItemCount(): Int {
        return  list.size;
    }
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        var textview_news:TextView
        var textview_time:TextView
        var textview_sources:TextView
        var image:ImageView
        var card:CardView
        init {
            textview_news=itemView.findViewById(R.id.news2)
            textview_time=itemView.findViewById(R.id.time2)
            image=itemView.findViewById(R.id.imageView2)
            card=itemView.findViewById(R.id.cardview)
            textview_sources=itemView.findViewById(R.id.source2)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?)
        {
           listner.onitemClick(bindingAdapterPosition)
        }
    }
    interface OnNewsItemClickListener{
        fun onitemClick(pos:Int)

    }
}
