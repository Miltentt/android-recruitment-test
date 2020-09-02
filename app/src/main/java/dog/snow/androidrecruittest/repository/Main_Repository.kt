package dog.snow.androidrecruittest.repository

import android.util.Log
import dog.snow.androidrecruittest.Database.Details_Dao
import dog.snow.androidrecruittest.Database.Details_Database

import dog.snow.androidrecruittest.ui.model.Detail

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Main_Repository @Inject constructor( private val detailsDao: Details_Dao ) {




    fun loadDetails(query: String): Observable<List<Detail>> {
        return detailsDao.loadAllDetails("%" + query + "%")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}

