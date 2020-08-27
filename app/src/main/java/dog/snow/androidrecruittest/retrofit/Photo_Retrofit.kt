package dog.snow.androidrecruittest.retrofit

import dog.snow.androidrecruittest.retrofit.service.AlbumService
import dog.snow.androidrecruittest.retrofit.service.PhotoService
import dog.snow.androidrecruittest.retrofit.service.UserService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Photo_Retrofit {

    init {
       val retrofit =Retrofit.Builder().
        baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        albumService=retrofit.create(AlbumService::class.java)
        photoService = retrofit.create(PhotoService::class.java)
        userService = retrofit.create(UserService::class.java)
    }
companion object
{
   lateinit var albumService :AlbumService
   lateinit var photoService: PhotoService
   lateinit var userService: UserService
}

}