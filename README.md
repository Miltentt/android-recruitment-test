# Snowdog Android Recruitment test

## Used for this project:

- RxJava 2
- Retrofit
- Room Persistance Library
- LiveData
- MVVM
- RxBinding
- Picasso
- LiveData

## Some logic explained

### Handling the Retrofit
It was my first time dealing with calls that are dependant on each other, so I came up with solution using concatmap operator in Rxjava2.
```
return photoService.searchPhotos()
           .concatMapIterable {it}
```
As the call returns the List of photos I used iterable so I could get the photos separately. Then for every photo I use concatmap to make the next call.

```
return getPhotos()
           .concatMap{ it ->  (albumService.searchAlbums(it.albumId).subscribeOn(Schedulers.io()))}
```
```
 return  getAlbums()
            .concatMap { it ->  (userService.searchUsers(it.userId).subscribeOn(Schedulers.io())) }
```
Thanks to that I now got Flowables of every class I need for the program and they are all in the correct order. Because I didnt want to deal with all the data of these Flowables I decided to zip them into one Flowable that in the basic repository is called detail(since I needed the same values for both detailfragment and adapterlist I went with one class)
```
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
```

### Handling the SplashActivity

The app starts off checking if time since last database insertion is over 10 minutes. If so it deletes the database. Then it attempts to fetch data for detail class. Should that fail (Because of no internet acces or jsonplaceholder not being avaliable) it triggers the onError method that checks whether there is a local database of details(if yes, it will load it and proceed with the app). If it's empty it will trigger onerror dialog. If the attempt to fetch the data is sucesful onSucess will trigger and data will be inserted to database. Couple of things to keep in mind: if the data is sucesfully fetched it will always try to override the current database and will reset the 10 minutes timer. I use the same range of ids for the database entities cause Room only compares ids, that let's me easily override the local data.

### MainActivity

From more interesting stuff I use Jake Whartons RxBinding for binding UI widgets with RxJava and use debounce operator so the user doesn't overwhelm the database with requests.

### Conclusion
I actually had a lot of fun doing this project. I got to learn plenty of new things and expand on my knowledge and I think having a set task with boundaries and expectations makes you really adjust your coding and it's a really good way to improve yourself as a dev. Should I have more time I would obviously introduce dark mode to the app, Update the database on the go(if the timer reaches 10 minutes and user is still using the app I'd fetch the data and insert it into database in the background in MainActivity) and I would test the app more thoroughly.


### We are glad that you've joined SNOW.DOG recruitment process. Below is task for you, good luck!

1. Fork this repo to your github.
2. The repository contains app resources: icons, color palette, layouts, fragments and activities. You can use whatever library, design patterns and dependency injection you want. You can add and modify all the things you want, just show us that you know Android :)
3. Your task is to create an app on top of this project:
	1. Your main goal is to provide data
    	* download items from [jsonplaceholder.typicode.com](https://jsonplaceholder.typicode.com):
          1. Download photos from the endpoint [`/photos`](https://jsonplaceholder.typicode.com/photos). There are 5000 items, shrink the list to 100 using request parameter (`_limit=100`). You can use `RawPhoto` model.
          2. Download albums from the endpoint [`/albums/{id}`](https://jsonplaceholder.typicode.com/albums/2) for `ids` that are in the downloaded photos (field: `albumId`). You can use `RawAlbum` model.
          3. Download users from the endpoint [`/users/{id}`](https://jsonplaceholder.typicode.com/users/3) for `ids` that are in the downloaded albums (field: `userId`). You can use `RawUser` model.
        * Download all the items on Splash screen. The rest of the app should use cached data. If there are some problems with connection, display a dialog. You can use `showError()` in `SplashActivity`
        * Display a list with thumbnails on `RecyclerView` in `ListFragment`. You can use `ListItem` model and `ListAdapter` but you can change it or use whatever adapter you want.
        * Display details with a full photo in `DetailFragment` which appears after clicking on an item on the list. You can use `Detail` model.
        * Use `R.id.search` to filter data by `title` and `albumTitle` in `RecyclerView`.
    2. Your second goal is to style the app:
    	* All the necessary colors are defined in `palette.xml`, use them to style your app.
        * Use `R.drawable.ic_placeholder` for placeholders in `ImageViews`.
    3. Optional - if you want more :)
		* In `mock` flavor there are mocks for all requests that you can use while developing. You can finish mock flavor.
        * Use `R.id.banner` to show users if they are in offline mode. The app should start from scratch in offline mode for 10 minutes. After 10 minutes, when the app starts, show a dialog using `showError()` in `SplashActivity`. 
        * Create dark mode for the app. All the variants of logos are also defined in dark mode.
        * Try to create some animations/transitions.
        * Polish your app, try to add something that will blow our minds :)
4. Remember to commit as much as you can. Don't be afraid of mess in your commits, we will not look at your commit history :)
5. Once you've completed the task, please **send us the link to your repo**. We would also be grateful for your **feedback** for this task. Feel free to send us your ideas about it, maybe we can improve it for future candidates.

### Tips:
* jsonplaceholder uses [`placeholder.com`](https://placeholder.com) which returns error 410 on Android devices. You need to change `User-agent` header of requests (to `Cool app` or something different than default).
* jsonplaceholder response may took even one minute. Instead of increasing timeouts, try to retry requests few times. On second/third request it usually works fine.
* If you want to use your http client to cache data, be aware of `Pragma` and `Age` headers.

### Demo:

![Demo][demo]

[demo]: art/demo.gif
