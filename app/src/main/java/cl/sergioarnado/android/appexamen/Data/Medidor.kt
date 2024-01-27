package cl.sergioarnado.android.appexamen.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
enum class Medidor {
    Agua,
    Luz,
    Gas
}

open class Medidas(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: Medidor, val valor: Double, val fecha: LocalDate)

