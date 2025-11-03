package com.example.data.local.database

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeConverter {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromString(value: String?): LocalDateTime? = value?.let { LocalDateTime.parse(it, formatter) }

    @TypeConverter
    fun localDateTimeToString(dateTime: LocalDateTime?): String? = dateTime?.format(formatter)
}
