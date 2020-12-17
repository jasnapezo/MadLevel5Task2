package com.example.madlevel5task2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val repository = GameRepository(application.applicationContext)

    val games: LiveData<List<Game>> = repository.getAllGames()

    fun addGame(title: String, platform: String, day: String, month: String, year: String) {
        val dateFormat =  SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        val date = dateFormat.parse("$day-$month-$year")

        // change to background thread to insert game
        mainScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertGame(Game(title, platform, date))
            }
        }
    }

    fun deleteGame(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteGame(game)
            }
        }
    }

    fun deleteAllGames() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteAllGames()
            }
        }
    }

}