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
//import com.example.englishease.data.models.Question

import com.example.englishease.domain.models.Question

class QuestionAdapter(val questions: List<Question>, val listener: QuestionAdapter.OnItemClickListener )
    : RecyclerView.Adapter<QuestionAdapter.MyQuestion>() {

    class MyQuestion(item: View) : RecyclerView.ViewHolder(item) {
        val questionConstraintLayout: ConstraintLayout = item.findViewById(R.id.question_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.MyQuestion {
        val myItem = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionAdapter.MyQuestion(myItem)
    }

    override fun onBindViewHolder(holder: QuestionAdapter.MyQuestion, position: Int) {
        val questionText = questions[position].questionText
        val questionVariants = questions[position].variants
        val questionPoints = questions[position].points

        holder.questionConstraintLayout.findViewById<TextView>(R.id.textViewQuestDeclaration).text = questionText
        holder.questionConstraintLayout.findViewById<RadioButton>(R.id.radioButton_1).text =
            questionVariants[0]
        holder.questionConstraintLayout.findViewById<RadioButton>(R.id.radioButton_1).text =
            questionVariants[1]
        holder.questionConstraintLayout.findViewById<RadioButton>(R.id.radioButton_1).text =
            questionVariants[2]
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