package dog.snow.androidrecruittest.DI


import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dog.snow.androidrecruittest.Database.Details_Dao
import dog.snow.androidrecruittest.Database.Details_Database
import javax.inject.Singleton

@Module
class AppModule  {
    
@Singleton
@Provides
fun provideRoom(application: Application) : Details_Database
{
    return Room.databaseBuilder(application,Details_Database::class.java,"test").build()
}
    @Singleton
    @Provides
    fun provideDao (userDatabase: Details_Database) : Details_Dao
    {
        return userDatabase.getDetailsDAO()
    }

}