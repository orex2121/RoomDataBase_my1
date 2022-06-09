package ru.jandroid.roomdatabase_my1.data

import ru.jandroid.roomdatabase_my1.Person


class PersonRepositoryImpl(private val personDao: PersonDao) : PersonRepository {
    override suspend fun insert(person: Person) {
        personDao.insert(person)
    }

    override suspend fun getAllPersons() = personDao.getAllPersons()

    override suspend fun deleteById(id: String) {
        personDao.deleteById(id)
    }

}