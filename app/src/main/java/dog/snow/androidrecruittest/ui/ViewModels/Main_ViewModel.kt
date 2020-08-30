package dog.snow.androidrecruittest.ui.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.repository.Main_Repository
import dog.snow.androidrecruittest.ui.model.Detail
import io.reactivex.BackpressureStrategy
import javax.inject.Inject

class Main_ViewModel @Inject constructor(val mainRepository: Main_Repository) : ViewModel() {

fun getDetails() : LiveData<List<Detail>>
{
    return LiveDataReactiveStreams.fromPublisher(mainRepository.loadDetails().toFlowable(BackpressureStrategy.BUFFER))

}


}