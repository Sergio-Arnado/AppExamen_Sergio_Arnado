package cl.sergioarnado.android.appexamen.Data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class MetricasViewModel : ViewModel() {

    var numeroMedicion by mutableStateOf("")

    var medidorValue by mutableStateOf("")

    var selectedMedicionTipo by mutableStateOf(Medidor.Agua)

    // acá almacenaos todas las mediciones
    var mediciones by mutableStateOf(listOf<Medidas>())

    var fechaMedidor by mutableStateOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))

    @RequiresApi(Build.VERSION_CODES.O)
    fun ingresarMedicion() {

        val valor = medidorValue.toDoubleOrNull()
        if (valor != null) {

            val nuevaMedicion = Medidas(
                tipo = selectedMedicionTipo,
                valor = valor,
                fecha = LocalDate.now() )

            mediciones = mediciones + nuevaMedicion
        } else {
            // Manejar error de conversión de valor aquí
            println("Error: Valor de medición no es un número válido.")
        }
    }

}