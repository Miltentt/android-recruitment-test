package dog.snow.androidrecruittest.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.ui.model.Detail
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
 interface Details_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(IllegalArgumentException::class)
    fun insertUsers(  detail : ArrayList<Detail>)

    @Query("SELECT * FROM Detail WHERE id LIKE :id")
    fun loadDetail(id : Int) : Single<Detail>

    @Query("SELECT * FROM Detail ")
    fun loadAllDetails() : Flowable<List<Detail>>


}