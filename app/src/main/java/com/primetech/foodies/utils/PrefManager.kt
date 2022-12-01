package com.instances.resturent_finder.utils

import android.content.Context
import android.content.SharedPreferences
import com.primetech.foodies.utils.Constants
import com.primetech.foodies.utils.Constants.Companion.TOKEN

class PrefManager( var context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(
        Constants.PREF_NAME,
        Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()


    var accessToken:String
        get() = pref.getString(TOKEN, "")!!
        set(value) {
            editor.putString(TOKEN,value)
            editor.apply()
            editor.commit()
        }

    fun logout(){
        editor.clear()
        editor.apply()
        editor.commit()
    }
}