package dog.snow.androidrecruittest.DI

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dog.snow.androidrecruittest.BaseApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidSupportInjectionModule::class)
)
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(application : Application) : Builder
        fun build() : AppComponent
    }
}