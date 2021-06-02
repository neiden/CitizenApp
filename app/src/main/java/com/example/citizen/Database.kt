package com.example.citizen


import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import com.example.citizen.models.Post
import com.example.citizen.models.User
import org.json.JSONObject


class Database(val context: Context) {
    companion object {
        var TOKEN: String? = null;
    }
    val endpoint = "http://10.0.2.2:8080"
    val queue = Volley.newRequestQueue(context)

    fun login(user: User, next: (User?) -> Unit) {
        var body = JSONObject("{\"username\":\"${user.username}\",\"password\":\"${user.password}\"}")
        Log.d("DB", "{\"username\":\"${user.username}\",\"password\":\"${user.password}\"}")
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

//    fun getUser(id: Int): User {
//        var ret: Array<Post>? = null;
//        val req = object: JsonArrayRequest(Request.Method.GET, "$endpoint/user/${id}", null,
//            { response ->
//                Log.d("DB", "Returned: ${response.toString()}")
//                for (i in 0 until response.length()) {
//                    val item = response.getJSONObject(i)
//                    Log.d("DB", "${item.getString("title")}")
//                }
//                // TODO: Parse JSON
//            },
//            { error ->
//                Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
//                ret = null
//                // error
//            })
//        {
//            override fun getHeaders(): MutableMap<String, String> {
//                val headers = HashMap<String, String>()
//                headers["Authorization"] = "Bearer ${Database.TOKEN}"
//                return headers
//            }
//        }
//        queue.add(req)
//        return ret
//    }

    fun getPost(): Array<Post>? {
        var ret: Array<Post>? = null;
        val req = object: JsonArrayRequest(Request.Method.GET, "$endpoint/post", null,
        { response ->
            Log.d("DB", "Returned: ${response.toString()}")
            var l = mutableListOf<Post>()
            for (i in 0 until response.length()) {
                val item = response.getJSONObject(i)
                val user = item.getJSONObject("User")
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
                    item.getDouble("long") as Float,
                    item.getDouble("lat") as Float,
                    0,
                    0))
            }
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

    fun getPost(id: Int): Post? {
        var ret: Post? = null;
//        val req = object: JsonArrayRequest(Request.Method.GET, "$endpoint/post/${id}", null,
//            { response ->
//                Log.d("DB", "Returned: ${response.toString()}")
//                for (i in 0 until response.length()) {
//                    val item = response.getJSONObject(i)
//                    Log.d("DB", "${item.getString("title")}")
//                }
//                // TODO: Parse JSON
//            },
//            { error ->
//                Log.d("DB", "Error: ${error.toString()} \n ${error.message}")
//                ret = null
//                // error
//            })
//        {
//            override fun getHeaders(): MutableMap<String, String> {
//                val headers = HashMap<String, String>()
//                headers["Authorization"] = "Bearer ${Database.TOKEN}"
//                return headers
//            }
//        }
//        queue.add(req)
        return ret
    }
}