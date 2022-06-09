package ru.jandroid.roomdatabase_my1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.jandroid.roomdatabase_my1.Person
import ru.jandroid.roomdatabase_my1.R

class PersonAdapter(private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<PersonViewHolder>() {

    private val personsList = mutableListOf<Person>()

    fun addPerson(person: Person) {
        personsList.add(person)
        notifyDataSetChanged()
    }

    fun setPersons(persons: List<Person>){
        personsList.clear()
        personsList.addAll(persons)
        notifyDataSetChanged()
    }

    fun removePerson(id: String) {
        val personToRemove = personsList.find { it.id == id }
        personToRemove?.let {
            personsList.remove(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(personsList[position])
    }

    override fun getItemCount() = personsList.size

    interface OnItemClickListener {
        fun onItemClicked(id: String)
    }

}