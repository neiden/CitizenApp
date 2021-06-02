package com.example.citizen


import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import com.example.citizen.models.Post
import com.example.citizen.models.User


class Database(val context: Context) {
    companion object {
        var TOKEN: String? = null;
    }
    val endpoint = "http://10.0.2.2:8080"
    val queue = Volley.newRequestQueue(context)

    fun login(user: User): User {
        user.token = "test token"
        return user
    }

    fun getUser(id: Int) {
        var ret: Array<Post>? = null;
        val req = object: JsonArrayRequest(Request.Method.GET, "$endpoint/user/${id}", null,
            { response ->
                Log.d("DB", "Returned: ${response.toString()}")
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    Log.d("DB", "${item.getString("title")}")
                }
                // TODO: Parse JSON
            },
            { error ->
                Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
                ret = null
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
        return ret
    }

    fun getPost(): Array<Post>? {
        var ret: Array<Post>? = null;
        val req = object: JsonArrayRequest(Request.Method.GET, "$endpoint/post", null,
        { response ->
            Log.d("DB", "Returned: ${response.toString()}")
            for (i in 0 until response.length()) {
                val item = response.getJSONObject(i)
                Log.d("DB", "${item.getString("title")}")
            }
            // TODO: Parse JSON
        },
        { error ->
            Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
            ret = null
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
        return ret
    }

    fun getPost(id: Int): Post {
        var ret: Array<Post>? = null;
        val req = object: JsonArrayRequest(Request.Method.GET, "$endpoint/post/${id}", null,
            { response ->
                Log.d("DB", "Returned: ${response.toString()}")
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    Log.d("DB", "${item.getString("title")}")
                }
                // TODO: Parse JSON
            },
            { error ->
                Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
                ret = null
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
        return ret
    }
}