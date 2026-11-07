package com.example.abbystowerapp.ui.Screens

/**
 * Project: AbbysTowerApp
 * From: com.example.abbystowerapp.ui.Screens
 * Created by: karla
 * On: 05/11/2026
 * All rights reserved: 2026
 */


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.abbystowerapp.R
import com.example.abbystowerapp.ui.components.BarraSuperior
import com.example.abbystowerapp.ui.theme.AbbysTowerAppTheme

private val Gold = Color(0xFFF2B705)
private val GoldSoft = Color(0xFFC9A04A)
private val BackgroundColor = Color(0xFF0B0719)
private val CardBackground = Color(0xFF2B0A3A)
private const val BACKGROUND_IMAGE_ALPHA = 0.5f

private data class Nivel(
    val titulo: String,
    val descripcion: String,
    @DrawableRes val imagen: Int?
)

private val niveles = listOf(
    Nivel(
        "Piso 1",
        "Te encontraras con enemigos basicos para ir comenzando tu aventura",
        null //  recordar CAMBIAR por R.drawable.nivel_piso1
    ),
    Nivel(
        "Piso 2",
        "Nuevos enemigos por descubrir y mejor equipamento",
        null // recordar CAMBIAR por R.drawable.nivel_piso2
    ),
    Nivel(
        "Piso 3",
        "Aqui pondras a prueba tus habilidades aprendidas en los pisos anteriores",
        null //  recordar CAMBIAR por R.drawable.nivel_piso3
    ),
            Nivel(
            "Piso 4",
    "Aqui pondras a prueba tus habilidades aprendidas en los pisos anteriores",
    null //  recordar CAMBIAR por R.drawable.nivel_piso3
)
)

@Composable
fun LevelsScreen(onBack: () -> Unit) {
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
            BarraSuperior(titulo = "Niveles", onBack = onBack)

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(niveles) { nivel -> NivelCard(nivel) }
            }
        }
    }
}

@Composable
private fun NivelCard(nivel: Nivel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, RectangleShape, ambientColor = Gold, spotColor = Gold)
            .background(CardBackground)
            .border(3.dp, Gold)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2.6f)
                .border(2.dp, GoldSoft)
                .background(
                    Brush.verticalGradient(listOf(Color(0xFF3A1E4D), Color(0xFF160820)))
                )
        ) {
            if (nivel.imagen != null) {
                Image(
                    bitmap = ImageBitmap.imageResource(nivel.imagen),
                    contentDescription = nivel.titulo,
                    contentScale = ContentScale.Crop,
                    filterQuality = FilterQuality.None,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Text(
            text = nivel.titulo,
            color = Gold,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
        Text(
            text = nivel.descripcion,
            color = Gold,
            fontSize = 12.sp,
            lineHeight = 17.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LevelsScreenPreview() {
    AbbysTowerAppTheme { LevelsScreen(onBack = {}) }
}