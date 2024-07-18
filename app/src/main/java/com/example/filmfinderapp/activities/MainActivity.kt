package com.example.filmfinderapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmfinderapp.R
import com.example.filmfinderapp.adapters.FilmAdapter
import com.example.filmfinderapp.data.Film
import com.example.filmfinderapp.databinding.ActivityMainBinding
import com.example.filmfinderapp.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FilmAdapter
    private lateinit var filmList:List<Film>
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)

        val searchViewItem = menu.findItem(R.id.filmSearchView)
        val searchView = searchViewItem.actionView as SearchView


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {


            override fun onQueryTextSubmit(query: String): Boolean {
                searchByTitle(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })

        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmList = emptyList()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter= FilmAdapter(filmList){ position ->
            navigateToDetail(filmList[position])
        }

        binding.filmRecyclerView.adapter = adapter
        binding.filmRecyclerView.layoutManager= GridLayoutManager(this, 2)
        searchByTitle("mat")



    }

    private fun navigateToDetail(film: Film) {
        Log.d("MainActivity", "Navigating to detail for Film: ${film.title}, ID: ${film.id}")

        Toast.makeText(this, film.title, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FILM_ID, film.id)
        startActivity(intent)
    }
    private fun searchByTitle (query: String){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val filmFinderApiService = RetrofitProvider.getFilmFinderApiService()
                val result = filmFinderApiService.findFilmByTitle(query)
                runOnUiThread {
                    if (result.response == "True") {
                        filmList = result.results
                        adapter.updateData(filmList)
                            Log.d("MainActivity", "Films found: ${filmList.size}")
                    } else {
                        adapter.updateData(emptyList())
                        Log.d("MainActivity", "No films found")
                    }
                }
            }
            catch (e:Exception){

                e.printStackTrace()
                Log.e("MainActivity", "Error fetching films", e)
            }
        }
    }

}