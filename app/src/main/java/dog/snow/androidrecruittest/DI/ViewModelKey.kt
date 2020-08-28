package dog.snow.androidrecruittest.DI

import androidx.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import javax.xml.transform.OutputKeys.METHOD
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
 annotation class ViewModelKey (val value : KClass<out ViewModel>)
