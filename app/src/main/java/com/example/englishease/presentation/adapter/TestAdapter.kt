package com.example.englishease.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.englishease.R

class TestAdapter(val list: List<String>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<TestAdapter.MyTest>() {

    class MyTest(item: View) : RecyclerView.ViewHolder(item) {
        //        val textView: TextView = item.findViewById(R.id.test_item_text_view)
        val cardView: CardView = item.findViewById(R.id.test_card)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTest {
        val myItem = LayoutInflater.from(parent.context).inflate(R.layout.theory_item, parent, false)
        return MyTest(myItem)
    }

    override fun onBindViewHolder(holder: MyTest, position: Int) {
        val text = list[position]
//        holder.textView.text = text
        holder.cardView.findViewById<TextView>(R.id.theory_item_text_view).text = text
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(list[position])
        }
    }

    override fun getItemCount() = list.size

    interface OnItemClickListener {
        fun onItemClickListener(testName: String)
    }

}