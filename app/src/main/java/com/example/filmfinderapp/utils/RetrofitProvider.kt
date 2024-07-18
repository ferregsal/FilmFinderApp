package com.example.filmfinderapp.utils

import com.example.filmfinderapp.data.FilmFinderApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object{
        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun getFilmFinderApiService():FilmFinderApiService{
            return getRetrofit().create(FilmFinderApiService::class.java)
        }
    }
}