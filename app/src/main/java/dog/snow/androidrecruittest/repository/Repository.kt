package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.retrofit.service.PhotoService
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class Repository @Inject constructor(val photoService: PhotoService) {

    fun getPhotos() : Flowable<List<RawPhoto>>
    {
       return photoService.searchPhotos()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
    }


}