package dog.snow.androidrecruittest.DI.Splash

import dagger.Module
import dagger.Provides
import dog.snow.androidrecruittest.Database.Details_Dao
import dog.snow.androidrecruittest.Database.Details_Database
import dog.snow.androidrecruittest.repository.Splash_Repository
import dog.snow.androidrecruittest.retrofit.service.AlbumService
import dog.snow.androidrecruittest.retrofit.service.PhotoService
import dog.snow.androidrecruittest.retrofit.service.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class Splash_Module {

    @Provides
    fun makeClient() : OkHttpClient
    {
       val httpclient = OkHttpClient.Builder()
        httpclient.addInterceptor { chain ->
            var original = chain.request()
            var request = original.newBuilder()
                .addHeader("User-Agent", "Android Recruit Test")
                .method(original.method(), original.body())
                .build();
            chain.proceed(request)
        }
        val client= httpclient.build()
return  client
    }


    @Provides
    fun provideRetrofitInstance(client: OkHttpClient) : Retrofit
    {
        return  Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provide_Photo_Service(retrofit : Retrofit) : PhotoService
    {
        return retrofit.create(PhotoService::class.java)
    }
    @Provides
    fun provide_Album_Service(retrofit : Retrofit) : AlbumService
    {
        return retrofit.create(AlbumService::class.java)
    }
    @Provides
    fun provide_User_Service(retrofit : Retrofit) : UserService
    {
        return retrofit.create(UserService::class.java)
    }
    @Provides
    fun provide_Splash_Repository(photoService: PhotoService,albumService: AlbumService,userService: UserService,detailsDatabase: Details_Database,detailsDao: Details_Dao ) : Splash_Repository
    {
        return Splash_Repository(photoService,albumService,userService,detailsDao,detailsDatabase)
    }

}