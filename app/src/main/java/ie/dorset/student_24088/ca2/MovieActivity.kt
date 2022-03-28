package ie.dorset.student_24088.ca2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
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

        val bundle = intent.extras?.getBundle("Bundle")!!
        position = bundle.getInt("Position")
        movie = bundle.getParcelable("Movie")!!

        bindingMovie.title.text = movie.title
        bindingMovie.starring.text = movie.info_cast
        bindingMovie.runningTime.text = movie.info_runningtime
        bindingMovie.description.text = movie.synopsis_short
        bindingMovie.buttonMinusEnabled.setImageResource(R.drawable.ic_baseline_remove)
        bindingMovie.buttonMinusDisabled.setImageResource(R.drawable.ic_baseline_remove)
        bindingMovie.seatNumber.text = movie.seatsSelected.toString()
        bindingMovie.buttonPlusEnabled.setImageResource(R.drawable.ic_baseline_add)
        bindingMovie.buttonPlusDisabled.setImageResource(R.drawable.ic_baseline_add)
        bindingMovie.seatIcon.setImageResource(R.drawable.ic_baseline_chair)
        bindingMovie.seatAvailabilityNumber.text = movie.seatsRemaining.toString()

        Picasso.get()
            .load(movie.baseUrl + movie.image_hero)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_connection_error)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(findViewById<ImageView>(R.id.image))

        Picasso.get()
            .load(movie.baseUrl + movie.cert_image)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_connection_error)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(findViewById<ImageView>(R.id.cert_icon))

        if (movie.seatsSelected == 0) {
            bindingMovie.buttonMinusEnabled.visibility = GONE
            bindingMovie.buttonMinusDisabled.visibility = VISIBLE
        } else {
            bindingMovie.buttonMinusEnabled.visibility = VISIBLE
            bindingMovie.buttonMinusDisabled.visibility = GONE
        }

        if (movie.seatsRemaining == 0) {
            bindingMovie.buttonPlusEnabled.visibility = GONE
            bindingMovie.buttonPlusDisabled.visibility = VISIBLE
        } else {
            bindingMovie.buttonPlusEnabled.visibility = VISIBLE
            bindingMovie.buttonPlusDisabled.visibility = GONE
        }

        bindingMovie.buttonMinusEnabled.setOnClickListener {
            if (movie.seatsSelected != 0) {
                movie.seatsSelected--
                bindingMovie.seatNumber.text = movie.seatsSelected.toString()
                movie.seatsRemaining++
                bindingMovie.seatAvailabilityNumber.text = movie.seatsRemaining.toString()
                bindingMovie.buttonPlusEnabled.visibility = VISIBLE
                bindingMovie.buttonPlusDisabled.visibility = GONE
                if (movie.seatsSelected == 0) {
                    bindingMovie.buttonMinusEnabled.visibility = GONE
                    bindingMovie.buttonMinusDisabled.visibility = VISIBLE
                }
            } else {
                bindingMovie.buttonMinusEnabled.visibility = GONE
                bindingMovie.buttonMinusDisabled.visibility = VISIBLE
            }
        }

        bindingMovie.buttonPlusEnabled.setOnClickListener {
            if (movie.seatsRemaining != 0) {
                movie.seatsSelected++
                bindingMovie.seatNumber.text = movie.seatsSelected.toString()
                movie.seatsRemaining--
                bindingMovie.seatAvailabilityNumber.text = movie.seatsRemaining.toString()
                bindingMovie.buttonMinusEnabled.visibility = VISIBLE
                bindingMovie.buttonMinusDisabled.visibility = GONE
                if (movie.seatsRemaining == 0) {
                    bindingMovie.buttonPlusEnabled.visibility = GONE
                    bindingMovie.buttonPlusDisabled.visibility = VISIBLE
                }
            } else {
                bindingMovie.buttonPlusEnabled.visibility = GONE
                bindingMovie.buttonPlusDisabled.visibility = VISIBLE
            }
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

        val dataChanged = Intent().putExtra("Bundle", bundle)

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
    // Debugging purposes
    companion object {
        private const val TAG = "MovieActivity"
    }
}