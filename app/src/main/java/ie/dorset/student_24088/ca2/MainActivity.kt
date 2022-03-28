package ie.dorset.student_24088.ca2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.dorset.student_24088.ca2.databinding.ActivityMainBinding
import ie.dorset.student_24088.ca2.model.Movie
import ie.dorset.student_24088.ca2.network.MovieApi

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called!") // Only for debugging purposes

        // Inflates the associated layout via view binding
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMain.root
        setContentView(view)

        bindingMain.moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val bundle = it.data?.getBundleExtra("Bundle")
                    val position = bundle?.getInt("Position")
                    val movie = bundle?.getParcelable<Movie>("Movie")
                    if (position != null && position >= 0)
                        bindingMain.moviesRecyclerView.adapter?.notifyItemChanged(position, movie!!)
                }
            }

        val baseUrl = "https://www.myvue.com/"
        val endPoint = "data/films/"

        MovieApi().fetch(
            baseUrl,
            endPoint,
            resultLauncher,
            this,
            bindingMain.moviesRecyclerView,
            bindingMain.statusErrorImage
        )

        bindingMain.dataCredits.dataCreditsLink.text = baseUrl.substring(
            baseUrl.indexOf("www."),
            baseUrl.indexOf(".com") + 4
        )

        bindingMain.dataCredits.dataCreditsLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(baseUrl))
            startActivity(intent)
        }
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
        private const val TAG = "MainActivity"
    }
}