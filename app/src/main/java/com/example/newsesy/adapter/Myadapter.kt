package com.example.newsesy.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.example.Articles
import com.example.newsesy.R
class Myadapter(val list:ArrayList<Articles>,var context: Context,private val listner:OnNewsItemClickListener) : Adapter<Myadapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  {
       val view=LayoutInflater.from(parent.context)
           .inflate(R.layout.row_item,parent,false);
        return   ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
      holder.textview_news.text=list.get(position).title
      var s: String = list.get(position).publishedAt
        holder.textview_time.text=s.substring(s.indexOf('T')+1,s.length-4)
       Glide.with(context).load(list.get(position).urlToImage).into(holder.image)
    }
    override fun getItemCount(): Int {
      return  list.size
    }
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
      var textview_news:TextView
      var textview_time:TextView
      var image:ImageView
          init {
              textview_news=itemView.findViewById(R.id.news)
              textview_time=itemView.findViewById(R.id.time)
              image=itemView.findViewById(R.id.imageView)
              itemView.setOnClickListener(this)
              textview_news.setOnClickListener(this)
          }

        override fun onClick(p0: View?) {
            if(bindingAdapterPosition!=RecyclerView.NO_POSITION)
                listner.onitemClick(bindingAdapterPosition)
        }
    }
    interface OnNewsItemClickListener{
        fun onitemClick(pos:Int)

    }
}

