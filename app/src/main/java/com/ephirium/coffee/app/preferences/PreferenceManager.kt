package com.ephirium.coffee.app.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)

    var compliment: String?
        get() = sharedPreferences.getString(COMPLIMENT_KEY, null)
        set(value) = sharedPreferences.edit().putString(COMPLIMENT_KEY, value).apply()

    companion object {
        const val SHARED_PREFERENCE_KEY = "pref"
        const val COMPLIMENT_KEY = "compliment"
    }
}