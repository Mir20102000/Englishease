package com.example.englishease.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englishease.R
//import com.example.englishease.data.models.Question

import com.example.englishease.domain.models.Question

class QuestionAdapter(val questions: List<Question>)
    : RecyclerView.Adapter<QuestionAdapter.MyQuestion>() {

    class MyQuestion(item: View) : RecyclerView.ViewHolder(item) {
//        val questionLinearLayout: LinearLayout = item.findViewById(R.id.question_item_layout)
        val questionText: TextView = item.findViewById(R.id.textViewQuestDeclaration)
        val answer_one: RadioButton = item.findViewById(R.id.radioButton_1)
        val answer_two: RadioButton = item.findViewById(R.id.radioButton_2)
        val answer_three: RadioButton = item.findViewById(R.id.radioButton_3)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.MyQuestion {
        val myItem = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionAdapter.MyQuestion(myItem)
    }

    override fun onBindViewHolder(holder: QuestionAdapter.MyQuestion, position: Int) {
        val questionText = questions[position].questionText
        val questionVariants = questions[position].variants
        val questionPoints = questions[position].points



//        holder.questionLinearLayout.findViewById<TextView>(R.id.textViewQuestDeclaration).text = questionText
//        holder.questionLinearLayout.findViewById<RadioButton>(R.id.radioButton_1).text =
//            questionVariants[0]
//        holder.questionLinearLayout.findViewById<RadioButton>(R.id.radioButton_1).text =
//            questionVariants[1]
//        holder.questionLinearLayout.findViewById<RadioButton>(R.id.radioButton_1).text =
//            questionVariants[2]
        holder.questionText.text = questionText
        holder.answer_one.text = questionVariants[0]
        holder.answer_two.text = questionVariants[1]
        holder.answer_three.text = questionVariants[2]
//        holder.questionText.text = questionText

    //        holder.itemView.setOnClickListener {
//            listener.onItemClickListener(list[position])
//        }
    }

    override fun getItemCount() = questions.size

    interface OnItemClickListener {
        fun onItemClickListener(lessonName: String)
    }
}