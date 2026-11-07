package com.example.abbystowerapp.ui.components

/**
 * Project: AbbysTowerApp
 * From: com.example.abbystowerapp.ui.components
 * Created by: karla
 * On: 05/11/2026
 * All rights reserved: 2026
 */



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.abbystowerapp.Model.Filtro
import com.example.abbystowerapp.Model.Orden
import kotlin.math.roundToInt

private val Gold = Color(0xFFF2B705)
private val GoldSoft = Color(0xFFC9A04A)
private val Fondo = Color(0xFF2B0A3A)

@Composable
fun FiltroDialog(
    filtroActual: Filtro,
    onAplicar: (Filtro) -> Unit,
    onCerrar: () -> Unit
) {
    var borrador by remember { mutableStateOf(filtroActual) }

    Dialog(
        onDismissRequest = onCerrar,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .background(Fondo)
                .border(3.dp, Gold)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Filtrar personajes",
                color = Gold,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp
            )

            Text("Ordenar por", color = GoldSoft, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Orden.entries.forEach { opcion ->
                OpcionOrden(
                    texto = opcion.etiqueta,
                    seleccionada = borrador.orden == opcion,
                    onClick = { borrador = borrador.copy(orden = opcion) }
                )
            }

            Spacer(Modifier.height(4.dp))
            Text("Valores mínimos", color = GoldSoft, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            SliderStat("Vida", borrador.minVida) { borrador = borrador.copy(minVida = it) }
            SliderStat("Ataque", borrador.minAtaque) { borrador = borrador.copy(minAtaque = it) }
            SliderStat("Defensa", borrador.minDefensa) { borrador = borrador.copy(minDefensa = it) }

            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BotonDialog("Limpiar", Modifier.weight(1f)) { borrador = Filtro() }
                BotonDialog("Aplicar", Modifier.weight(1f)) { onAplicar(borrador) }
            }
        }
    }
}

@Composable
private fun OpcionOrden(texto: String, seleccionada: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (seleccionada) {
            Box(Modifier.size(12.dp).rotate(45f).background(Gold))
        } else {
            Box(Modifier.size(9.dp).border(1.5.dp, GoldSoft, CircleShape))
        }
        Text(
            text = texto,
            color = if (seleccionada) Gold else Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun SliderStat(label: String, valor: Int, onCambio: (Int) -> Unit) {
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(label, color = Color.White, fontSize = 13.sp, modifier = Modifier.weight(1f))
            Text(valor.toString(), color = Gold, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
        Slider(
            value = valor.toFloat(),
            onValueChange = { onCambio(it.roundToInt()) },
            valueRange = 0f..60f,
            colors = SliderDefaults.colors(
                thumbColor = Gold,
                activeTrackColor = Gold,
                inactiveTrackColor = Color(0x55C9A04A)
            )
        )
    }
}

@Composable
private fun BotonDialog(texto: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .height(40.dp)
            .background(Color(0x33000000))
            .border(2.dp, Gold)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(texto, color = GoldSoft, fontWeight = FontWeight.Bold, fontSize = 13.sp)
    }
}