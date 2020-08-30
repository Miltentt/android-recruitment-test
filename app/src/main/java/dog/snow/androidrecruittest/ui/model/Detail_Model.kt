package dog.snow.androidrecruittest.ui.model

import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser

data class Detail_Model (
    val rawPhoto: RawPhoto,
val rawAlbum: RawAlbum,
val rawUser: RawUser)



