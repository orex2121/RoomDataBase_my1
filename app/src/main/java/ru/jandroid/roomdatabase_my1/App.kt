package ru.jandroid.roomdatabase_my1

import android.app.Application
import ru.jandroid.roomdatabase_my1.data.AppDatabase
import ru.jandroid.roomdatabase_my1.data.PersonRepository
import ru.jandroid.roomdatabase_my1.data.PersonRepositoryImpl


class App : Application() {
    private lateinit var database: AppDatabase
    lateinit var repository: PersonRepository

    override fun onCreate() {
        super.onCreate()

        database = AppDatabase.buildDatabase(
                applicationContext,
                DATABASE_NAME
            )

        repository = PersonRepositoryImpl(database.personDao())
    }

    companion object {
        private const val DATABASE_NAME = "my_amazing_app_database.db"
    }
}