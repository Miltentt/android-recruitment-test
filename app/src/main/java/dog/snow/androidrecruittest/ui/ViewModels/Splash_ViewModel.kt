package dog.snow.androidrecruittest.ui.ViewModels

import android.graphics.ColorSpace
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.repository.Repository
import dog.snow.androidrecruittest.ui.model.Detail
import dog.snow.androidrecruittest.ui.model.Detail_Model
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class Splash_ViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private var details = ArrayList<Detail>()

     fun getEverything() : Flowable<Detail>
     {
      return repository.getEverything()

     }

    fun insertIntoDatabase() : Completable
    {
        return repository.insertIntoDatabase(details)
    }

    fun populateList(detail: Detail)
    {
        details.add(detail)
    }

    fun checkIfDatabaseEmpty() : Observable<List<Detail>> {
        return repository.checkIfDatabaseEmpty()
    }

    fun deleteDB()
    {
        repository.deleteDB().subscribe()
    }









    }




