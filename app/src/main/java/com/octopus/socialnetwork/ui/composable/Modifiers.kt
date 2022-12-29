package com.octopus.socialnetwork.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
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


@Stable
fun Modifier.mirroringIcon(): Modifier =
    composed {
        if (LocalLayoutDirection.current == LayoutDirection.Rtl) {
            this.scale(scaleX = -1f, scaleY = 1f)
        } else {
            this
        }
    }


@Stable
@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.coloredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 2.dp,
    shadowRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = composed {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparent = color.copy(alpha = 0f).toArgb()

    this.drawBehind {

        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparent

            frameworkPaint.setShadowLayer(
                shadowRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                shadowColor
            )
            it.drawRoundRect(
                0f,
                0F,
                this.size.width,
                this.size.height,
                borderRadius.toPx(),
                borderRadius.toPx(),
                paint
            )
        }
    }
}