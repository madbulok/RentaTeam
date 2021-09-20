package com.uzlov.rentateam.repo.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uzlov.rentateam.repo.models.Data
import com.uzlov.rentateam.repo.models.Support


class Converters {
    @TypeConverter
    fun fromSupport(support: Support?) = Gson().toJson(support)


    @TypeConverter
    fun toSupport(support: String) : Support = Gson().fromJson(support, Support::class.java)


    @TypeConverter
    fun fromData(data:  List<Data>) = Gson().toJson(data)


    @TypeConverter
    fun toData(data: String) :  List<Data> {
        val type = object : TypeToken<List<Data>>(){}.type
        return Gson().fromJson(data, type)
    }

}