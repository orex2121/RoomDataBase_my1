package ru.jandroid.roomdatabase_my1.data

import ru.jandroid.roomdatabase_my1.Person


interface PersonRepository {

    suspend fun insert(person: Person)

    suspend fun getAllPersons(): List<Person>

    suspend fun deleteById(id: String)
}