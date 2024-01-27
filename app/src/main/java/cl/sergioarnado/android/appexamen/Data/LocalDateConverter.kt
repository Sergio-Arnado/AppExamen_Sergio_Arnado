package cl.sergioarnado.android.appexamen.Data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConvert {
    @TypeConverter
    fun fromLocalDate(value: LocalDate): String {
        return value.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDate(value: String): LocalDate {
        return LocalDate.parse(value)
    }
}