package com.example.movielist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieList: ArrayList<Movie>
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView

    // Sorting toggle flags
    private var isRatingAsc = true
    private var isYearAsc = true
    private var isGenreAsc = true

    // Launcher for AddMovieActivity
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val title = data.getStringExtra("title") ?: ""
                    val year = data.getStringExtra("year") ?: ""
                    val genre = data.getStringExtra("genre") ?: ""
                    val rating = data.getStringExtra("rating") ?: ""

                    val newMovie = Movie(title, year, genre, rating)
                    movieList.add(newMovie)
                    movieAdapter.notifyDataSetChanged()
                    Log.d("MOVIELIST", "Added movie: $title ($year), $genre, rating: $rating")
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize and load from file
        movieList = ArrayList()
        readFile()

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(movieList)
        recyclerView.adapter = movieAdapter

        // Enable swipe-to-delete
        val itemTouchHelper = ItemTouchHelper(movieAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Add movie button
        val addMovieButton = findViewById<Button>(R.id.addMovieButton)
        addMovieButton.setOnClickListener {
            val intent = Intent(this, AddMovieActivity::class.java)
            startForResult.launch(intent)
        }

        // Save list button
        val saveListButton = findViewById<Button>(R.id.saveListButton)
        saveListButton.setOnClickListener {
            saveList()
        }
    }

    private fun readFile() {
        val file = File(filesDir, "MOVIELIST.csv")
        if (!file.exists()) return

        movieList.clear()

        try {
            file.forEachLine { line ->
                val tokens = line.split(",")
                if (tokens.size == 4) {
                    val (title, year, genre, rating) = tokens
                    movieList.add(Movie(title, year, genre, rating))
                }
            }
            Log.d("MOVIELIST", "Loaded ${movieList.size} movies from file")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun writeFile() {
        val file = File(filesDir, "MOVIELIST.csv")

        try {
            val writer = BufferedWriter(FileWriter(file))
            for (movie in movieList) {
                writer.write("${movie.title},${movie.year},${movie.genre},${movie.rating}")
                writer.newLine()
            }
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun saveList() {
        writeFile()
        Log.d("MOVIELIST", "Movie list saved to MOVIELIST.csv")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ratingSort -> {
                if (isRatingAsc) {
                    movieList.sortBy { it.rating }
                } else {
                    movieList.sortByDescending { it.rating }
                }
                isRatingAsc = !isRatingAsc
                Log.d("MOVIELIST", "Sorted by rating (${if (isRatingAsc) "ASC" else "DESC"})")
            }
            R.id.yearSort -> {
                if (isYearAsc) {
                    movieList.sortBy { it.year }
                } else {
                    movieList.sortByDescending { it.year }
                }
                isYearAsc = !isYearAsc
                Log.d("MOVIELIST", "Sorted by year (${if (isYearAsc) "ASC" else "DESC"})")
            }
            R.id.genreSort -> {
                if (isGenreAsc) {
                    movieList.sortBy { it.genre }
                } else {
                    movieList.sortByDescending { it.genre }
                }
                isGenreAsc = !isGenreAsc
                Log.d("MOVIELIST", "Sorted by genre (${if (isGenreAsc) "ASC" else "DESC"})")
            }
        }
        movieAdapter.notifyDataSetChanged()
        return super.onOptionsItemSelected(item)
    }
}
