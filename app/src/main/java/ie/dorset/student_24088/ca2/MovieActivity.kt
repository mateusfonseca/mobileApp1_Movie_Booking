package ie.dorset.student_24088.ca2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ie.dorset.student_24088.ca2.databinding.ActivityMovieBinding
import ie.dorset.student_24088.ca2.model.Movie

class MovieActivity : AppCompatActivity() {
    private lateinit var bindingMovie: ActivityMovieBinding
    private lateinit var movie: Movie
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called!") // Only for debugging purposes

        // Inflates the associated layout via view binding
        bindingMovie = ActivityMovieBinding.inflate(layoutInflater)
        val view = bindingMovie.root
        setContentView(view)

        val bundle = intent.extras?.getBundle("MovieBundle")!!
        position = bundle.getInt("Position")
        movie = bundle.getParcelable("Movie")!!
        Log.d(TAG, position.toString())
        Log.d(TAG, movie.toString())

        val buttonRemoveEnabled = findViewById<ImageView>(R.id.button_minus_enabled)
        val buttonRemoveDisabled = findViewById<ImageView>(R.id.button_minus_disabled)
        val seatNumber = findViewById<TextView>(R.id.seat_number)
        val buttonAddEnabled = findViewById<ImageView>(R.id.button_plus_enabled)
        val buttonAddDisabled = findViewById<ImageView>(R.id.button_plus_disabled)
        val seatsRemaining = findViewById<TextView>(R.id.seat_availability_number)

//        findViewById<ImageView>(R.id.image).sour = movies[position!!].image_hero
        findViewById<TextView>(R.id.title).text = movie.title
        findViewById<TextView>(R.id.starring).text = movie.info_cast
        findViewById<TextView>(R.id.running_time).text = movie.info_runningtime
        findViewById<TextView>(R.id.running_time).text = movie.synopsis_short
        buttonRemoveEnabled.setImageResource(R.drawable.ic_baseline_remove)
        buttonRemoveDisabled.setImageResource(R.drawable.ic_baseline_remove)
        seatNumber.text = movie.seatsSelected.toString()
        buttonAddEnabled.setImageResource(R.drawable.ic_baseline_add)
        buttonAddDisabled.setImageResource(R.drawable.ic_baseline_add)
        findViewById<ImageView>(R.id.seat_icon).setImageResource(R.drawable.ic_baseline_chair)
        seatsRemaining.text = movie.seatsRemaining.toString()

        Picasso.get()
            .load("https://www.myvue.com" + movie.image_hero)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(findViewById<ImageView>(R.id.image))

        Picasso.get()
            .load("https://www.myvue.com" + movie.cert_image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(findViewById<ImageView>(R.id.cert_icon))

        if (movie.seatsSelected == 0) {
            buttonRemoveEnabled.visibility = GONE
            buttonRemoveDisabled.visibility = VISIBLE
        } else {
            buttonRemoveEnabled.visibility = VISIBLE
            buttonRemoveDisabled.visibility = GONE
        }

        if (movie.seatsRemaining == 0) {
            buttonAddEnabled.visibility = GONE
            buttonAddDisabled.visibility = VISIBLE
        } else {
            buttonAddEnabled.visibility = VISIBLE
            buttonAddDisabled.visibility = GONE
        }

        buttonRemoveEnabled.setOnClickListener {
            if (movie.seatsSelected != 0) {
                movie.seatsSelected--
                seatNumber.text = movie.seatsSelected.toString()
                movie.seatsRemaining++
                seatsRemaining.text = movie.seatsRemaining.toString()
                buttonAddEnabled.visibility = VISIBLE
                buttonAddDisabled.visibility = GONE
                if (movie.seatsSelected == 0) {
                    buttonRemoveEnabled.visibility = GONE
                    buttonRemoveDisabled.visibility = VISIBLE
                }
            } else {
                buttonRemoveEnabled.visibility = GONE
                buttonRemoveDisabled.visibility = VISIBLE
            }
//            buttonRemove.isClickable = movie.seatsSelected != 0
//            buttonAdd.isClickable = movie.seatsRemaining != 0
        }

        buttonAddEnabled.setOnClickListener {
            if (movie.seatsRemaining != 0) {
                movie.seatsSelected++
                seatNumber.text = movie.seatsSelected.toString()
                movie.seatsRemaining--
                seatsRemaining.text = movie.seatsRemaining.toString()
                buttonRemoveEnabled.visibility = VISIBLE
                buttonRemoveDisabled.visibility = GONE
                if (movie.seatsRemaining == 0) {
                    buttonAddEnabled.visibility = GONE
                    buttonAddDisabled.visibility = VISIBLE
                }
            } else {
                buttonAddEnabled.visibility = GONE
                buttonAddDisabled.visibility = VISIBLE
            }
//            buttonRemove.isClickable = movie.seatsSelected != 0
//            buttonAdd.isClickable = movie.seatsRemaining != 0
        }

        bindingMovie.dataCredits.dataCreditsLink.text = movie.baseUrl.substring(
            movie.baseUrl.indexOf("www."),
            movie.baseUrl.indexOf(".com") + 4
        )

        bindingMovie.dataCredits.dataCreditsLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.baseUrl))
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable("Movie", movie)
        bundle.putInt("Position", position)
        Log.d(TAG, bundle.getInt("Position", -5).toString())
        val dataChanged = Intent()
        dataChanged.putExtra("Bundle", bundle)
        Log.d(TAG, "$RESULT_OK")
        Log.d(TAG, position.toString())
        Log.d(TAG, movie.toString())
        Log.d(TAG, dataChanged.getBundleExtra("Bundle")?.getInt("Position").toString())
        setResult(RESULT_OK, dataChanged)
        super.onBackPressed()
    }

    // Only for debugging purposes
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart called!")
    }

    // Only for debugging purposes
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called!")
    }

    // Only for debugging purposes
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called!")
    }

    // Only for debugging purposes
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called!")
    }

    // Only for debugging purposes
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called!")
    }

    // Only for debugging purposes
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called!")
    }

    // TAG used to identify the activity.
    // Debugging purposes and on ActivitySetter call
    companion object {
        private const val TAG = "MovieActivity"
    }
}