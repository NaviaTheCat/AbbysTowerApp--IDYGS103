package com.example.abbystowerapp.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.abbystowerapp.R
import com.example.abbystowerapp.ui.theme.AbbysTowerAppTheme
import kotlinx.coroutines.delay

// Qué tanto se ve cada imagen (0f = invisible, 1f = totalmente visible)
private const val PRINCIPAL_ALPHA = 0.8f
private const val SECUNDARIO_ALPHA = 0.6f

// Degradado de Figma: color + opacidad en cada punto (ARGB)
private val GradientBrush = Brush.verticalGradient(
    colorStops = arrayOf(
        0.0f to Color(0x00101746),
        0.5f to Color(0xE0343B68),
        1.0f to Color(0x000A0F2F)
    )
)

@Composable
fun LoadScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF05091F))   // 1) color base
    ) {
        // 2) Degradado de Figma
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GradientBrush)
        )

        // 3) Cielo con estrellas
        Image(
            painter = painterResource(id = R.drawable.fondo_principal),
            contentDescription = null,
            contentScale = ContentScale.Crop,   // llena toda la pantalla sin deformarse
            alpha = PRINCIPAL_ALPHA,
            modifier = Modifier.fillMaxSize()
        )

        // 4) Humo morado encima
        Image(
            painter = painterResource(id = R.drawable.fondo_secundario),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = SECUNDARIO_ALPHA,
            modifier = Modifier.fillMaxSize()
        )

        // 5) Contenido: logo y debajo el marco con texto
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo de Abbys Tower",
                modifier = Modifier.size(500.dp)
            )

            Box(
                modifier = Modifier
                    .width(180.dp)
                    .aspectRatio(2160f / 740f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.inicio),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
                Text(
                    text = "cargando . . .",
                    color = Color(0xFFE8A317),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadScreenPreview() {
    AbbysTowerAppTheme {
        LoadScreen(onFinished = {})
    }
}