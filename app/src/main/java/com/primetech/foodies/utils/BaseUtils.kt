package com.primetech.foodies.utils

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.primetech.foodies.utils.Constants.Companion.DATA_FILE
import java.io.IOException
import java.lang.reflect.Type


class BaseUtils {

    companion object{

        fun getData(context: Context): ArrayList<Response> {
            val fileData = getJsonDataFromAsset(context)
            val gson = GsonBuilder().create()
            val listType: Type = object : TypeToken<ArrayList<Response?>?>() {}.type
            return gson.fromJson(fileData, listType)
        }

        private fun getJsonDataFromAsset(context: Context): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(DATA_FILE).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

    }
}