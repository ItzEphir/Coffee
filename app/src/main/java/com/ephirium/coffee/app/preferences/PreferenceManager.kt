package com.ephirium.coffee.app.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("pref", Context.MODE_PRIVATE)

    var compliment: String?
        get() {
            return sharedPreferences.getString("compliment", null)
        }
        set(value) {
            sharedPreferences.edit().putString("compliment", value).apply()
        }

}