package dog.snow.androidrecruittest

import dog.snow.androidrecruittest.Database.Details_Dao
import dog.snow.androidrecruittest.Database.Details_Database
import dog.snow.androidrecruittest.repository.Main_Repository
import dog.snow.androidrecruittest.repository.Splash_Repository
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.ui.model.Detail
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock


class SplashRepository_Testing : Retrofit_Test() {


    val photoService = returnPhoto()
    val albumService = returnAlbum()
    val userService = returnUser()


   // fetch 100 photos
    @Test
    fun fetch100Photos()
    {
        var subscriber = TestSubscriber<RawPhoto>()
        returnPhoto().searchPhotos().flatMapIterable {it}
            .subscribe(subscriber)

        subscriber.assertValueCount(100)

    }
    //fetch 100 albums
    @Test
     fun fetch100Albums()
    {
        var subscriber = TestSubscriber<RawAlbum>()
        returnPhoto().searchPhotos().flatMapIterable {it}
            .concatMap{ it ->  (albumService.searchAlbums(it.albumId))}
            .subscribe(subscriber)

        subscriber.assertValueCount(100)
    }

    //fetch 100 users
    @Test
    fun fetch100Users()
    {
        var subscriber = TestSubscriber<RawUser>()
        returnPhoto().searchPhotos().flatMapIterable {it}
            .concatMap{ it ->  (albumService.searchAlbums(it.albumId))}
            .concatMap { it ->  (userService.searchUsers(it.userId))}
            .subscribe(subscriber)

        subscriber.assertValueCount(100)
    }

    //zip the fetched
    @Test
    fun zipFetched()
    {
        var subscriber = TestSubscriber<Detail>()
        var i=0;
        Flowable.zip(
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
            .subscribe(subscriber)

        subscriber.assertValueCount(100)
        subscriber.assertComplete()
        subscriber.assertNoErrors()


    }

    fun getPhotos() : Flowable<RawPhoto>
    {
        return photoService.searchPhotos()
            .concatMapIterable {it}

    }

    fun getAlbums() : Flowable<RawAlbum>
    {
        return getPhotos()
            .concatMap{ it ->  (albumService.searchAlbums(it.albumId))}
    }
    fun getUsers() : Flowable<RawUser>
    {
        return  getAlbums()
            .concatMap { it ->  (userService.searchUsers(it.userId)) }
    }







}