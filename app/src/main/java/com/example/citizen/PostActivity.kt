package com.example.citizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.res.Configuration
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citizen.models.Comment
import com.example.citizen.models.Post
import com.example.citizen.models.User

class PostActivity : AppCompatActivity(), CommentDialogListener {

    lateinit var post: PostFragment
    lateinit var comments: CommentListFragment
    lateinit var title: TextView
    lateinit var content: TextView
    lateinit var postOp: TextView
    lateinit var commentButton: Button

    var db: Database = Database.getInstance(this)

    companion object {
        var COMMENTLIST: MutableList<Comment> = mutableListOf()
    }

    override fun onPassData(data: String) {
        var commentList: RecyclerView = findViewById(R.id.commentList)
        var dataArray = data.split(",").toTypedArray()
        var op = dataArray[0]
        var content = dataArray[1]
        db.postComment(Comment(0, 0, null, 0, "$content", 0, 0), 0) {
            if(it != null) {
                Log.d("GGG", "${it.body}")
                COMMENTLIST.add(it)
                commentList.adapter?.notifyDataSetChanged()
            }
        }
        //COMMENTLIST.add(Comment(0, 0, User(0, "harry", null, "role", null), 0, "$content", 0, 0))
        commentList.adapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        var commentList: RecyclerView = findViewById(R.id.commentList)
        var postId: Int = intent.getIntExtra("POST", 0)
        COMMENTLIST = mutableListOf()
        Log.d("PostID", "$postId")


        commentList.adapter = MyCommentListRecyclerViewAdapter(COMMENTLIST)
        commentList.layoutManager = LinearLayoutManager(this)

        db.getPost(postId){
            if(it != null){
                Log.d("getPOST", "${it.title}")
                if(it.comments != null){
                    for (i in 0 until it?.comments!!.size){
                        COMMENTLIST.add(it.comments!![i])
                    }
                    }
            }
        }

        postOp = findViewById(R.id.postOp)
        title = findViewById(R.id.postTitle)
        content = findViewById(R.id.postContent)
        commentButton = findViewById(R.id.commentButton)

        comments = CommentListFragment.newInstance()



        Log.d("EEE", "${COMMENTLIST.toString()}")
        commentList.adapter?.notifyDataSetChanged()


        commentButton.setOnClickListener{
            var commentDialogFrag = CreateCommentDialogFragment()
            commentDialogFrag.show(supportFragmentManager, "fragment_create_comment_dialog")
        }

//        //var fragment = supportFragmentManager.findFragmentById(R.id.post) as PostFragment
//        val fragmentManager = supportFragmentManager
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.post,post)
//        //transaction.addToBackStack(null)
//        transaction.replace(R.id.fragmentContainerView,CommentListFragment.newInstance())
//        transaction.commit()


    }

    fun addComment(comment: Comment){
        COMMENTLIST.add(comment)
        comments.addComment(comment)
    }

}