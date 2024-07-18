package com.example.filmfinderapp.activities

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.filmfinderapp.data.Film
import com.example.filmfinderapp.databinding.ActivityDetailBinding
import com.example.filmfinderapp.utils.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var film: Film

    companion object {
        const val EXTRA_FILM_ID = "FILM_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filmId = intent.getStringExtra(EXTRA_FILM_ID)
        if (filmId != null) {
            getFilmById(filmId)
        } else {
            // Manejar el error si el ID no es válido
        }
    }

    private fun getFilmById(filmId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val filmFinderApiService = RetrofitProvider.getFilmFinderApiService()
                val result = filmFinderApiService.getFilmById(filmId)
                film = result
                runOnUiThread {
                    Log.d("DetailActivity", "UI thread")

                    binding.titleTextView.text = result.title
                    binding.directorTextView.text = Html.fromHtml("<b>Director:</b> \t ${result.director}", 0)

                    binding.plotTextView.text = result.plot
                    binding.durationTextView.text =  Html.fromHtml("<b>Runtime:</b> \t ${result.runtime}",0)
                    binding.genreTextView.text =  Html.fromHtml("<b>Genre:</b> \t ${result.genre}",0)
                    binding.yearTextView.text =  Html.fromHtml("<b>Year:</b> \t ${result.year}",0)
                    binding.countryTextView.text =  Html.fromHtml("<b>Country:</b> \t ${result.country}",0)
                    when (result.director) {
                        "Quentin Tarantino" -> binding.titleTextView.setTextColor(Color.RED)
                    }
                    Picasso.get().load(result.poster).into(binding.posterImageView)
                    }




            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("DetailActivity", "Error fetching film data", e)
            }
        }
    }
    }

