package ru.jandroid.roomdatabase_my1

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.jandroid.roomdatabase_my1.adapter.PersonAdapter
import java.util.*


class MainActivity : AppCompatActivity(), PersonAdapter.OnItemClickListener {

    private val random: Int
        get() = Random().nextInt(9)

    private val bigRandom: Int
        get() = Random().nextInt(10000)

    private lateinit var personAdapter: PersonAdapter

    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        addPersonButton.setOnClickListener {
            val person = Person(
                id = bigRandom.toString(),
                name = getRandomName(),
                description = getRandomDescription(),
                avatarUrl = getRandomAvatarUrl()
            )
            personAdapter.addPerson(person)

            insertPerson(person)

        }

        retrievePersons()
    }

    override fun onDestroy() {
        super.onDestroy()
        alertDialog?.dismiss()
    }

    override fun onItemClicked(id: String) {
        alertDialog = AlertDialog.Builder(this)
            .setTitle("Delete Person")
            .setMessage("Do you want to delete the person from the list")
            .setPositiveButton("Yes") { dialog, _ ->
                deletePersonById(id)
                dialog.dismiss()
            }.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun initRecyclerView() {
        personAdapter = PersonAdapter(this)

        with(personList) {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = personAdapter
            this.setHasFixedSize(true)
        }
    }

    private fun getRandomName() = resources.getStringArray(R.array.names)[random]

    private fun getRandomDescription() = resources.getStringArray(R.array.sentences)[random]

    private fun getRandomAvatarUrl() = "https://i.pravatar.cc/150?img=$random"


    private fun insertPerson(person: Person) {
        // Work on background thread
        lifecycleScope.launch(Dispatchers.IO) {
            (applicationContext as App).repository.insert(person = person)
        }
    }

    private fun retrievePersons() {
        // Work on background thread
        lifecycleScope.launch(Dispatchers.IO) {
            val persons = (applicationContext as App).repository.getAllPersons()
            // Work on main thread

            withContext(Dispatchers.Main) {
                personAdapter.setPersons(persons)
            }
        }
    }

    private fun deletePersonById(id: String) {
        // Work on background thread
        lifecycleScope.launch(Dispatchers.IO) {
            (applicationContext as App).repository.deleteById(id)
            withContext(Dispatchers.Main) {
                personAdapter.removePerson(id)
            }
        }
    }
}