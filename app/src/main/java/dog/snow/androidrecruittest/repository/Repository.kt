package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.Database.Details_Dao
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.retrofit.service.AlbumService
import dog.snow.androidrecruittest.retrofit.service.PhotoService
import dog.snow.androidrecruittest.retrofit.service.UserService
import dog.snow.androidrecruittest.ui.model.Detail
import dog.snow.androidrecruittest.ui.model.Detail_Model
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(val photoService: PhotoService, val albumService: AlbumService, val userService: UserService,val detailsDao: Details_Dao) {

    fun getPhotos() : Flowable<RawPhoto>
    {
       return photoService.searchPhotos()
           .concatMapIterable {it}
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAlbums() : Flowable<RawAlbum>
    {
      return getPhotos()
           .concatMap{ it ->  (albumService.searchAlbums(it.albumId).subscribeOn(Schedulers.io()))}
    }
    fun getUsers() : Flowable<RawUser>
    {
       return  getAlbums()
            .concatMap { it ->  (userService.searchUsers(it.userId).subscribeOn(Schedulers.io())) }
    }


    fun getEverything() : Flowable<Detail_Model>{
        return Flowable.zip(
            getPhotos(),
            getAlbums(),
            getUsers(),
            Function3<RawPhoto, RawAlbum, RawUser, Detail_Model>
            { photos: RawPhoto, albums: RawAlbum, users: RawUser ->
                Detail_Model(photos, albums, users)
            })
    }

    fun insertIntoDatabase(details : ArrayList<Detail>) : Completable
    {
        return Completable.fromAction{detailsDao.insertUsers(details)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }



}




