package dog.snow.androidrecruittest.ui.ViewModels

import android.graphics.ColorSpace
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

import dog.snow.androidrecruittest.repository.Splash_Repository
import dog.snow.androidrecruittest.ui.model.Detail
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class Splash_ViewModel @Inject constructor(val repository: Splash_Repository) : ViewModel() {

    private var disposable = CompositeDisposable()
    private var details = ArrayList<Detail>()
    private var outdatedLiveData = MutableLiveData<Boolean>()
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
       disposable.add(repository.deleteDB().subscribe())
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    fun calculateTime(creationTime : Long) : LiveData<Boolean>
    {
        if(Calendar.getInstance().timeInMillis-creationTime>600000 && creationTime!=-1L)
            outdatedLiveData.value=true
        return outdatedLiveData
    }
}




