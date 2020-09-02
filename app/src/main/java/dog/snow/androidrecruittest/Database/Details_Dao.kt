package dog.snow.androidrecruittest.Database

import androidx.annotation.NonNull
import androidx.room.*
import dog.snow.androidrecruittest.ui.model.Detail

import io.reactivex.Observable
import io.reactivex.Single

@Dao
 interface Details_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(IllegalArgumentException::class)
    fun insertDetails(  detail : ArrayList<Detail>)


    @Query("SELECT * FROM Detail WHERE albumTitle LIKE :query OR photoTitle LIKE :query")
    fun loadAllDetails(query : String) : Observable<List<Detail>>

    @Query("SELECT * FROM Detail")
    fun loadAllDetails() : Observable<List<Detail>>
   @Query("DELETE FROM Detail")
   fun nukeTable()



}