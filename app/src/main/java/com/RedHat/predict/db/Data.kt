package com.RedHat.predict.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Data(
    var bencana: String = "",
    var locationname: String = "",
    var locationcity: String = "",
    var locationlatlong: String = "",
    var locationconfidience: String = "",
    var waktu: String = "",
    var terjadibencana: String = "",
    var reason:  String = ""
): Parcelable