package com.example.movielist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddMovieActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editYear: EditText
    private lateinit var editGenre: EditText
    private lateinit var editRating: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        editTitle = findViewById(R.id.editTitle)
        editYear = findViewById(R.id.editYear)
        editGenre = findViewById(R.id.editGenre)
        editRating = findViewById(R.id.editRating)
    }

    fun backToMain(view: View) {
        val title = editTitle.text.toString()
        val year = editYear.text.toString()
        val genre = editGenre.text.toString()
        val rating = editRating.text.toString()

        Log.d("MOVIELIST", "Title: $title, Year: $year, Genre: $genre, Rating: $rating")

        val resultIntent = Intent().apply {
            putExtra("title", title)
            putExtra("year", year)
            putExtra("genre", genre)
            putExtra("rating", rating)
        }

        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
