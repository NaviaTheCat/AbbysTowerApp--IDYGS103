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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide

private val Gold = Color(0xFFF2B705)
private val GoldSoft = Color(0xFFC9A04A)

@Composable
fun BarraSuperior(titulo: String, onBack: () -> Unit) {
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
            text = titulo,
            color = GoldSoft,
            fontFamily = FontFamily.Serif,   // luego Cinzel
            fontSize = 26.sp
        )
    }
}