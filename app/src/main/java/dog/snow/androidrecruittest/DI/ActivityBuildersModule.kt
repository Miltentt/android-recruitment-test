package dog.snow.androidrecruittest.DI

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dog.snow.androidrecruittest.DI.Splash.Splash_Module
import dog.snow.androidrecruittest.DI.Splash.Splash_ViewModelsModule
import dog.snow.androidrecruittest.MainActivity
import dog.snow.androidrecruittest.SplashActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [Splash_Module::class,Splash_ViewModelsModule::class])
    abstract fun ContributeAuthActivity() : SplashActivity


}