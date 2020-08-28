package dog.snow.androidrecruittest

import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.retrofit.service.PhotoService
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class RESTFetchTest {
    @Test
    @Throws(IOException::class)
    fun testRestFetch() {
        val subscriber = TestSubscriber<List<RawPhoto>>()
        val rf = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val service = rf.create(PhotoService::class.java)
        service.searchPhotos().subscribe(subscriber)
        subscriber.assertComplete()
        subscriber.assertNoErrors()
        subscriber.assertValue({t->t.size==100})


    }
}