package vcmsa.ci.playlistmanagerapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private lateinit var btnShowSongs: Button
    private lateinit var btnAvgRating: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tvDisplay = findViewById(R.id.tvDisplay)
        btnShowSongs = findViewById(R.id.btnShowSongs)
        btnAvgRating = findViewById(R.id.btnAvgRating)
        btnBack = findViewById(R.id.btnBack)

        btnShowSongs.setOnClickListener {
            val builder = StringBuilder()
            for ((index, song) in MainActivity.songList.withIndex()) {
                builder.append("Song ${index + 1}:\n")
                builder.append("Title: ${song.title}\n")
                builder.append("Artist: ${song.artist}\n")
                builder.append("Rating: ${song.rating}\n")
                builder.append("Comment: ${song.comment}\n\n")
            }

            tvDisplay.text = builder.toString()
        }

        btnAvgRating.setOnClickListener {
            if (MainActivity.songList.isEmpty()) {
                tvDisplay.text = "No songs in the playlist"
            } else {
                val avg = MainActivity.songList.map { it.rating }.average()
                tvDisplay.text = "Average Rating: %.2f".format(avg)
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}