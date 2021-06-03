package com.example.citizen


import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import com.example.citizen.models.Comment
import com.example.citizen.models.Post
import com.example.citizen.models.User
import org.json.JSONObject


class Database(val context: Context) {
    companion object {
        var TOKEN: String? = null
        private var INSTANCE: Database? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Database(context).also {
                    INSTANCE = it
                }
            }
    }
    val endpoint = "http://10.0.2.2:8080"
    val queue = Volley.newRequestQueue(context)

    fun login(user: User, next: (User?) -> Unit) {
        var body = JSONObject("{\"username\":\"${user.username}\",\"password\":\"${user.password}\"}")
        val req = JsonObjectRequest(Request.Method.POST, "$endpoint/user/login", body,
            { response ->
                Log.d("DB", "Returned: ${response.toString()}")
                Database.TOKEN = response.getString("token")
                next(User(
                    response.getInt("id"),
                    response.getString("username"),
                    null,
                    response.getString("role"),
                    response.getString("token")
                ))
            },
            { error ->
                Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
                next(null)
                // error
            })
        queue.add(req)
    }

    fun register(user: User, next: (User?) -> Unit) {
        var body = JSONObject("{\"username\":\"${user.username}\",\"password\":\"${user.password}\"}")
        val req = JsonObjectRequest(Request.Method.POST, "$endpoint/user", body,
            { response ->
                Log.d("DB", "Returned: ${response.toString()}")
                Database.TOKEN = response.getString("token")
                next(User(
                    response.getInt("id"),
                    response.getString("username"),
                    null,
                    response.getString("role"),
                    response.getString("token")
                ))
            },
            { error ->
                Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
                next(null)
                // error
            })
        queue.add(req)
    }

    fun getPost(next: (MutableList<Post>?) -> Unit) {
        val req = object: JsonArrayRequest(Request.Method.GET, "$endpoint/post", null,
        { response ->
            Log.d("DB", "Returned: ${response.toString()}")
            var l = mutableListOf<Post>()
            for (i in 0 until response.length()) {
                val item = response.getJSONObject(i)
                val user = item.getJSONObject("User")
                var longitude = 0.0
                var latitude = 0.0
                if (!item.isNull("long")) {
                    longitude = item.getDouble("long")
                }
                if (!item.isNull("lat")) {
                    latitude = item.getDouble("lat")
                }
                l.add(Post(
                    item.getInt("id"),
                    item.getString("title"),
                    item.getString("body"),
                    User(
                        user.getInt("id"),
                        user.getString("username"),
                        null,
                        user.getString("role"),
                        null),
                    null,
                    longitude,
                    latitude,
                    0,
                    0))
            }
            next(l)
        },
        { error ->
            Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
            next(null)
            // error
        })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer ${Database.TOKEN}"
                return headers
            }
        }
        queue.add(req)
    }

    fun getPost(id: Int, next: (Post?) -> Unit) {
        val req = object: JsonObjectRequest(Request.Method.GET, "$endpoint/post/${id}", null,
            { response ->
                Log.d("DB", "Returned: ${response.toString()}")
                val user = response.getJSONObject("User")
                var longitude = 0.0
                var latitude = 0.0
                if (!response.isNull("long")) {
                    longitude = response.getDouble("long")
                }
                if (!response.isNull("lat")) {
                    latitude = response.getDouble("lat")
                }
                var l = mutableListOf<Comment>()
                var comments = response.getJSONArray("Comments")
                for (i in 0 until comments.length()) {
                    var item = comments.getJSONObject(i)
                    var itemUser = item.getJSONObject("User")
                    l.add(Comment(
                        item.getInt("id"),
                        item.getInt("PostId"),
                        User(
                            itemUser.getInt("id"),
                            itemUser.getString("username"),
                            null,
                            itemUser.getString("role"),
                            null
                        ),
                        0,
                        item.getString("body"),
                        0,
                        0
                    ))
                }
                var post = Post(
                    response.getInt("id"),
                    response.getString("title"),
                    response.getString("body"),
                    User(
                        user.getInt("id"),
                        user.getString("username"),
                        null,
                        user.getString("role"),
                        null),
                    null,
                    longitude,
                    latitude,
                    0,
                    0)
                next(post)
            },
            { error ->
                Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
                next(null)
                // error
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer ${Database.TOKEN}"
                return headers
            }
        }
        queue.add(req)
    }

    fun postPost(post: Post, next: (Post?) -> Unit) {
        Log.d("DB", "TEST: {\"title\":\"${post.title}\",\"body\":\"${post.body}\"}")
        var body = JSONObject("{\"title\":\"${post.title}\",\"body\":\"${post.body}\"}")
        val req = object: JsonObjectRequest(Request.Method.POST, "$endpoint/post", body,
            { response ->
                Log.d("DB", "Returned: ${response.toString()}")
                var longitude = 0.0
                var latitude = 0.0
                if (!response.isNull("long")) {
                    longitude = response.getDouble("long")
                }
                if (!response.isNull("lat")) {
                    latitude = response.getDouble("lat")
                }
                var l = mutableListOf<Comment>()
                var post = Post(
                    response.getInt("id"),
                    response.getString("title"),
                    response.getString("body"),
                    null,
                    null,
                    longitude,
                    latitude,
                    0,
                    0)
                next(post)
            },
            { error ->
                Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
                next(null)
                // error
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer ${Database.TOKEN}"
                return headers
            }
        }
        queue.add(req)
    }

    fun postComment(comment: Comment, postId: Int, next: (Comment?) -> Unit) {
        var body = JSONObject("{\"body\":\"${comment.body}\"}")
        val req = object: JsonObjectRequest(Request.Method.POST, "$endpoint/comment/${postId}", body,
            { response ->
                Log.d("DB", "Returned: ${response.toString()}")
                var itemUser = response.getJSONObject("User")
                next(Comment(
                    response.getInt("id"),
                    response.getInt("PostId"),
                    User(
                        itemUser.getInt("id"),
                        itemUser.getString("username"),
                        null,
                        itemUser.getString("role"),
                        null
                    ),
                    0,
                    response.getString("body"),
                    0,
                    0
                ))
            },
            { error ->
                Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
                next(null)
                // error
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer ${Database.TOKEN}"
                return headers
            }
        }
        queue.add(req)
    }
}