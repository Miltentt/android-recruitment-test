package dog.snow.androidrecruittest.Util

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Timer_Class {
//600000
    val timerlivedata = MutableLiveData<Boolean>()
    init {
        timerlivedata.value = false
    }
    val timer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
        }
        override fun onFinish() {
            timerlivedata.value=true
        }
    }
    fun startTimer()
    {
        timer.start()
    }
    fun cancelTimer()
    {
        timer.cancel()
    }
    fun returnTimerLiveData() : LiveData<Boolean>
    {
        return timerlivedata
    }

}