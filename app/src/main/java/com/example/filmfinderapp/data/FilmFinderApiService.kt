package com.example.filmfinderapp.data


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmFinderApiService {
    @GET("/?apikey=a9f9ac01")
        suspend fun findFilmByTitle(@Query("s") title: String) : FilmFinderResponse
    @GET("/?apikey=a9f9ac01")
    suspend fun getFilmById(@Query("i")id:String) : Film
}
