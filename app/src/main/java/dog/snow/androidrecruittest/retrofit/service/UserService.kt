package dog.snow.androidrecruittest.retrofit.service

import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawUser
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users/{id}")
    fun searchUsers(@Path("id") id : Int) : Flowable<RawUser>

}