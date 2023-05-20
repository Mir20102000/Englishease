package com.example.englishease.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englishease.R

class TheoryAdapter(val list: List<String>,  val listener: TheoryAdapter.OnItemClickListener) :
    RecyclerView.Adapter<TheoryAdapter.MyTheory>(){

        class MyTheory(item: View) : RecyclerView.ViewHolder(item) {
            val textView: TextView = item.findViewById(R.id.theory_item_text_view)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTheory {
        val myItem = LayoutInflater.from(parent.context).inflate(R.layout.theory_item, parent, false)
        return MyTheory(myItem)
    }

    override fun onBindViewHolder(holder: MyTheory, position: Int) {
        val text = list[position]
        holder.textView.text = text
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(list[position])
        }
    }

    override fun getItemCount() = list.size

    interface OnItemClickListener {
        fun onItemClickListener(theoryName: String)
    }
}

