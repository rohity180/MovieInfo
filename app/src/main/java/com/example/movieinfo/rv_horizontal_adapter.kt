package com.example.movieinfo


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.horizontal_movie_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class rv_horizontal_adapter(val ctx:Context, val top_rated_list:results):RecyclerView.Adapter<customViewholder>() {
    var movies= listOf<String>("The Shawshank Redemption (1994)","The Godfather: Part I (1972)","The Godfather: Part II (1974)","The Dark Knight (2008)","12 Angry Men (1957)")
    var mov1="Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
    var mov2="The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."
    var mov3="The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate."
    var mov4="When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice."
    var mov5="A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence."
    var description= listOf<String>(mov1,mov2,mov3,mov4,mov5)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customViewholder {
        val layout_inflator = LayoutInflater.from(ctx)
        val row = layout_inflator.inflate(R.layout.horizontal_movie_list,parent,false)
        return customViewholder(row)
    }

    override fun getItemCount(): Int {

        return top_rated_list.results.count()
        //return 10
    }

    override fun onBindViewHolder(holder: customViewholder, position: Int) {

        val movie = top_rated_list.results[position]
        var d=movie.release_date
        val format = SimpleDateFormat("yyyy-MM-dd")
        var date= format.parse(d)
        var calendar:Calendar = Calendar.getInstance()
        calendar.time=date
        //val cal: Calendar = Calendar.getInstance()
        //val d = Date(page_data.entry_date)
        //var imagev = holder.view.findViewById<ImageView>(R.id.photo)
        holder.view.title.setText(movie.title+" (${calendar.get(Calendar.YEAR)})")
        holder.view.description.setText(movie.overview)

        Glide.with(holder.view.context).load("https://image.tmdb.org/t/p/w500/"+movie.poster_path.substring(1)).into(holder.view.photo)
        println("http://image.tmdb.org/t/p/w300"+movie.poster_path)
        holder.view.photo.setOnClickListener {
            var intent = Intent(ctx, Movie_Details::class.java)
            //intent.putExtra("movie_id", movie.id)
            val mBundle = Bundle()
            mBundle.putInt("movie_id", movie.id)
            intent.putExtras(mBundle)
            ContextCompat.startActivity(ctx, intent, mBundle)
        }





    }


}





class customViewholder(val view: View):RecyclerView.ViewHolder(view) {

}
