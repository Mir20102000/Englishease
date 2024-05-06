package com.example.englishease.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.englishease.R

class LessonAdapter(val list: List<String>, val lessonDescs: List<String>, val listener: LessonAdapter.OnItemClickListener) :
    RecyclerView.Adapter<LessonAdapter.MyLesson>(){

        class MyLesson(item: View) : RecyclerView.ViewHolder(item) {
//            val textView: TextView = item.findViewById(R.id.lesson_item_title)
            val cardView: CardView = item.findViewById(R.id.lesson_card)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonAdapter.MyLesson {
        val myItem = LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false)
        return LessonAdapter.MyLesson(myItem)
    }

    override fun onBindViewHolder(holder: LessonAdapter.MyLesson, position: Int) {
        val text = list[position]
        val lessonName = list[position]
        val lessonDescs = lessonDescs[position]
//        holder.textView.text = text
        holder.cardView.findViewById<TextView>(R.id.lesson_item_title).text = text
        holder.cardView.findViewById<TextView>(R.id.lesson_item_desc).text = lessonDescs
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(list[position])
        }
    }

    override fun getItemCount() = list.size

    interface OnItemClickListener {
        fun onItemClickListener(lessonName: String)
    }
}