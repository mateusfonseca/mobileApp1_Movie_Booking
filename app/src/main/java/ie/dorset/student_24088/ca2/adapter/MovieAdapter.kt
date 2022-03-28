package ie.dorset.student_24088.ca2.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ie.dorset.student_24088.ca2.MovieActivity
import ie.dorset.student_24088.ca2.R
import ie.dorset.student_24088.ca2.model.Movie

class MovieAdapter(
    private val movies: Array<Movie>,
    private val resultLauncher: ActivityResultLauncher<Intent>,
    private val context: Context
) : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movies_recycler_template, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MoviesViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        Log.d(TAG, "trying to change")
        if (payloads.isNotEmpty()) {
            Log.d(TAG, "trying to change one more")
            if (payloads[0] is Movie) {
                val movieUpdated = payloads[0] as Movie
                Log.d(TAG, (movieUpdated).seatsSelected.toString())
                movies[position] = movieUpdated
            }
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.itemView.findViewById<TextView>(R.id.title).text = movie.title
        holder.itemView.findViewById<TextView>(R.id.starring).text = movie.info_cast
        holder.itemView.findViewById<TextView>(R.id.running_time).text = movie.info_runningtime

        if (movie.seatsSelected > 0) {
            holder.itemView.findViewById<TextView>(R.id.seat_availability_number).text =
                movie.seatsSelected.toString()
        } else {
            if (movie.seatsRemaining < 3) {
                holder.itemView.findViewById<TextView>(R.id.seat_availability_number)
                    .setTextColor(Color.parseColor("#FF0000"))
            }
            holder.itemView.findViewById<TextView>(R.id.seat_availability_number).text =
                movie.seatsRemaining.toString()
        }

        val image = holder.itemView.findViewById<ImageView>(R.id.image)
        val certIcon = holder.itemView.findViewById<ImageView>(R.id.cert_icon)
        val seatIcon = holder.itemView.findViewById<ImageView>(R.id.seat_icon)

        val imageUrl = "https://www.myvue.com${movie.image_hero}"
        Picasso.get()
            .load(imageUrl)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)!!)
            .error(ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)!!)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(image)

        val certIconUrl = "https://www.myvue.com${movie.cert_image}"
        Picasso.get()
            .load(certIconUrl)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)!!)
            .error(ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)!!)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(certIcon)

        seatIcon.setImageResource(R.drawable.ic_baseline_chair)

        val movieView = holder.itemView.findViewById<LinearLayout>(R.id.movie)

        movieView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("Position", position)
            bundle.putParcelable("Movie", movie)
            val intent =
                Intent(context, MovieActivity::class.java).putExtra("MovieBundle", bundle)
            resultLauncher.launch(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    companion object {
        private const val TAG = "MovieAdapter"
    }

    class MoviesViewHolder(v: View) : RecyclerView.ViewHolder(v)
}