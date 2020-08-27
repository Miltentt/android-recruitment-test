package dog.snow.androidrecruittest


import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dog.snow.androidrecruittest.DI.DaggerAppComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build();
    }
}