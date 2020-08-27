package dog.snow.androidrecruittest.retrofit.service

import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("photos")
    fun searchPhotos(@Query("_limit") value:Int)
            : Flowable<RawPhoto>
}