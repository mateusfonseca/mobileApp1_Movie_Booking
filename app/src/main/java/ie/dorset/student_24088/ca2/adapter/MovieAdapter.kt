package ie.dorset.student_24088.ca2.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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
        if (payloads.isNotEmpty()) {
            if (payloads[0] is Movie) {
                val movieUpdated = payloads[0] as Movie
                movies[position] = movieUpdated
                availabilityCheck(holder, movieUpdated)
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]

        holder.itemView.findViewById<TextView>(R.id.title).text = movie.title
        holder.itemView.findViewById<TextView>(R.id.starring).text = movie.info_cast
        holder.itemView.findViewById<TextView>(R.id.running_time).text = movie.info_runningtime
        holder.itemView.findViewById<ImageView>(R.id.seat_icon)
            .setImageResource(R.drawable.ic_baseline_chair)

        availabilityCheck(holder, movie)

        val image = holder.itemView.findViewById<ImageView>(R.id.image)
        val imageUrl = movie.baseUrl + movie.image_hero
        Picasso.get()
            .load(imageUrl)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.loading_animation)!!)
            .error(ContextCompat.getDrawable(context, R.drawable.ic_connection_error)!!)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(image)

        val certIcon = holder.itemView.findViewById<ImageView>(R.id.cert_icon)
        val certIconUrl = movie.baseUrl + movie.cert_image
        Picasso.get()
            .load(certIconUrl)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.loading_animation)!!)
            .error(ContextCompat.getDrawable(context, R.drawable.ic_connection_error)!!)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(certIcon)

        val movieView = holder.itemView.findViewById<LinearLayout>(R.id.movie)

        movieView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("Position", position)
            bundle.putParcelable("Movie", movie)
            val intent =
                Intent(context, MovieActivity::class.java).putExtra("Bundle", bundle)
            resultLauncher.launch(intent)
        }
    }

    private fun availabilityCheck(holder: MoviesViewHolder, movie: Movie) {
        if (movie.seatsSelected > 0) {
            holder.itemView.findViewById<ImageView>(R.id.seat_icon)
                .setColorFilter(ContextCompat.getColor(context, R.color.green))
            holder.itemView.findViewById<TextView>(R.id.seat_availability_number).text =
                movie.seatsSelected.toString()
            holder.itemView.findViewById<TextView>(R.id.seat_availability_number).setTextColor(
                ContextCompat.getColor(context, R.color.green)
            )
            holder.itemView.findViewById<TextView>(R.id.seat_availability_text).text =
                context.getString(R.string.seats_selected)
            holder.itemView.findViewById<TextView>(R.id.seat_availability_text).setTextColor(
                ContextCompat.getColor(context, R.color.green)
            )
            holder.itemView.findViewById<TextView>(R.id.filling_fast).visibility = GONE
        } else {
            holder.itemView.findViewById<TextView>(R.id.seat_availability_number).text =
                movie.seatsRemaining.toString()
            holder.itemView.findViewById<TextView>(R.id.seat_availability_text).text =
                context.getString(R.string.seats_remaining)
            if (movie.seatsRemaining < 3) {
                holder.itemView.findViewById<ImageView>(R.id.seat_icon)
                    .setColorFilter(ContextCompat.getColor(context, R.color.red))
                holder.itemView.findViewById<TextView>(R.id.seat_availability_number).setTextColor(
                    ContextCompat.getColor(context, R.color.red)
                )
                holder.itemView.findViewById<TextView>(R.id.seat_availability_text).setTextColor(
                    ContextCompat.getColor(context, R.color.red)
                )
                holder.itemView.findViewById<TextView>(R.id.filling_fast).visibility = VISIBLE
            } else {
                holder.itemView.findViewById<ImageView>(R.id.seat_icon)
                    .setColorFilter(ContextCompat.getColor(context, R.color.light_gray))
                holder.itemView.findViewById<TextView>(R.id.seat_availability_number).setTextColor(
                    ContextCompat.getColor(context, R.color.light_gray)
                )
                holder.itemView.findViewById<TextView>(R.id.seat_availability_text).setTextColor(
                    ContextCompat.getColor(context, R.color.light_gray)
                )
                holder.itemView.findViewById<TextView>(R.id.filling_fast).visibility = GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MoviesViewHolder(v: View) : RecyclerView.ViewHolder(v)
}