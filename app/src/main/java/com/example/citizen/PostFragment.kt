package com.example.citizen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.content.Intent
import android.app.AlertDialog

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_POST = "post"

/**
 * A simple [Fragment] subclass.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostFragment : Fragment() {

    private var post: Post? = null

    private var upvoted: Boolean = false
    private var downvoted: Boolean = false

    lateinit var upvoteButton: ImageButton
    lateinit var downvoteButton: ImageButton
    lateinit var replyButton: ImageButton
    lateinit var opTV: TextView
    lateinit var contentTV: TextView
    lateinit var titleTV: TextView
    lateinit var scoreTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            post = it.get(ARG_POST) as Post
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_post, container, false)

        upvoteButton = view.findViewById<ImageButton>(R.id.upArrow)
        downvoteButton = view.findViewById<ImageButton>(R.id.downArrow)
        replyButton = view.findViewById<ImageButton>(R.id.reply)
        opTV = view.findViewById(R.id.op)
        contentTV = view.findViewById(R.id.content)
        titleTV = view.findViewById(R.id.title)
        scoreTV = view.findViewById(R.id.score)

        opTV.text = post?.op
        contentTV.text = post?.content
        titleTV.text = post?.title
        scoreTV.text = post?.score.toString()

        replyButton.setOnClickListener {
            //Log.d("ACTIVITY","$activity")
            if (activity !is PostActivity) {
                var intent = Intent(activity, PostActivity::class.java)
                startActivity(intent)
                //TODO figure out dialog popup stuff
            }
        }

        // Upvote button code
        upvoteButton.setOnClickListener {
            if (downvoted) {
                downvoteButton.background = resources.getDrawable(R.drawable.gray_arrow)
                downvoted = false
                post?.upvote()
            }
            if (!upvoted){
                upvoteButton.background = resources.getDrawable(R.drawable.orange_arrow)
                post?.upvote()
                upvoted = true
                scoreTV.text = post?.score?.toString()
            }
        }

        // Downvote button code
        downvoteButton.setOnClickListener {
            if(upvoted){
                upvoteButton.background = resources.getDrawable(R.drawable.gray_arrow)
                upvoted = false
                post?.downvote()
            }
            if(!downvoted) {
                downvoteButton.background = resources.getDrawable(R.drawable.blue_arrow)
                post?.downvote()
                downvoted = true
                scoreTV.text = post?.score?.toString()
            }
        }


        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(post: Post) =
            PostFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_POST,post)
                }
            }
    }
}