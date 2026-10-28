package com.example.abbystowerapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.example.abbystowerapp.R

@Composable
fun CardCharacters(
    name: String,
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val frame = ImageBitmap.imageResource(R.drawable.marco_card)
    val character = ImageBitmap.imageResource(imageRes)

    Box(
        modifier = modifier
            .aspectRatio(690f / 580f)          // proporción del PNG del marco
            .clickable(onClick = onClick)
    ) {
        // 1) Fondo oscuro dentro del marco
        Box(
            modifier = Modifier
                .matchParentSize()
                .drawBehind {
                    val left = size.width * 0.055f
                    val right = size.width * 0.945f
                    val top = size.height * 0.13f
                    val bottom = size.height * 0.90f

                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF3A1E4D), Color(0xFF160820)),
                            startY = top,
                            endY = bottom
                        ),
                        topLeft = Offset(left, top),
                        size = Size(right - left, bottom - top)
                    )
                }
        )

        // 2) Personaje (pixel art nítido)
        Image(
            bitmap = character,
            contentDescription = name,
            contentScale = ContentScale.Fit,
            filterQuality = FilterQuality.None,
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        )

        // 3) Sombra suave detrás del nombre para que se lea
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 9.dp, vertical = 9.dp)
                .height(34.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color(0xCC000000))
                    )
                )
        )

        // 4) Nombre
        Text(
            text = name,
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 14.dp)
        )

        // 5) Marco encima de todo (incluye la insignia de la esquina)
        Image(
            bitmap = frame,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            filterQuality = FilterQuality.None,
            modifier = Modifier.matchParentSize()
        )
    }
}