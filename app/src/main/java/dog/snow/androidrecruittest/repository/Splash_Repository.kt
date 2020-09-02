package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.Database.Details_Dao
import dog.snow.androidrecruittest.Database.Details_Database

import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.retrofit.service.AlbumService
import dog.snow.androidrecruittest.retrofit.service.PhotoService
import dog.snow.androidrecruittest.retrofit.service.UserService
import dog.snow.androidrecruittest.ui.model.Detail


import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Splash_Repository @Inject constructor(val photoService: PhotoService, val albumService: AlbumService, val userService: UserService,val detailsDao: Details_Dao,private val detailsDatabase: Details_Database) {

    private var i :Int = 0
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


    fun getEverything() : Flowable<Detail>{
        return Flowable.zip(
            getPhotos(),
            getAlbums(),
            getUsers(),
            Function3<RawPhoto, RawAlbum, RawUser, Detail>
            { photos: RawPhoto, albums: RawAlbum, users: RawUser ->
                Detail(id =i++,photoId = photos.id,
                    photoTitle = photos.title,albumTitle = albums.title,
                    username =users.username,email = users.email,
                    phone = users.phone,url = photos.url)
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertIntoDatabase(details : ArrayList<Detail>) : Completable
    {
        return Completable.fromAction{detailsDao.insertDetails(details)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
    fun checkIfDatabaseEmpty() : Observable<List<Detail>>
    {
       return detailsDao.loadAllDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteDB() : Completable
    {
       return Completable.fromAction{detailsDatabase.clearAllTables()}
            .subscribeOn(Schedulers.io())

    }



}




