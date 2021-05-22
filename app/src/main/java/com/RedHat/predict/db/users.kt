package com.RedHat.predict.db

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class users(
    var id: String = "",
    var nama: String = "",
    var email :String = "",
    var password: String = "",

) : Parcelable