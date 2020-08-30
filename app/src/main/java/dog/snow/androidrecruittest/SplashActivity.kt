package dog.snow.androidrecruittest

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.DaggerAppCompatActivity
import dog.snow.androidrecruittest.DI.ViewModelsProviderFactory
import dog.snow.androidrecruittest.ui.ViewModels.Splash_ViewModel
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity(R.layout.splash_activity) {
    @Inject
     lateinit var viewModelsProviderFactory: ViewModelsProviderFactory
    private lateinit var splashViewmodel: Splash_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewmodel = ViewModelProviders.of(this,viewModelsProviderFactory)[Splash_ViewModel::class.java]
        makeAPICall()
    }

    private fun showError(errorMessage: String?) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message, errorMessage))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> makeAPICall() }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }

    private fun makeAPICall()
    {
        splashViewmodel.getEverything()
            .subscribe(
                {detail -> splashViewmodel.addDetails(detail)},
                {showError("cant download the data")},
                {insertDatabase()})


    }
    private fun insertDatabase()
    {
        splashViewmodel.insertIntoDatabase().subscribe(
            {startActivity(Intent(this,MainActivity::class.java))},
            {showError("Database Error")})
    }

}