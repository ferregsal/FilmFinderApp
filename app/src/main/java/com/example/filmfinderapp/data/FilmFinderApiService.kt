package com.example.filmfinderapp.data

import com.example.superheroesapp.data.Superhero
import com.example.superheroesapp.data.SuperheroResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmFinderApiService {
    @GET("search/{name}")
    suspend fun findSuperheroesByName(@Path("name") query: String) : SuperheroResponse
    @GET("{id}")
    suspend fun getSuperheroById(@Path("id")id:Int) : Superhero
}
