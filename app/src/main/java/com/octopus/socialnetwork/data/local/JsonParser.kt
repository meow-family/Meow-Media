package com.octopus.socialnetwork.data.local

import com.google.gson.Gson
import javax.inject.Inject

class JsonParser @Inject constructor(val gson: Gson) {

    inline fun <reified T> parseFromJson(json: String?, classType: Class<T>): T {
        return gson.fromJson(json,classType)
    }
}