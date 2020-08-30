package dog.snow.androidrecruittest.DI.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dog.snow.androidrecruittest.DI.ViewModelKey
import dog.snow.androidrecruittest.DI.ViewModelsProviderFactory
import dog.snow.androidrecruittest.ui.ViewModels.Main_ViewModel
import dog.snow.androidrecruittest.ui.ViewModels.Splash_ViewModel
@Module
abstract class Main_ViewModelsModule
{
    @Binds
@IntoMap
@ViewModelKey(Main_ViewModel::class)
@SuppressWarnings("unused")
abstract fun bindViewModel(mainViewmodel: Main_ViewModel) : ViewModel

@SuppressWarnings("unused")
@Binds
abstract fun bind_ViewModel(ViewModelsProviderFactory: ViewModelsProviderFactory) : ViewModelProvider.Factory
}