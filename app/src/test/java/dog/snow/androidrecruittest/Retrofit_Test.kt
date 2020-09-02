package dog.snow.androidrecruittest

import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.retrofit.service.AlbumService
import dog.snow.androidrecruittest.retrofit.service.PhotoService
import dog.snow.androidrecruittest.retrofit.service.UserService
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


open class Retrofit_Test {


        val rf = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        fun returnPhoto(): PhotoService {
            var servicephoto = rf.create(PhotoService::class.java)
            return servicephoto
        }

        fun returnAlbum(): AlbumService {
            var servicealbum = rf.create(AlbumService::class.java)
            return servicealbum
        }
       fun returnUser() : UserService {
           var serviceuser = rf.create(UserService::class.java)
       return serviceuser
       }




    }
