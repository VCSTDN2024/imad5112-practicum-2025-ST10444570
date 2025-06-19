package vcmsa.ci.playlistmanagerapp

//Tisetso Lephoto || ST10444570

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    // Declare views
    private lateinit var tvDisplay: TextView
    private lateinit var btnShowSongs: Button
    private lateinit var btnAvgRating: Button
    private lateinit var btnBack: Button

    // Override onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Initialise views
        tvDisplay = findViewById(R.id.tvDisplay)
        btnShowSongs = findViewById(R.id.btnShowSongs)
        btnAvgRating = findViewById(R.id.btnAvgRating)
        btnBack = findViewById(R.id.btnBack)

        //
        btnShowSongs.setOnClickListener {
            // Inflate the new layout
            val displayedSongsView = layoutInflater.inflate(R.layout.displayed_songs, null)
            val tvPlaylist = displayedSongsView.findViewById<TextView>(R.id.tvPlaylist)

            // Build the playlist string
            val builder = StringBuilder()
            for ((index, song) in MainActivity.songList.withIndex()) {
                builder.append("Song ${index + 1}:\n")
                builder.append("Title: ${song.title}\n")
                builder.append("Artist: ${song.artist}\n")
                builder.append("Rating: ${song.rating}\n")
                builder.append("Comment: ${song.comment}\n\n")
            }

            // Set the string into tvPlaylist and show it in a dialog
            tvPlaylist.text = builder.toString()

            // Show the dialog
            android.app.AlertDialog.Builder(this)
                .setTitle("Songs in Playlist")
                .setView(displayedSongsView)
                .setPositiveButton("OK", null)
                .show()
        }

        // Handle average rating button click
        btnAvgRating.setOnClickListener {
            if (MainActivity.songList.isEmpty()) {
                tvDisplay.text = "No songs in the playlist"
            } else {
                val avg = MainActivity.songList.map { it.rating }.average()
                tvDisplay.text = "Average Rating: %.2f".format(avg)
            }
        }

        // Handle back button click
        btnBack.setOnClickListener {
            finish()
        }
    }
}