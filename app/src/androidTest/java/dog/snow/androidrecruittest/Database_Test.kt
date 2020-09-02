package dog.snow.androidrecruittest

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dog.snow.androidrecruittest.Database.Details_Dao
import dog.snow.androidrecruittest.Database.Details_Database
import org.junit.After

open class Database_Test {

    private val DetailDB = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),Details_Database::class.java).allowMainThreadQueries()
        .build()

    fun getDao () : Details_Dao
    {
        return DetailDB.getDetailsDAO()
    }


    @After
    public fun finish()
    {
        DetailDB.close()
    }


}
