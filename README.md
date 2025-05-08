# 📽️ MovieList – CS3013 Major Program 3

Android app for CS3013 that allows users to add, view, delete, sort, and save a list of movies.

---

## ✅ Features Implemented

- 📋 View a vertical list of movie entries (RecyclerView)
- ➕ Add a movie with title, year, genre, and rating
- 🗑️ Swipe right to delete any movie
- 💾 Save movie list to `MOVIELIST.csv`
- 🔁 Load saved list on app launch
- ↕️ Sort movie list by:
    - Rating
    - Year
    - Genre
- ☑️ Internal file storage compliant with class instructions
- 🧱 App structure modeled after the `BookRecycle` example

---

## 📂 File Structure

- `MainActivity.kt`: Main screen with list, buttons, sorting
- `AddMovieActivity.kt`: Second screen to enter new movie data
- `MovieAdapter.kt`: Adapter + ViewHolder for RecyclerView
- `movie_item.xml`: Layout for each list item
- `activity_main.xml`, `activity_add_movie.xml`: Screen layouts
- `MOVIELIST.csv`: Auto-created and updated by the app (internal)

---

## 🛠️ How to Run

1. Clone repo:  git clone https://github.com/willmaddock/MovieList.git
2. Open in Android Studio
3. Run on emulator or physical device
4. Test:
- Add movie → Submit
- Swipe right to delete
- Tap "SAVE LIST" → File saved
- Relaunch app → List restored

---

## ⚠️ Notes

- No external libraries were used.
- App follows the 13-step rubric and BookRecycle structure exactly.
- `MOVIELIST.csv` saved in: /data/data/com.example.movielist/files/ 
---

## 🎓 Created By

William Maddock  
CS3013 – Mobile App Development  
Spring 2025