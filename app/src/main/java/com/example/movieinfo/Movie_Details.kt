package com.example.movieinfo

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class Movie_Details : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        var get_intent= intent
        var bundle=get_intent.extras
        //var movie_id=75780
        if (bundle!=null) {
            var movie_id =bundle.getInt("movie_id")
            var api_key="5093ea845dcefebec67f1dfa0a17e722"
            var get_movie="https://api.themoviedb.org/3/movie/${movie_id}?api_key=${api_key}"
            var client= OkHttpClient()
            val movie_request= Request.Builder().url(get_movie).build()
            client.newCall(movie_request).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("m",e.message)


                }

                override fun onResponse(call: Call, response: Response) {
                    var body=response.body?.string()
                    Log.d("m",body)
                    //println(body)
                    val gson= GsonBuilder().create()
                    val movie=gson.fromJson(body,movie::class.java)
                    runOnUiThread {
                        if(movie!=null)
                        {
                            var poster = findViewById<ImageView>(R.id.movie_poster)
                            var title= findViewById<TextView>(R.id.movie_title)
                            var overview=findViewById<TextView>(R.id.overview)
                            var calendar: Calendar = Calendar.getInstance()
                            val format = SimpleDateFormat("yyyy-MM-dd")
                            var d=movie.release_date
                            if(!d.isNullOrEmpty()){
                                var date= format.parse(d)
                                calendar.time=date
                            }
                            else{
                                var date = format.parse("1800-01-01")
                                calendar.time=date
                            }


                            if (!movie.poster_path.isNullOrEmpty())
                            {
                                Glide.with(this@Movie_Details).load("https://image.tmdb.org/t/p/w500/"+movie.poster_path.substring(1)).into(poster)

                            }
                            title.text=movie.title+" (${calendar.get(Calendar.YEAR)})"
                            overview.text=movie.overview
                        }

                    }

                }

            })
        }


    }
}
