package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.Database.Details_Dao
import dog.snow.androidrecruittest.ui.model.Detail
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Main_Repository @Inject constructor(val detailsDao: Details_Dao) {

fun loadDetails() : Observable<List<Detail>>
{
return detailsDao.loadAllDetails()
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

}
fun loadDetail(id : Int) : Single<Detail>
{
    return detailsDao.loadDetail(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}


}