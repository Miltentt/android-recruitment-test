package dog.snow.androidrecruittest

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import dog.snow.androidrecruittest.DI.ViewModelsProviderFactory
import dog.snow.androidrecruittest.Util.Timer_Class
import dog.snow.androidrecruittest.repository.Main_Repository
import dog.snow.androidrecruittest.ui.ListFragment
import dog.snow.androidrecruittest.ui.ViewModels.Main_ViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.layout_banner.*
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(R.layout.main_activity) {
    var disposables = CompositeDisposable()
    @Inject
    lateinit var viewModelsProviderFactory: ViewModelsProviderFactory

    lateinit var mainVewmodel: Main_ViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        mainVewmodel = ViewModelProvider(this, viewModelsProviderFactory).get(Main_ViewModel::class.java)
        mainVewmodel.returnTimerLiveData().observe({ lifecycle },
            {
                if (it) this.getSharedPreferences("default", Context.MODE_PRIVATE).edit().putBoolean("outdated", true).apply()
            })
    setupConnectivityManager()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setupConnectivityManager()
    {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(connectivityManager?.activeNetworkInfo?.isAvailable==null)
        {
            offlineMode(false)
        }
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                offlineMode(true)

            }
            override fun onLost(network: Network?) {
                offlineMode(false)
            }

        })
    }

    fun offlineMode(boolean: Boolean)
    {
        when(boolean) {
            false -> {
                mainVewmodel.timerStart();
              disposables.add(  Completable.fromAction({ banner.visibility = View.VISIBLE })
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe())
            }
            true ->
            {
                mainVewmodel.timerCancel()
              disposables.add(  Completable.fromAction({banner.visibility =View.GONE})
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe())
            }
        }
    }


    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}




