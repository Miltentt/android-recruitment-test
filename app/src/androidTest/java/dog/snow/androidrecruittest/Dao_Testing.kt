package dog.snow.androidrecruittest

import dog.snow.androidrecruittest.ui.model.Detail
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.TimeUnit

class Dao_Testing : Database_Test() {
    // Insert Read
    @Test
    fun insertThenRead()
    {
      //Arrange
        var list = ArrayList<Detail>()
        var detail1 = Detail(0,1,"title","albumtitle","bob","xxx@x","89867878789","www.dsa.diks")
        var detail2 = Detail(1,0,"uio","hhh","bobb","xxmx@x","898678vbn78789","www.dsauu.diks")
        list.add(detail1)
        list.add(detail2)
        var subscriber = TestObserver<List<Detail>>()

        //Action
        getDao().insertDetails(list)
        Thread.sleep(2000)
        getDao().loadAllDetails().subscribe(subscriber)

        //  Assert
        subscriber.assertValue(list.toList())

    }

    //Insert Delete check if empty
    @Test
    fun insertDeleteCheckIfEmpty()
    {
        //Arrange
        var list = ArrayList<Detail>()
        var detail1 = Detail(0,1,"title","albumtitle","bob","xxx@x","89867878789","www.dsa.diks")
        var detail2 = Detail(1,0,"uio","hhh","bobb","xxmx@x","898678vbn78789","www.dsauu.diks")
        list.add(detail1)
        list.add(detail2)
        var subscriber = TestObserver<List<Detail>>()

        //Action
        getDao().insertDetails(list)
        Thread.sleep(2000)
        getDao().nukeTable()
        Thread.sleep(2000)
        getDao().loadAllDetails().subscribe(subscriber)

        //Assert
        subscriber.assertNoValues()

    }

    // Replace entity with same id
    @Test
    fun ReplaceWithSameID()
    {
        //Arrange
        var list = ArrayList<Detail>()
        var list2 =  ArrayList<Detail>()
        var detail1 = Detail(0,1,"title","albumtitle","bob","xxx@x","89867878789","www.dsa.diks")
        var detail2 = Detail(0,0,"uio","hhh","bobb","xxmx@x","898678vbn78789","www.dsauu.diks")
        list.add(detail1)
        list2.add(detail2)
        var subscriber = TestObserver<List<Detail>>()

       //Action
        getDao().insertDetails(list)
        Thread.sleep(2000)
        getDao().insertDetails(list2)
        Thread.sleep(2000)
        getDao().loadAllDetails().subscribe(subscriber)

        //  Assert
        subscriber.assertValue(list2.toList())
    }


}