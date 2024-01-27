package cl.sergioarnado.android.appexamen

import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.provider.Settings.Global.getString
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.lifecycle.viewmodel.compose.viewModel
import cl.sergioarnado.android.appexamen.Data.Medidas
import cl.sergioarnado.android.appexamen.Data.Medidor

import cl.sergioarnado.android.appexamen.Data.MetricasViewModel
import cl.sergioarnado.android.appexamen.ui.theme.AppExamenTheme
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController, viewModel: MetricasViewModel) {
    val contexto = LocalContext.current


    Column {
        Button(onClick = { navController.navigate("details") }) {
            Text("Ir a detalles")
        }
        FormularioMedicionesScreen(viewModel)
    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(navController: NavController, viewModel: MetricasViewModel) {
    val contexto = LocalContext.current

    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.gas),
                contentDescription = "Logo"
            )
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ){
        Text(text = contexto.getString(R.string.app_name), style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(18.dp)
        )

        OutlinedTextField(value = viewModel.numeroMedicion,
            onValueChange = { viewModel.numeroMedicion = it },
            label = { Text("Medidor")},
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = viewModel.fechaMedidor,
            onValueChange = { viewModel.fechaMedidor = it},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Fecha")},
            readOnly = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Botones de opción para el tipo de medidor
        Column {
            Text(text = "Medidor de: ")
            Medidor.values().forEach { tipo ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (tipo == viewModel.selectedMedicionTipo),
                            onClick = { viewModel.selectedMedicionTipo = tipo }
                        )
                        .padding(vertical = 8.dp)
                    ){
                    RadioButton(
                        selected = (tipo == viewModel.selectedMedicionTipo),
                        onClick = { viewModel.selectedMedicionTipo = tipo }
                    )
                    Text(text = tipo.name, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
             onClick = { viewModel.ingresarMedicion()
            navController.navigate("Inicio")
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(contexto.getString(R.string.btn_text_Registrar_Medición))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: MetricasViewModel = viewModel()
    NavHost(navController, startDestination = "Inicio") {
        composable("Inicio") { HomeScreen(navController, viewModel) }
        composable("details") { DetailScreen(navController, viewModel) }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormularioMedicionesScreen(viewModel: MetricasViewModel) {
    LazyColumn {
        items(viewModel.mediciones) { medida ->
            // Aquí defines cómo se muestra cada medición en la lista
            Row(modifier = Modifier.padding(8.dp)) {
                Text(text = medida.tipo.name, modifier = Modifier.weight(1f))
                Text(text = "${medida.valor}", modifier = Modifier.weight(1f))
                Text(text = medida.fecha.format(DateTimeFormatter.ISO_LOCAL_DATE),modifier = Modifier.weight(1f)
                )
            }
        }
    }

}








