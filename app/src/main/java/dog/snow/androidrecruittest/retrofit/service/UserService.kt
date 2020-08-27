package dog.snow.androidrecruittest.retrofit.service

import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawUser
import io.reactivex.Flowable
import retrofit2.http.GET

interface UserService {
    @GET("")
    fun searchUsers() : Flowable<RawUser>

}