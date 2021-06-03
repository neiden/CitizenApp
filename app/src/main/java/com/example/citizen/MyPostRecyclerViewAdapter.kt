package com.example.citizen

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.citizen.models.Post

import com.example.citizen.placeholder.PlaceholderContent.PlaceholderItem


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPostRecyclerViewAdapter(
    private val values: List<Post>,
    val context: Context
) : RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_post, parent, false)
        return ViewHolder(itemView)

    }





    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currItem = values[position]
        var downvoted = false
        var upvoted = false
        holder.upvoteButton.setImageResource(R.drawable.gray_arrow)
        holder.downvoteButton.setImageResource(R.drawable.gray_arrow)
        holder.replyButton.setImageResource(R.drawable.comment)
        holder.opTV.text = currItem.user.username
        holder.contentTV.text = currItem.body
        holder.titleTV.text = currItem.title
        holder.scoreTV.text = (currItem.upvotes - currItem.downvotes).toString()

        holder.replyButton.setOnClickListener{
                Log.d("POST","Clicked on reply")
                var intent = Intent(context, PostActivity::class.java)

                with (intent){
                    putExtra("POST", currItem.id)
                }

                context.startActivity(intent)
        }

        holder.upvoteButton.setOnClickListener{
            if (downvoted) {
                holder.downvoteButton.background = context.resources.getDrawable(R.drawable.gray_arrow)
                downvoted = false
                currItem.upvotes++
            }
            if (!upvoted){
                holder.upvoteButton.background = context.resources.getDrawable(R.drawable.orange_arrow)
                currItem.upvotes++
                upvoted = true
                holder.scoreTV.text = (currItem.upvotes - currItem.downvotes).toString()
            }
        }

        holder.downvoteButton.setOnClickListener{
            if(upvoted){
                holder.upvoteButton.background = context.resources.getDrawable(R.drawable.gray_arrow)
                upvoted = false
                currItem.downvotes++
            }
            if(!downvoted) {
                holder.downvoteButton.background = context.resources.getDrawable(R.drawable.blue_arrow)
                currItem.downvotes++
                downvoted = true
                holder.scoreTV.text = (currItem.upvotes - currItem.downvotes).toString()
            }
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