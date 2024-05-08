package com.example.englishease.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.englishease.R

class QuestionAdapter(val questions: List<String>, val variants: List<String>, val listener: QuestionAdapter.OnItemClickListener )
    : RecyclerView.Adapter<QuestionAdapter.MyQuestion>() {

    class MyQuestion(item: View) : RecyclerView.ViewHolder(item) {
        val questionConstraintLayout: ConstraintLayout = item.findViewById(R.id.question_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.MyQuestion {
        val myItem = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionAdapter.MyQuestion(myItem)
    }

    override fun onBindViewHolder(holder: QuestionAdapter.MyQuestion, position: Int) {
        val questionName = questions[position]
//        val questionVarOne = variants[position]
//        val questionVarTwo = variants[position]
//        val questionVarThree = variants[position]
        holder.questionConstraintLayout.findViewById<TextView>(R.id.textViewQuestDeclaration).text = questionName
//        holder.questionConstraintLayout.findViewById<RadioButton>(R.id.radioButton_1).text =
//            questionVarOne
//        holder.questionConstraintLayout.findViewById<RadioButton>(R.id.radioButton_2).text =
//        holder.questionConstraintLayout.findViewById<RadioButton>(R.id.radioButton_3).text =

    //        holder.itemView.setOnClickListener {
//            listener.onItemClickListener(list[position])
//        }
    }

    override fun getItemCount() = questions.size

    interface OnItemClickListener {
        fun onItemClickListener(lessonName: String)
    }
}