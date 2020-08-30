package dog.snow.androidrecruittest.DI

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dog.snow.androidrecruittest.ui.ListFragment

@Module
abstract class FragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract fun listfragment() : ListFragment

}