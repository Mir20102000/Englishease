package com.example.englishease.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englishease.domain.models.Conclusion
import com.example.englishease.R


class ResultAdapter(val list: List<Conclusion>) : RecyclerView.Adapter<ResultAdapter.MyResult>() {

    class MyResult(item: View) : RecyclerView.ViewHolder(item) {
        val tvUser: TextView = item.findViewById(R.id.user_result_text_view)
        val tvResult: TextView = item.findViewById(R.id.res_results_text_view)
        val tvDate: TextView = item.findViewById(R.id.date_result_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyResult {
        val myItem =
            LayoutInflater.from(parent.context).inflate(R.layout.test_result_item, parent, false)
        return ResultAdapter.MyResult(myItem)
    }

    override fun onBindViewHolder(holder: MyResult, position: Int) {
        holder.tvUser.text = list[position].userName
        holder.tvResult.text = list[position].points.toString()
        holder.tvDate.text = list[position].date
    }

    override fun getItemCount() = list.size
}