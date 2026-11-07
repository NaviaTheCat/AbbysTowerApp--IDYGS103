package com.example.abbystowerapp.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.example.abbystowerapp.Data.PersonajesRepository
import com.example.abbystowerapp.Model.Filtro
import com.example.abbystowerapp.Model.aplicar
import com.example.abbystowerapp.R
import com.example.abbystowerapp.ui.components.CardCharacters
import com.example.abbystowerapp.ui.components.FiltroDialog
import com.example.abbystowerapp.ui.theme.AbbysTowerAppTheme

private val Gold = Color(0xFFF2B705)
private val GoldSoft = Color(0xFFC9A04A)
private val BackgroundColor = Color(0xFF0B0719)
private const val BACKGROUND_IMAGE_ALPHA = 0.5f

@Composable
fun CharactersScreen(
    onBack: () -> Unit,
    onCharacterClick: (Int) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf(Filtro()) }
    var mostrarFiltro by remember { mutableStateOf(false) }

    val characters = PersonajesRepository.personajes.aplicar(filtro, query)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)            // 1) color base
    ) {
        // 2) Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondo_secundario),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = BACKGROUND_IMAGE_ALPHA,
            modifier = Modifier.fillMaxSize()
        )

        // 3) Contenido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Barra superior: regresar + título
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color(0x33000000))
                        .border(2.dp, Gold)
                        .clickable(onClick = onBack),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Lucide.ArrowLeft,
                        contentDescription = "Volver",
                        tint = Gold,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Text(
                    text = "Personajes",
                    color = GoldSoft,
                    fontFamily = FontFamily.Serif,   // luego Cinzel
                    fontSize = 28.sp
                )
            }

            // Buscador + botón Filtrar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.weight(1f)
                )
                FilterButton(
                    activo = filtro.activo,
                    onClick = { mostrarFiltro = true }
                )
            }

            // Resultados
            if (characters.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Sin resultados", color = GoldSoft, fontSize = 16.sp)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(characters, key = { it.id }) { character ->
                        CardCharacters(
                            name = character.nombre,
                            imageRes = character.imagen,
                            onClick = { onCharacterClick(character.id) }
                        )
                    }
                }
            }
        }

        // 4) Ventana de filtros
        if (mostrarFiltro) {
            FiltroDialog(
                filtroActual = filtro,
                onAplicar = {
                    filtro = it
                    mostrarFiltro = false
                },
                onCerrar = { mostrarFiltro = false }
            )
        }
    }
}

@Composable
private fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        ),
        cursorBrush = SolidColor(Gold),
        modifier = modifier
            .height(40.dp)
            .background(Color(0x33000000))
            .border(2.dp, Gold),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.padding(horizontal = 12.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = "Buscar un personaje",
                        color = GoldSoft,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
private fun FilterButton(activo: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(104.dp)
            .height(40.dp)
            .background(if (activo) Color(0x66F2B705) else Color(0x33000000))
            .border(2.dp, Gold)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (activo) "Filtrar ●" else "Filtrar",
            color = if (activo) Gold else GoldSoft,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CharactersScreenPreview() {
    AbbysTowerAppTheme {
        CharactersScreen(onBack = {})
    }
}