package dog.snow.androidrecruittest.ui.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.repository.Repository
import dog.snow.androidrecruittest.ui.model.Detail
import dog.snow.androidrecruittest.ui.model.Detail_Model
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject


class Splash_ViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private var details = ArrayList<Detail>()

     fun getEverything() : Flowable<Detail_Model>
     {
      return repository.getEverything()
     }


     fun addDetails(detail: Detail_Model)
     {
         details.add(Detail(photoId = detail.rawPhoto.id,
             photoTitle = detail.rawPhoto.title,albumTitle = detail.rawAlbum.title,
             username = detail.rawUser.username,email = detail.rawUser.email,
             phone = detail.rawUser.phone,url = detail.rawPhoto.url))
     }
    fun insertIntoDatabase() : Completable
    {
        return repository.insertIntoDatabase(details)
    }






    }




