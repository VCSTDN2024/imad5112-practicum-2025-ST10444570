package vcmsa.ci.playlistmanagerapp

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

        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnNext: Button = findViewById(R.id.btnNext)
        val btnExit: Button = findViewById(R.id.btnExit)

        btnAdd.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_song, null)
            val dialog = android.app.AlertDialog.Builder(this)
                .setTitle("Add Song to Playlist")
                .setView(dialogView)
                .setPositiveButton("Add") { _, _ ->
                    val title = dialogView.findViewById<EditText>(R.id.etTitle).text.toString()
                    val artist = dialogView.findViewById<EditText>(R.id.etArtist).text.toString()
                    val rating = dialogView.findViewById<EditText>(R.id.etRating).text.toString().toIntOrNull() ?: 0
                    val comment = dialogView.findViewById<EditText>(R.id.etComment).text.toString()

                    if (rating in 1..5 && songList.size < 4) {
                        songList.add(Song(title, artist, rating, comment))
                        Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Invalid input or max songs reached", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancel", null)
                .create()
            dialog.show()
        }

        btnNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}

data class Song(val title: String, val artist: String, val rating: Int, val comment: String)