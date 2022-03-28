package ie.dorset.student_24088.ca2.network

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ie.dorset.student_24088.ca2.adapter.MovieAdapter
import ie.dorset.student_24088.ca2.model.Movie
import okhttp3.*
import java.io.IOException

class MovieApi : AppCompatActivity() {
    fun fetch(
        baseUrl: String,
        endPoint: String,
        resultLauncher: ActivityResultLauncher<Intent>,
        context: Context,
        recyclerView: RecyclerView,
        statusErrorImage: ImageView
    ) {
        val client = OkHttpClient()
        val request = Request.Builder().url(baseUrl + endPoint).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "Exception: $e")
                runOnUiThread { statusErrorImage.visibility = View.VISIBLE }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val bodyString = response.body?.string()
                    val jsonString = bodyString?.substring(
                        bodyString.indexOf("[{"),
                        bodyString.indexOf("}]") + 2
                    )
                    val gson = Gson()
                    val movies = gson.fromJson(jsonString, Array<Movie>::class.java)
                    movies.forEach {
                        it.baseUrl = baseUrl
                        it.endPoint = endPoint
                        Log.i(TAG, it.toString())
                    }

                    runOnUiThread {
                        Handler(Looper.getMainLooper()).post {
                            val moviesAdapter = MovieAdapter(movies, resultLauncher, context)
                            recyclerView.adapter = moviesAdapter
                        }
                    }
                }
            }
        })
    }

    companion object {
        private const val TAG: String = "MovieAPI"
    }
}