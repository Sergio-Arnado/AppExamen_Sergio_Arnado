package cl.sergioarnado.android.appexamen.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IngresoMedidadDao {
    @Insert
    suspend fun insertar(ingresoMedidas: IngresoMedidas): Long

    @Update
    suspend fun actualizar(ingresoMedidas: IngresoMedidas)

    @Delete
    suspend fun eliminar(ingresoMedidas: IngresoMedidas)

    @Query("SELECT * FROM ingresomedidas")
    fun obtenerTodos(): Flow<List<IngresoMedidas>>


    @Query("SELECT * FROM ingresomedidas WHERE Gasto = :tipoGasto")
    fun buscarPorTipo(tipoGasto: Medidas): Flow<List<IngresoMedidas>>
}