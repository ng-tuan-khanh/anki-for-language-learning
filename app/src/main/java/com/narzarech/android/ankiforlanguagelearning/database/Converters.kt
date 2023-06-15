package com.narzarech.android.ankiforlanguagelearning.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromIntListToJson(list: List<Int>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonToIntList(list: String): List<Int>? {
        val listType = object : TypeToken<ArrayList<Int>>(){}.type
        return Gson().fromJson<List<Int>>(list, listType)
    }

    @TypeConverter
    fun fromStringListToJson(list: List<String>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonToStringList(list: String): List<String>? {
        val listType = object : TypeToken<ArrayList<String>>(){}.type
        return Gson().fromJson<List<String>>(list, listType)
    }
}