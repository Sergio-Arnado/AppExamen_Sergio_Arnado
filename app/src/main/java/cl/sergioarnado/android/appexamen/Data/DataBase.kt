package cl.sergioarnado.android.appexamen.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Medidor::class], version = 1)
@TypeConverters(LocalDateConvert::class)
abstract class DataBase : RoomDatabase() {
    abstract fun IngresoMedidadDao(): IngresoMedidadDao
}