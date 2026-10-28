package com.example.abbystowerapp.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.Drama
import com.composables.icons.lucide.Gamepad2
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Scroll
import com.composables.icons.lucide.Swords
import com.example.abbystowerapp.R
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.abbystowerapp.ui.theme.AbbysTowerAppTheme

private val Gold = Color(0xFFF2B705)
private val GoldSoft = Color(0xFFC9A04A)
private val CardBackground = Color(0xFF2B0A3A)
private val TitleFont = FontFamily.Serif   // luego Cinzel

private val ScreenBackground = Brush.verticalGradient(
    listOf(Color(0xFF2A0B3D), Color(0xFF0B0314))
)

// Degradado de Figma: 51136D (0%) -> 2B0A3A (91%)
private val FrameGradient = Brush.verticalGradient(
    colorStops = arrayOf(
        0.00f to Color(0xFF51136D),
        0.91f to Color(0xFF2B0A3A),
        1.00f to Color(0xFF2B0A3A)
    )
)

/**
 * Marco dibujado en 9 partes: las esquinas mantienen su tamaño (cornerSize)
 * y solo se estiran los lados, así no se deforma sin importar el tamaño.
 */
@Composable
private fun PixelFrame(
    modifier: Modifier = Modifier,
    cornerSize: Dp,
    contentPadding: PaddingValues,
    innerBrush: Brush? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val image = ImageBitmap.imageResource(R.drawable.marcoprincipal)

    Box(
        modifier = modifier
            .drawBehind {
                val iw = image.width
                val ih = image.height
                val c = (iw * 0.19f).toInt()      // esquina dentro del PNG original
                val d = cornerSize.roundToPx()    // esquina en pantalla
                val w = size.width.toInt()
                val h = size.height.toInt()

                // 1) Fondo/degradado solo dentro del hueco del marco
                if (innerBrush != null) {
                    val inset = d * 0.6f
                    drawRect(
                        brush = innerBrush,
                        topLeft = Offset(inset, inset),
                        size = Size(size.width - 2 * inset, size.height - 2 * inset)
                    )
                }

                // 2) Las 8 piezas del marco (el centro está vacío)
                val sx = intArrayOf(0, c, iw - c, iw)
                val sy = intArrayOf(0, c, ih - c, ih)
                val dx = intArrayOf(0, d, w - d, w)
                val dy = intArrayOf(0, d, h - d, h)

                for (i in 0..2) {
                    for (j in 0..2) {
                        if (i == 1 && j == 1) continue
                        drawImage(
                            image = image,
                            srcOffset = IntOffset(sx[i], sy[j]),
                            srcSize = IntSize(sx[i + 1] - sx[i], sy[j + 1] - sy[j]),
                            dstOffset = IntOffset(dx[i], dy[j]),
                            dstSize = IntSize(dx[i + 1] - dx[i], dy[j + 1] - dy[j]),
                            filterQuality = FilterQuality.None   // pixel art nítido
                        )
                    }
                }
            }
            .padding(contentPadding),
        content = content
    )
}

private val HomeBackgroundColor = Color(0xFF0B0719)

// Qué tanto se ve la imagen sobre el color (0f = solo color, 1f = solo imagen)
private const val BACKGROUND_IMAGE_ALPHA = 0.5f

@Composable
fun HomeScreen(
    onCharactersClick: () -> Unit,
    onLevelsClick: () -> Unit,
    onHistoryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HomeBackgroundColor)          // 1) color base
    ) {
        // 2) Imagen de fondo, de borde a borde
        Image(
            painter = painterResource(id = R.drawable.fondo_secundario),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = BACKGROUND_IMAGE_ALPHA,
            modifier = Modifier.fillMaxSize()
        )

        // 3) Contenido, respetando las barras del sistema
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            contentAlignment = Alignment.Center
        ) {
            PixelFrame(
                modifier = Modifier
                    .fillMaxWidth(0.94f)
                    .aspectRatio(2f / 3f),
                cornerSize = 56.dp,
                contentPadding = PaddingValues(horizontal = 46.dp, vertical = 42.dp),
                innerBrush = FrameGradient
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Bienvenido",
                        color = Color(0xFFD8CCE6),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    TitleCard()

                    Text(
                        text = "Explorar",
                        color = GoldSoft,
                        fontFamily = TitleFont,
                        fontSize = 16.sp
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        ExploreCard(
                            title = "Personajes",
                            subtitle = "Mira los Personajes",
                            icon = Lucide.Drama,
                            onClick = onCharactersClick,
                            modifier = Modifier.weight(1f)
                        )
                        ExploreCard(
                            title = "Niveles",
                            subtitle = "Mira los Niveles",
                            icon = Lucide.Swords,
                            onClick = onLevelsClick,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    ExploreCard(
                        title = "Historia",
                        subtitle = "Descubre el origen",
                        icon = Lucide.Scroll,
                        onClick = onHistoryClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
// Marco pequeño: el mismo marco, esquinas más chicas, alto según el contenido
@Composable
private fun TitleCard() {
    PixelFrame(
        modifier = Modifier.fillMaxWidth(),
        cornerSize = 26.dp,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "ABBYS TOWER",
                color = GoldSoft,
                fontFamily = TitleFont,
                fontSize = 20.sp,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Explora la torre del abismo e intenta llegar a lo más alto",
                color = Color.White,
                fontSize = 11.sp,
                lineHeight = 14.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Badge(text = "2.5 D", icon = Lucide.Gamepad2)
                Badge(text = "Dungeons")
            }
        }
    }
}

@Composable
private fun Badge(text: String, icon: ImageVector? = null) {
    val shape = RoundedCornerShape(8.dp)
    Row(
        modifier = Modifier
            .background(Color(0xFF1B0626), shape)
            .border(1.5.dp, Gold, shape)
            .padding(horizontal = 8.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
        Text(text = text, color = Color.White, fontSize = 11.sp)
    }
}

@Composable
private fun ExploreCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(4.dp)
    Column(
        modifier = modifier
            .background(CardBackground, shape)
            .border(2.dp, Gold, shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 6.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Gold,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = title.uppercase(),
            color = GoldSoft,
            fontFamily = TitleFont,
            fontSize = 13.sp
        )
        Text(text = subtitle, color = Color.White, fontSize = 10.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AbbysTowerAppTheme {
        HomeScreen(onCharactersClick = {}, onLevelsClick = {}, onHistoryClick = {})
    }
}