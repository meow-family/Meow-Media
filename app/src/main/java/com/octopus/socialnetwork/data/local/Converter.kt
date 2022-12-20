package com.octopus.socialnetwork.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.octopus.socialnetwork.data.remote.response.dto.Avatar

class Converter {
    @TypeConverter
    fun fromAvatar(value: Avatar?): String? {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAvatar(string: String?): Avatar? {
        val gson = Gson()
        return gson.fromJson(string, Avatar::class.java)
    }
}