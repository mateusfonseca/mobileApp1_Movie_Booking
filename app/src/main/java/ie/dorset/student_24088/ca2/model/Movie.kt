package ie.dorset.student_24088.ca2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Movie(
    var title: String = "Title",
    var image_hero: String = "Image",
    var cert_image: String = "Certification",
    var synopsis_short: String = "Synopsis",
    var info_cast: String = "Starring",
    var info_runningtime: String = "Running time",
    var seatsRemaining: Int = Random.nextInt(16),
    var seatsSelected: Int = 0,
    var baseUrl: String = "https://www.website.com/",
    var endPoint: String = "data/"
) : Parcelable