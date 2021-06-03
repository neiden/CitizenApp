package com.example.citizen

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.citizen.models.Comment


class MyCommentListRecyclerViewAdapter(
    private val values: List<Comment>
) : RecyclerView.Adapter<MyCommentListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCommentListRecyclerViewAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_post_layout, parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.commentContent.text = item.body
        holder.commentOp.text = item.user?.username
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var commentOp: TextView = itemView.findViewById(R.id.commentOp)
        var commentContent: TextView = itemView.findViewById(R.id.commentContent)

    }

}