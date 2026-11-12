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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.abbystowerapp.R
import com.example.abbystowerapp.ui.components.BarraSuperior
import com.example.abbystowerapp.ui.theme.AbbysTowerAppTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import kotlin.math.PI
import kotlin.math.floor
import kotlin.math.sin
import androidx.compose.foundation.layout.offset

private val Gold = Color(0xFFF2B705)
private val GoldSoft = Color(0xFFC9A04A)
private val BackgroundColor = Color(0xFF0B0719)
private val TextoLibro = Color(0xFF2A1A0A)
private const val BACKGROUND_IMAGE_ALPHA = 0.5f


private val TituloLibro = Color(0xFF7A1F12)

// Cada elemento es una doble página del libro: el título del acto va arriba
// de la hoja izquierda. Mantén cada texto en ~75 caracteres o menos para que
// quepa en la hoja en teléfonos angostos.
private data class PaginaLibro(
    val titulo: String,
    val izquierda: String,
    val derecha: String
)

private val paginas = listOf(
    PaginaLibro(
        titulo = "Prólogo",
        izquierda = "2035. Dante y la expedición del capitán Vargas llegan al piso 20.",
        derecha = "Una criatura enorme los alcanza. Oscuridad. La expedición nunca regresó."
    ),
    PaginaLibro(
        titulo = "La despedida",
        izquierda = "Antes de partir, Abby le dio a Dante el colgante de su padre.",
        derecha = "«Me lo devuelves cuando vuelvas.»"
    ),
    PaginaLibro(
        titulo = "La entrada",
        izquierda = "Tres meses después. A las dos semanas dejaron de buscar a la expedición.",
        derecha = "«Nadie va a entrar por él. Entonces entro yo.»"
    ),
    PaginaLibro(
        titulo = "El ascenso",
        izquierda = "Abby sube siguiendo las hojas del diario de Dante.",
        derecha = "Piso 4: huellas de botas que regresan. De una sola persona."
    ),
    PaginaLibro(
        titulo = "Rastros",
        izquierda = "Piso 5: una mano negra marcada en el cristal.",
        derecha = "Piso 7: una fogata. Una sola manta."
    ),
    PaginaLibro(
        titulo = "La cima",
        izquierda = "En la cima se acaban las huellas. Algo la espera.",
        derecha = "La Sombra. Y pelea con su mismo combo."
    ),
    PaginaLibro(
        titulo = "Final",
        izquierda = "La Sombra cae. Abby reconoce el colgante: «Era de papá. Yo te lo di.»",
        derecha = "Dante venía de regreso todo ese tiempo. «Volví.» Continuará…"
    )
)

@Composable
fun HistoryScreen(onBack: () -> Unit) {
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
            BarraSuperior(titulo = "Historia", onBack = onBack)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArcoConPersonaje()
                BandaHistoria()
                Spacer(Modifier.height(8.dp))
                LibroConPaginas()
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable

private fun ArcoConPersonaje() {
    val arco = ImageBitmap.imageResource(R.drawable.arco)
    val personaje = ImageBitmap.imageResource(R.drawable.abby)

    Box(
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .aspectRatio(1f)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(Modifier.weight(0.27f))
            Image(
                bitmap = personaje,
                contentDescription = "Personaje",
                contentScale = ContentScale.Fit,
                filterQuality = FilterQuality.None,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.67f)
            )
            Spacer(Modifier.weight(0.06f))
        }
        Image(
            bitmap = arco,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            filterQuality = FilterQuality.None,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun BandaHistoria() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(
                Brush.verticalGradient(listOf(Color(0xFF3B2A18), Color(0xFF6B4A22), Color(0xFF3B2A18)))
            )
            .drawBehind {
                drawLine(GoldSoft, Offset(0f, 0f), Offset(size.width, 0f), strokeWidth = 3f)
                drawLine(GoldSoft, Offset(0f, size.height), Offset(size.width, size.height), strokeWidth = 3f)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "HISTORIA",
            color = Color(0xFFE9C766),
            fontFamily = FontFamily.Serif,
            fontSize = 26.sp
        )
    }
}

private val PapelHoja = Color(0xFFF8E5BF)


@Composable
private fun LibroConPaginas() {
    val libro = ImageBitmap.imageResource(R.drawable.libro)
    val ultima = paginas.size - 1
    val pagerState = rememberPagerState(pageCount = { paginas.size })
    val densidad = LocalDensity.current.density

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .aspectRatio(1.5f)
    ) {
        val ancho = maxWidth
        val alto = maxHeight

        Image(
            bitmap = libro,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            filterQuality = FilterQuality.None,
            modifier = Modifier.fillMaxSize()
        )


        val pos = (pagerState.currentPage + pagerState.currentPageOffsetFraction)
            .coerceIn(0f, ultima.toFloat())
        val base = floor(pos).toInt().coerceIn(0, ultima)
        val t = if (base >= ultima) 0f else pos - base
        val siguiente = (base + 1).coerceAtMost(ultima)
        val brillo = sin(PI * t).toFloat().coerceIn(0f, 1f)


        Box(
            modifier = Modifier
                .offset(x = ancho * 0.21f, y = alto * 0.15f)
                .size(width = ancho * 0.26f, height = alto * 0.57f)
                // Se desvanece cuando la hoja que gira cae encima con el texto nuevo
                .graphicsLayer { alpha = (2f - 2f * t).coerceIn(0f, 1f) }
        ) {
            TextoPagina(paginas[base].izquierda, paginas[base].titulo)
        }


        Box(
            modifier = Modifier
                .offset(x = ancho * 0.57f, y = alto * 0.15f)
                .size(width = ancho * 0.29f, height = alto * 0.57f)
                // Invisible en reposo (ahí el texto lo pinta la hoja de encima);
                // aparece conforme la hoja se levanta
                .graphicsLayer { alpha = (2f * t).coerceIn(0f, 1f) }
        ) {
            TextoPagina(paginas[siguiente].derecha)
        }


        Box(
            modifier = Modifier
                .offset(x = ancho * 0.50f, y = alto * 0.10f)
                .size(width = ancho * 0.38f, height = alto * 0.68f)
                .graphicsLayer {
                    rotationY = -180f * t
                    cameraDistance = 12f * densidad
                    transformOrigin = TransformOrigin(0f, 0.5f)
                }
                .background(PapelHoja.copy(alpha = brillo))
        ) {
            if (t < 0.5f) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = ancho * 0.07f,
                            end = ancho * 0.02f,
                            top = alto * 0.05f,
                            bottom = alto * 0.06f
                        )
                ) {
                    TextoPagina(paginas[base].derecha)
                }
            } else {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = ancho * 0.03f,
                            end = ancho * 0.09f,
                            top = alto * 0.05f,
                            bottom = alto * 0.06f
                        )
                ) {
                    Box(Modifier.fillMaxSize().graphicsLayer { scaleX = -1f }) {
                        TextoPagina(paginas[siguiente].izquierda, paginas[siguiente].titulo)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.18f * brillo))
            )
        }


        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { Box(Modifier.fillMaxSize()) }
    }

    Spacer(Modifier.height(10.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(paginas.size) { i ->
            if (i == pagerState.currentPage) {
                Box(
                    Modifier
                        .size(12.dp)
                        .rotate(45f)
                        .background(Gold)
                )
            } else {
                Box(
                    Modifier
                        .size(9.dp)
                        .background(GoldSoft, CircleShape)
                )
            }
        }
    }
}

@Composable
private fun TextoPagina(texto: String, titulo: String? = null) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (titulo != null) {
            Text(
                text = titulo.uppercase(),
                color = TituloLibro,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                lineHeight = 13.sp,
                maxLines = 1
            )
            Spacer(Modifier.height(3.dp))
        }
        Text(
            text = texto,
            color = TextoLibro,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            lineHeight = 14.sp,
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    AbbysTowerAppTheme { HistoryScreen(onBack = {}) }
}