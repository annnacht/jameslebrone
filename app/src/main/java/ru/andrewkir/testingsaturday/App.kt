package ru.andrewkir.testingsaturday

import android.app.Application
import androidx.room.Room
import ru.andrewkir.testingsaturday.data.dao.UsersDatabase

class App : Application() {
  override fun onCreate() {
    super.onCreate()
    db = Room.databaseBuilder(
      applicationContext,
      UsersDatabase::class.java,
      "user-db"
    )
      .allowMainThreadQueries()
      .build()
  }

  companion object {
    var db: UsersDatabase? = null
    fun getDatabase(): UsersDatabase? {
      return db
    }
  }
}