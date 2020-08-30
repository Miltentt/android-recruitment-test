package dog.snow.androidrecruittest.retrofit.service

import dog.snow.androidrecruittest.repository.model.RawAlbum
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("/albums/{id}")
    fun searchAlbums(@Path("id") id : Int) : Flowable<RawAlbum>

}