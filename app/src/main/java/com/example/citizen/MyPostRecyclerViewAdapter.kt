package com.example.citizen

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

import com.example.citizen.placeholder.PlaceholderContent.PlaceholderItem
import com.example.citizen.databinding.FragmentPostListBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPostRecyclerViewAdapter(
    private val values: List<Post>
) : RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_post, parent, false)

        return ViewHolder(itemView)

    }





    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currItem = values[position]
        holder.upvoteButton.setImageResource(R.drawable.gray_arrow)
        holder.downvoteButton.setImageResource(R.drawable.gray_arrow)
        holder.replyButton.setImageResource(R.drawable.comment)
        holder.opTV.text = currItem.op
        holder.contentTV.text = currItem.content
        holder.titleTV.text = currItem.title
        holder.scoreTV.text = "no score"

        holder.upvoteButton.setOnClickListener{
            currItem.upvote()
            Log.d("III", "Post upvoted: ${currItem.title}")

        }

        holder.downvoteButton.setOnClickListener{
            currItem.downvote()
            Log.d("III", "Post downvoted: ${currItem.title}")
        }
    }

    override fun getItemCount(): Int = values.size


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var upvoteButton: ImageButton = itemView.findViewById(R.id.upArrow)
        var downvoteButton: ImageButton = itemView.findViewById(R.id.downArrow)
        var replyButton: ImageButton = itemView.findViewById(R.id.reply)
        var opTV: TextView = itemView.findViewById(R.id.op)
        var contentTV: TextView = itemView.findViewById(R.id.content)
        var titleTV: TextView = itemView.findViewById(R.id.title)
        var scoreTV: TextView = itemView.findViewById(R.id.score)

    }

}