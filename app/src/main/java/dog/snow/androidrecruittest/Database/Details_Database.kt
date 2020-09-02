package dog.snow.androidrecruittest.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import dog.snow.androidrecruittest.ui.model.Detail

@Database(entities = [Detail::class], version = 2)
abstract class Details_Database : RoomDatabase() {

    abstract fun getDetailsDAO() : Details_Dao



}