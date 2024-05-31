package com.example.englishease.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.englishease.R
import com.example.englishease.domain.models.Question


class QuestionAdapter(val questions: List<Question>)
    : RecyclerView.Adapter<QuestionAdapter.MyQuestion>() {

    var state = IntArray(questions.size) { _ -> 0}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.MyQuestion {
        val myItem = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionAdapter.MyQuestion(myItem)
    }

    override fun onBindViewHolder(holder: QuestionAdapter.MyQuestion, position: Int) {
        val questionNumber = questions[position].questionNumber
        val questionText = questions[position].questionText
        val questionVariants = questions[position].variants
        val questionPoints = questions[position].points



        holder.questionText.text = "${questionNumber}. $questionText"
        holder.answer_one.text = questionVariants[0]
        holder.answer_two.text = questionVariants[1]
        holder.answer_three.text = questionVariants[2]

//        holder.radio_group.addView(holder.answer_one)
//        holder.radio_group.addView(holder.answer_two)
//        holder.radio_group.addView(holder.answer_three)


//        setRadio(holder, questions[position].variants[])
//        setRadio(holder, holder.radio_group.checkedRadioButtonId)


        holder.answer_one.setOnClickListener {
            state[position] = questionPoints[0]
//            setRadio(holder, holder.radio_group.checkedRadioButtonId)
        }

        holder.answer_two.setOnClickListener {
            state[position] = questionPoints[1]
//            setRadio(holder, holder.radio_group.checkedRadioButtonId)
        }

        holder.answer_three.setOnClickListener {
            state[position] = questionPoints[2]
//            setRadio(holder, holder.radio_group.checkedRadioButtonId)
        }

//        holder.radio_group.checkedRadioButtonId
//        holder.answer_one.isChecked

    }

//    private fun setRadio(holder: MyQuestion, selection: Int) {
////        println("SELECT:$selection")
//        val b1: RadioButton = holder.answer_one
//        val b2: RadioButton = holder.answer_two
//        val b3: RadioButton = holder.answer_three
//
//        b1.isChecked = false
//        b2.isChecked = false
//        b3.isChecked = false
//
//        if (selection == 0) b1.isChecked = true
//        if (selection == 1) b2.isChecked = true
//        if (selection == 2) b3.isChecked = true
//    }


    override fun getItemCount() = questions.size


    class MyQuestion(item: View) : RecyclerView.ViewHolder(item) {
        val questionText: TextView = item.findViewById(R.id.textViewQuestDeclaration)
        val answer_one: RadioButton = item.findViewById(R.id.radioButton_1)
        val answer_two: RadioButton = item.findViewById(R.id.radioButton_2)
        val answer_three: RadioButton = item.findViewById(R.id.radioButton_3)

//        val textViewPoints: TextView = item.findViewById(R.id.points)

//        val radio_group: RadioGroup = item.findViewById(R.id.radioGroup)

    }
}