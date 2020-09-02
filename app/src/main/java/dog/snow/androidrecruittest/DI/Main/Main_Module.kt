package dog.snow.androidrecruittest.DI.Main

import dagger.Module
import dagger.Provides
import dog.snow.androidrecruittest.Database.Details_Dao
import dog.snow.androidrecruittest.repository.Main_Repository
import dog.snow.androidrecruittest.retrofit.service.UserService
import retrofit2.Retrofit
import java.util.*


@Module
class Main_Module {

    @Provides
    fun provideMain_Repository(detailsDao: Details_Dao) : Main_Repository
    {
        return Main_Repository(detailsDao)
    }

}


