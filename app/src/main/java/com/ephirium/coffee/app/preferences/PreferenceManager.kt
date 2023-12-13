package com.ephirium.coffee.app.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(sharedPreferenceKey, Context.MODE_PRIVATE)

    var compliment: String?
        get() = sharedPreferences.getString(complimentKey, null)
        set(value) = sharedPreferences.edit().putString(complimentKey, value).apply()

    companion object {
        const val sharedPreferenceKey = "pref"
        const val complimentKey = "compliment"
    }
}