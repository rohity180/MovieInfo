package com.example.movieinfo

data class results(val results:List<result>){}
data class result(val id:Int , val adult:Boolean , val original_language:String , val original_title:String, val title:String, val vote_average:Float, val overview:String,val release_date:String, val poster_path:String){

}
data class movies(val movies:List<movie>)
data class movie(var adult:Boolean=false,var backdrop_path:String="empty",var budget:Long=100,var homepage:String="Empty", var id:Int=0,var imdb_id:String, var original_language:String , var original_title:String,var overview:String="empty",var poster_path:String="empty",var status:String="empty",var tagline:String="empty",var title:String="empty",var vote_average:Float=10.00f, var release_date:String,var runtime:Int){

}

data class belongs_to_collection(var id:Int=0,var name:String="empty",var poster_path:String="empty",var backdrop_path:String="Empty"){

}

//data class movie(var adult:Boolean=false,var backdrop_path:String="empty",var collection:belongs_to_collection,var budget:Long=100,var generes:ArrayList<Pair<Int,String>>,var homepage:String="Empty", var id:Int=0,var imdb_id:Int, var original_language:String , var original_title:String,var overview:String="empty",var poster_path:String="empty",var status:String="empty",var tagline:String="empty",var title:String="empty",var vote_average:Float=10.00f){

//}

