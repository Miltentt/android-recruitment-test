package dog.snow.androidrecruittest.ui.ViewModels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import dog.snow.androidrecruittest.Util.Timer_Class
import dog.snow.androidrecruittest.repository.Main_Repository
import dog.snow.androidrecruittest.ui.model.Detail
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject
import androidx.lifecycle.ViewModel as ViewModel1

class Main_ViewModel @Inject constructor(val mainRepository: Main_Repository, val timerClass: Timer_Class) : ViewModel1() {

    private  var livedataa : MutableLiveData<List<Detail>> = MutableLiveData()


    fun getLiveData() : LiveData<List<Detail>>
{
    return livedataa
}


fun getDetails(query : String)
{
    mainRepository.loadDetails(query)
        .subscribe({livedataa.apply{ value = it}},{Log.i("Database query","Something went wrong with Database query")})

}
    fun timerStart()
    {
        timerClass.startTimer()
    }
    fun timerCancel()
    {
        timerClass.cancelTimer()
    }
    fun returnTimerLiveData() : LiveData<Boolean>
    {
        return timerClass.returnTimerLiveData()
    }


}