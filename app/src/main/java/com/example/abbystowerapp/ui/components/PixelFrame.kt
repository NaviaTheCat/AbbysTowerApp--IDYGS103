package com.example.abbystowerapp.ui.components

/**
 * Project: AbbysTowerApp
 * From: com.example.abbystowerapp.ui.components
 * Created by: karla
 * On: 05/11/2026
 * All rights reserved: 2026
 */


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.example.abbystowerapp.R


@Composable
fun PixelFrame(
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
                val c = (iw * 0.19f).toInt()
                val d = cornerSize.roundToPx()
                val w = size.width.toInt()
                val h = size.height.toInt()


                if (innerBrush != null) {
                    val inset = d * 0.6f
                    drawRect(
                        brush = innerBrush,
                        topLeft = Offset(inset, inset),
                        size = Size(size.width - 2 * inset, size.height - 2 * inset)
                    )
                }


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