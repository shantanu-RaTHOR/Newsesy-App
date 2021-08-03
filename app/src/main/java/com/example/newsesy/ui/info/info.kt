package com.example.newsesy.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.newsesy.R

class info : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_info, container, false)
        val title: TextView? = activity?.findViewById(R.id.toolbar_text)
        if (title != null) {
            title.setText("ABOUT")
        }
        return root
    }
}