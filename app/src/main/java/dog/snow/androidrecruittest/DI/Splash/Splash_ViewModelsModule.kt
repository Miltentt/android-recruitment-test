package dog.snow.androidrecruittest.DI.Splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dog.snow.androidrecruittest.DI.ViewModelKey
import dog.snow.androidrecruittest.DI.ViewModelsProviderFactory
import dog.snow.androidrecruittest.ui.ViewModels.Splash_ViewModel

@Module
abstract class Splash_ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(Splash_ViewModel::class)
    @SuppressWarnings("unused")
    abstract fun bindViewModel(splash_viewmodel: Splash_ViewModel) : ViewModel

    @SuppressWarnings("unused")
    @Binds
    abstract fun bind_ViewModel(ViewModelsProviderFactory: ViewModelsProviderFactory) : ViewModelProvider.Factory
}