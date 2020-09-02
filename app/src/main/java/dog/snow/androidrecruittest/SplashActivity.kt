package dog.snow.androidrecruittest

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.DaggerAppCompatActivity
import dog.snow.androidrecruittest.DI.ViewModelsProviderFactory
import dog.snow.androidrecruittest.ui.ViewModels.Splash_ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.layout_progressbar.*
import kotlinx.android.synthetic.main.splash_activity.*
import java.util.*
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity(R.layout.splash_activity) {
    @Inject
     lateinit var viewModelsProviderFactory: ViewModelsProviderFactory
    private lateinit var splashViewmodel: Splash_ViewModel
    private var disposables = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewmodel = ViewModelProvider(this,viewModelsProviderFactory).get(Splash_ViewModel::class.java)
        splashViewmodel.calculateTime(this.getSharedPreferences("default", Context.MODE_PRIVATE).getLong("date",-1)).observe({lifecycle},
            {if(it) splashViewmodel.deleteDB()})
        makeAPICall()
        iv_logo_sd_symbol.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bounce))
        iv_logo_sd_text.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bounce))

    }



    private fun showError(errorMessage: String?) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message, errorMessage))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> makeAPICall();  }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }

    private fun makeAPICall()
    {
       disposables.add(splashViewmodel.getEverything()
            .subscribe(
                {splashViewmodel.populateList(it)},
                {checkIfDatabaseEmpty()},
                {insertDatabase()}))


    }
    private fun insertDatabase()
    {
       disposables.add(splashViewmodel.insertIntoDatabase().subscribe(
            {startActivity()
             this.getSharedPreferences("default", Context.MODE_PRIVATE).edit().putLong("date", Calendar.getInstance().timeInMillis).apply()},
            {showError("Database Error")}))
    }
    private fun checkIfDatabaseEmpty()
    {
       disposables.add( splashViewmodel.checkIfDatabaseEmpty()
            .subscribe{
                if(it.isEmpty()) showError("No local data, Please turn on the internet connection")
                else startActivity()})

    }

   private fun startActivity()
    {
        val intent = Intent(this,MainActivity::class.java)
        finish()
        startActivity(intent)

    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}