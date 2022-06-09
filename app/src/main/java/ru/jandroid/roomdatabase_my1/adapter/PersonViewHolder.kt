package ru.jandroid.roomdatabase_my1.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_person.view.*
import ru.jandroid.roomdatabase_my1.Person

class PersonViewHolder(
    itemView: View,
    private val itemClickListener: PersonAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(itemView) {

    fun bind(person: Person) {
        with(itemView) {
            person.run {
                nameTextView.text = name
                descriptionTextView.text = description

                Glide.with(context).load(avatarUrl).into(avatarImageView)


                deleteItemImageView.setOnClickListener {
                    itemClickListener.onItemClicked(id = id)
                }
            }
        }
    }

}