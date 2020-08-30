package dog.snow.androidrecruittest.ui.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import dog.snow.androidrecruittest.repository.model.RawPhoto
import kotlinx.android.parcel.Parcelize

@Entity
data class Detail(
    @PrimaryKey(autoGenerate = true)
    val id : Int =0,
    val photoId: Int,
    val photoTitle: String,
    var albumTitle: String="",
    var username: String="",
    var email: String="",
    var phone: String="",
    val url: String
)