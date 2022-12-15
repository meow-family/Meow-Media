package com.octopus.socialnetwork.ui.composable


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.octopus.socialnetwork.ui.theme.LightBlack_65
import com.octopus.socialnetwork.ui.theme.LightBlack_86


@Stable
fun Modifier.backgroundVerticalGradientWhite(
    color: Color = Color.White
) = then(
    Modifier
        .fillMaxWidth()
        .height(250.dp)
        .clip(RectangleShape)
        .zIndex(1f)
        .background(
            Brush.verticalGradient(
                listOf(Color.Transparent, color)
            )
        )
)

@Stable
fun Modifier.backgroundVerticalGradientLightBlack() = then(
    Modifier.background(
        Brush.verticalGradient(listOf(Color.Transparent, LightBlack_86))
    )
)

@Stable
fun Modifier.shadowLightBlack() = then(
    Modifier.shadow(16.dp, ambientColor = LightBlack_65)
)

@Stable
fun Modifier.backgroundTextShadow() = then(
    Modifier.background(
        brush = Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                Color(0xD9000000)
            ),
        )
    )
)


@Stable
fun Modifier.underLineBoarder(
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Dp
) = then(
    modifier
        .fillMaxWidth()
        .drawBehind {

            val strokeWidthPx = strokeWidth.toPx()
            val y = size.height - strokeWidthPx / 2

            drawLine(
                color,
                Offset(0f, y),
                Offset(size.width, y),
                strokeWidthPx
            )
        }
)