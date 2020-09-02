package dog.snow.androidrecruittest

import android.content.Context
import android.content.Intent
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
import kotlinx.android.synthetic.main.layout_progressbar.*
import kotlinx.android.synthetic.main.splash_activity.*
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity(R.layout.splash_activity) {
    @Inject
     lateinit var viewModelsProviderFactory: ViewModelsProviderFactory
    private lateinit var splashViewmodel: Splash_ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewmodel = ViewModelProvider(this,viewModelsProviderFactory).get(Splash_ViewModel::class.java)
        if(this.getSharedPreferences("default", Context.MODE_PRIVATE).getBoolean("outdated",false))
        {
            splashViewmodel.deleteDB()
            this.getSharedPreferences("default",Context.MODE_PRIVATE).edit().remove("outdated").apply();
        }
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
        splashViewmodel.getEverything()
            .subscribe(
                {splashViewmodel.populateList(it)},
                {checkIfDatabaseEmpty()},
                {insertDatabase();})


    }
    private fun insertDatabase()
    {
        splashViewmodel.insertIntoDatabase().subscribe(
            {startActivity(Intent(this,MainActivity::class.java))},
            {showError("Database Error")})
    }
    private fun checkIfDatabaseEmpty()
    {
        splashViewmodel.checkIfDatabaseEmpty()
            .subscribe({if(it.isEmpty()) showError("No local data, Please turn on the internet connection") else startActivity(Intent(this,MainActivity::class.java))})

    }





}