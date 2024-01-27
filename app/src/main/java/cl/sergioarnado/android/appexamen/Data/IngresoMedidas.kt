package cl.sergioarnado.android.appexamen.Data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class IngresoMedidas (
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    val Gasto: Medidas,
    val valor: Double,
    val fecha: String
)