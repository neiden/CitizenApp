package com.example.citizen


import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.citizen.models.Post
import com.example.citizen.models.User


class Database(val context: Context) {
    companion object {
        var TOKEN: String? = null;
    }
    val endpoint = "http://localhost:8080"
    val queue = Volley.newRequestQueue(context)

    fun login(user: User): User {
        user.token = "test token"
        return user
    }

    fun getUser(id: Int) {

    }

    fun getPost(): Array<Post>? {
        var ret: Array<Post>? = null;
        val req = JsonArrayRequest(Request.Method.GET, endpoint, null,
            { response ->
                Log.d("DB", "Returned: ${response.toString()}")
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    Log.d("DB", "${item.getString("title")}")
                }
                // TODO: Parse JSON
            },
            { error ->
                Log.d("DB", "Error")
                ret = null
                // error
            })

        return ret
    }

    fun getPost(id: Int): Post {
        return Post(id, "This is a post called by id.", "This is the body of a post that is called by its unique database id.", 0, 0F, 0F, 0, 0)
    }
}