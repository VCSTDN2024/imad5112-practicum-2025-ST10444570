package vcmsa.ci.playlistmanagerapp

//Tisetso Lephoto || ST10444570

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        val songList = mutableListOf<Song>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialise views
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnNext: Button = findViewById(R.id.btnNext)
        val btnExit: Button = findViewById(R.id.btnExit)

        // Set click listeners for buttons
        btnAdd.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_song, null)
            val dialog = android.app.AlertDialog.Builder(this).create()
            dialog.setView(dialogView)
            dialog.show()

            // Handle save button click
            val btnSave = dialogView.findViewById<Button>(R.id.btnSave)
            btnSave.setOnClickListener {
                val title = dialogView.findViewById<EditText>(R.id.etTitle).text.toString()
                val artist = dialogView.findViewById<EditText>(R.id.etArtist).text.toString()
                val ratingText = dialogView.findViewById<EditText>(R.id.etRating).text.toString()
                val comment = dialogView.findViewById<EditText>(R.id.etComment).text.toString()

                // Validate input and add song to list
                val rating = ratingText.toIntOrNull()
                if (title.isNotBlank() && artist.isNotBlank() && rating in 1..5 && MainActivity.songList.size < 4) {
                    MainActivity.songList.add(Song(title, artist, rating!!, comment))
                    Toast.makeText(this, "Song saved!", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()  // closes the dialog and returns to main screen
                } else {
                    Toast.makeText(this, "Please enter valid details.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Handle next button click
        btnNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // Handle exit button click
        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}

// Song data class
data class Song(val title: String, val artist: String, val rating: Int, val comment: String)