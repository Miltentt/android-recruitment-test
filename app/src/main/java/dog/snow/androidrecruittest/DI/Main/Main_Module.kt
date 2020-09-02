package dog.snow.androidrecruittest.DI.Main

import dagger.Module
import dagger.Provides
import dog.snow.androidrecruittest.Util.Timer_Class


@Module
class Main_Module {

    @Provides
    fun provideTimer() : Timer_Class
    {
        return Timer_Class()
    }
}