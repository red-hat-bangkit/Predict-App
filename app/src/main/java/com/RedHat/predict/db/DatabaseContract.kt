package com.RedHat.predict.db

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.RedHat.Predict"
    const val SCHEME = "content"

    internal class DisasterColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "disaster"
            const val _ID = "_id"
            const val  NAME = "name"
            const val   EMAIL = "email"
            const val PASSWORD = "date"

            // untuk membuat URI content://com.dicoding.picodiploma.mynotesapp/note
            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }

}