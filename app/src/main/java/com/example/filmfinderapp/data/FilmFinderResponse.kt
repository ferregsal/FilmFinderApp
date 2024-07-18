package com.example.filmfinderapp.data


import com.google.gson.annotations.SerializedName

data class FilmFinderResponse (
    @SerializedName("Response") val response: String,
    @SerializedName("Search") val results: List<Film>){


}
data class Film(
    @SerializedName("Title") val title: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("imdbID") val id : String,
    @SerializedName("Year") val year: String,
    @SerializedName("Plot") val plot : String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Country") val country : String,
    @SerializedName("Director") val director: String,
    @SerializedName("Runtime") val runtime: String
)
data class Image(
    @SerializedName("url") val url: String){

}
/*class IntegerAdapter : TypeAdapter<Int>() {
    override fun write(out: JsonWriter?, value: Int) {
        out?.value(value)
    }

    override fun read(`in`: JsonReader?): Int {
        if (`in` != null) {
            val value: String = `in`.nextString()
            if (value != "null") {
                return value.toInt()
            }
        }
        return 0
    }

}
*/