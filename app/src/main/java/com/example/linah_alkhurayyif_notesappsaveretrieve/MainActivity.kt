package com.example.linah_alkhurayyif_notesappsaveretrieve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DatabaseHandler(this)
        if(getItemsList().isNotEmpty()){
            RV_update()
        }else{
            cardView.isVisible=false
        }

        note_btn.setOnClickListener { addNote() }
    }
    private fun RV_update(){
        cardView.isVisible=true
        note_recyclerView.adapter = NoteAdapter(this, getItemsList())
        note_recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getItemsList(): ArrayList<Note>{
        return db.viewNotes()
    }
    private fun addNote(){
        if(note_et.text.isEmpty()){
            Toast.makeText(this, "Error note is empty!!", Toast.LENGTH_LONG).show()
        }else{
            db.addNote(Note(0, note_et.text.toString()))
            note_et.text.clear()
            Toast.makeText(this, "Note Added successfully!!", Toast.LENGTH_LONG).show()
           RV_update()
        }

    }
}