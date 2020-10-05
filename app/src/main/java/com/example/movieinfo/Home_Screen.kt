package com.example.movieinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class Home_Screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home__screen)
        var api_key="5093ea845dcefebec67f1dfa0a17e722"
        var top_rated_movies_page=1
        var search_url="https://api.themoviedb.org/3/search/movie?api_key=${api_key}&query="
        var top_rated_movie_url="https://api.themoviedb.org/3/movie/top_rated?api_key=${api_key}&language=en-US&page=${top_rated_movies_page.toString()}"
        val request_top_rated= Request.Builder().url(top_rated_movie_url).build()
        var client=OkHttpClient()
        val rv_upcoming=findViewById<RecyclerView>(R.id.rv_upcoming_movies)
        var llm = LinearLayoutManager(this)
        llm.orientation= LinearLayoutManager.HORIZONTAL
        rv_upcoming?.layoutManager = llm

        client.newCall(request_top_rated).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.d("m",e.message)


            }

            override fun onResponse(call: Call, response: Response) {
                var body=response.body?.string()
                Log.d("m",body)
                //println(body)
                val gson=GsonBuilder().create()
                val top_rated_list=gson.fromJson(body,results::class.java)
                runOnUiThread {
                    val adapter=rv_horizontal_adapter(this@Home_Screen,top_rated_list)
                    rv_upcoming.setAdapter(adapter)
                    adapter.notifyDataSetChanged()
                }

            }

        })

        val rv_search=findViewById<RecyclerView>(R.id.search_rv)
        var llm_v = LinearLayoutManager(this)
        llm_v.orientation= LinearLayoutManager.VERTICAL
        rv_search?.layoutManager = llm_v

        val search_box=findViewById<android.widget.SearchView>(R.id.search)
        search_box.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var s=search_url+newText
                var request_search= Request.Builder().url(s).build()
                client.newCall(request_search).enqueue(object:Callback{
                    override fun onFailure(call: Call, e: IOException) {
                        Log.d("m",e.message)


                    }

                    override fun onResponse(call: Call, response: Response) {
                        var body=response.body?.string()
                        Log.d("m",body)
                        //println(body)
                        var gson=GsonBuilder().create()

                        var top_rated_list=gson.fromJson(body,results::class.java)
                        runOnUiThread {
                            var adapter=rv_search_adapter(this@Home_Screen,top_rated_list)
                            rv_search.setAdapter(adapter)
                            adapter.notifyDataSetChanged()
                        }

                    }

                })

            return false
            }

        })


    }

}

