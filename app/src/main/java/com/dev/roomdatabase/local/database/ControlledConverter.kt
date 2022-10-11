package com.dev.roomdatabase.local.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class ControlledConverter(private val gson: Gson) {

    @TypeConverter
    fun fromJsonToLongList(json: String): List<Long>{
        return try{
            gson.fromJson(
                json,
                object : TypeToken<List<Long>>() {}.type
            )
        } catch (exp: JsonSyntaxException){
            emptyList()
        }
    }


    @TypeConverter
    fun longListToJsonString(longs: List<Long>): String{
        return try {
            gson.toJson(
                longs,
                object : TypeToken<List<Long>>() {}.type
            )
        }catch (exp: JsonSyntaxException){
            "[]"
        }
    }

    @TypeConverter
    fun fromJsonToIntList(json: String): List<Int>{
        return try{
            gson.fromJson(
                json,
                object : TypeToken<List<Int>>() {}.type
            )
        } catch (exp: JsonSyntaxException){
            emptyList()
        }
    }


    @TypeConverter
    fun intListToJsonString(longs: List<Int>): String{
        return try {
            gson.toJson(
                longs,
                object : TypeToken<List<Int>>() {}.type
            )
        }catch (exp: JsonSyntaxException){
            "[]"
        }
    }
}