package com.example.abbystowerapp.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.abbystowerapp.Model.Niveles
import com.example.abbystowerapp.R
import com.example.abbystowerapp.ui.components.BarraSuperior
import com.example.abbystowerapp.ui.theme.AbbysTowerAppTheme
import com.example.abbystowerapp.ui.viewmodel.NivelesUiState
import com.example.abbystowerapp.ui.viewmodel.NivelesViewModel

private val Gold = Color(0xFFF2B705)
private val GoldSoft = Color(0xFFC9A04A)
private val BackgroundColor = Color(0xFF0B0719)
private val CardBackground = Color(0xFF2B0A3A)
private const val BACKGROUND_IMAGE_ALPHA = 0.5f

@Composable
fun LevelsScreen(
    onBack: () -> Unit,
    viewModel: NivelesViewModel = viewModel()
) {
    LevelsContent(
        state = viewModel.state,
        onRetry = viewModel::cargar,
        onBack = onBack
    )
}

@Composable
fun LevelsContent(
    state: NivelesUiState,
    onRetry: () -> Unit,
    onBack: () -> Unit
) {
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

            when (state) {
                is NivelesUiState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Gold)
                    }
                }

                is NivelesUiState.Error -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(state.mensaje, color = GoldSoft, fontSize = 16.sp)
                            Text(
                                text = "Reintentar",
                                color = Gold,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .clickable(onClick = onRetry)
                                    .padding(12.dp)
                            )
                        }
                    }
                }

                is NivelesUiState.Success -> {
                    if (state.niveles.isEmpty()) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Sin niveles", color = GoldSoft, fontSize = 16.sp)
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            items(state.niveles, key = { it.id }) { nivel ->
                                NivelCard(nivel)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NivelCard(nivel: Niveles) {
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
            AsyncImage(
                model = nivel.Imagen,
                contentDescription = nivel.Nombre,
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.None,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = nivel.Nombre,
            color = Gold,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Text(
            text = nivel.descripcion,
            color = Gold,
            fontSize = 15.sp,
            lineHeight = 17.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LevelsScreenPreview() {
    AbbysTowerAppTheme {
        LevelsContent(
            state = NivelesUiState.Success(
                listOf(
                    Niveles(1, "Piso 1 - El Pueblo del Risco", "Pueblo con río y casas de entramado, vacío", "")
                )
            ),
            onRetry = {},
            onBack = {}
        )
    }
}