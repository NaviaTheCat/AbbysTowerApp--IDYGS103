package com.example.abbystowerapp.ui.Screens

/**
 * Project: AbbysTowerApp
 * From: com.example.abbystowerapp.ui.Screens
 * Created by: karla
 * On: 05/11/2026
 * All rights reserved: 2026
 */


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.abbystowerapp.Data.PersonajesRepository
import com.example.abbystowerapp.Model.Personaje
import com.example.abbystowerapp.R
import com.example.abbystowerapp.ui.components.BarraSuperior
import com.example.abbystowerapp.ui.components.PixelFrame
import com.example.abbystowerapp.ui.theme.AbbysTowerAppTheme
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.RectangleShape

private val Gold = Color(0xFFF2B705)
private val GoldSoft = Color(0xFFC9A04A)
private val BackgroundColor = Color(0xFF0B0719)
private val CardBackground = Color(0xFF2B0A3A)

private val DoradoBrillo = Color(0xFFE8B83A)

private val NumeroBrush = Brush.verticalGradient(
    listOf(Color(0xFFE9C766), Color(0xFFB88A2E))
)
private const val BACKGROUND_IMAGE_ALPHA = 0.5f

private val DescripcionGradient = Brush.verticalGradient(
    listOf(Color(0xFF51136D), Color(0xFF2B0A3A))
)

@Composable
fun DetailScreen(
    personajeId: Int,
    onBack: () -> Unit
) {
    val personaje = PersonajesRepository.porId(personajeId)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo_secundario),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = BACKGROUND_IMAGE_ALPHA,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            BarraSuperior(titulo = "Detalle personaje", onBack = onBack)

            if (personaje == null) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Personaje no encontrado", color = GoldSoft)
                }
            } else {
                DetailContent(personaje)
            }
        }
    }
}

@Composable
private fun DetailContent(personaje: Personaje) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Aureola(personaje)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatBox(label = "VIDA", value = personaje.vida)
            StatCircle(label = "ATAQUE", value = personaje.ataque)
            StatBox(label = "DEFENSA", value = personaje.defensa)
        }

        Text(
            text = "Descripción del personaje",
            color = GoldSoft,
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        PixelFrame(
            modifier = Modifier.fillMaxWidth(),
            cornerSize = 26.dp,
            contentPadding = PaddingValues(horizontal = 30.dp, vertical = 28.dp),
            innerBrush = DescripcionGradient
        ) {
            Text(
                text = personaje.descripcion,
                color = GoldSoft,
                fontFamily = FontFamily.Serif,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                textAlign = TextAlign.Justify
            )
        }

        Box(Modifier.height(12.dp))
    }
}

@Composable
private fun Aureola(personaje: Personaje) {
    val aureola = ImageBitmap.imageResource(R.drawable.marco_personaje)
    val imagen = ImageBitmap.imageResource(personaje.imagen)

    Box(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = imagen,
            contentDescription = personaje.nombre,
            contentScale = ContentScale.Fit,
            filterQuality = FilterQuality.None,
            modifier = Modifier.fillMaxSize(0.52f)
        )
        Image(
            bitmap = aureola,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            filterQuality = FilterQuality.None,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun StatBox(label: String, value: Int) {
    val shape = RectangleShape
    Column(
        modifier = Modifier
            .width(96.dp)
            .height(72.dp)
            .shadow(10.dp, shape, ambientColor = DoradoBrillo, spotColor = DoradoBrillo)
            .background(CardBackground)
            .border(5.dp, DoradoBrillo),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StatLabel(label)
        StatNumber(value, 28)
    }
}

@Composable
private fun StatCircle(label: String, value: Int) {
    Column(
        modifier = Modifier
            .size(100.dp)
            .shadow(12.dp, CircleShape, ambientColor = DoradoBrillo, spotColor = DoradoBrillo)
            .background(CardBackground, CircleShape)
            .border(5.dp, DoradoBrillo, CircleShape),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StatLabel(label)
        StatNumber(value, 32)
    }
}

@Composable
private fun StatLabel(label: String) {
    Text(
        text = label,
        style = TextStyle(
            brush = NumeroBrush,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            shadow = Shadow(Color(0xFF000000), Offset(2f, 3f), 4f)
        )
    )
}

@Composable
private fun StatNumber(value: Int, size: Int) {
    Text(
        text = value.toString(),
        style = TextStyle(
            brush = NumeroBrush,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = size.sp,
            shadow = Shadow(Color(0xFF000000), Offset(3f, 5f), 6f)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    AbbysTowerAppTheme {
        DetailScreen(personajeId = 0, onBack = {})
    }
}