package dog.snow.androidrecruittest.retrofit.service

import dog.snow.androidrecruittest.repository.model.RawAlbum
import io.reactivex.Flowable
import retrofit2.http.GET

interface AlbumService {
    @GET("")
    fun searchAlbums() : Flowable<RawAlbum>

}