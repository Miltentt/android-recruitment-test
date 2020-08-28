package dog.snow.androidrecruittest.ui.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.repository.Repository
import javax.inject.Inject

class Splash_ViewModel @Inject constructor(val repository: Repository) : ViewModel() {


    fun getPhotos()
    {
        repository.getPhotos()
            .subscribe({ Log.i("xd","worked")},{Log.i("xd","Didnt work")})


    }


}