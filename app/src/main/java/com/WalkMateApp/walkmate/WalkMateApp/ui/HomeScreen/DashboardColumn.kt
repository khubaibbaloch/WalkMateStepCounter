package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.ui.theme.ProgressColor1
import com.WalkMateApp.walkmate.ui.theme.ProgressColor2
import com.WalkMateApp.walkmate.ui.theme.ProgressColor3
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun DashboardColumn(
    isWalking:MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier =
            Modifier.size(220.dp)
        ) {
//            CircularProgressIndicator(
//                trackColor = Color.White,
//                // progress = 50f,
//                strokeWidth = 8.dp, color = Color.Red, modifier = Modifier.size(200.dp)
//            )

            val targetSteps = 8000
            val stepsWalked = 5500


            // Steps walked Progress
            CustomCircularProgress(
                canvasSize = 220.dp,
                indicatorValue = stepsWalked,
                foregroundIndicatorStrokeWidth = 26f,
                maxIndicatorValue = targetSteps,
                isWalking = isWalking.value
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "3,600", style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Icon(
                    painter = painterResource(id = R.drawable.footsteps),
                    contentDescription = "Done",
                    modifier = Modifier.size(45.dp),
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
fun CustomCircularProgress(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 100,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorWidth: Float = 30f,
    foregroundIndicatorColor: Brush = Brush.sweepGradient(
        colors = listOf(
            ProgressColor3,
            ProgressColor2,
            ProgressColor1
        )
    ),
    foregroundIndicatorStrokeWidth: Float = 25f,
    isWalking: Boolean = false
) {
    var allowedIndicatorValue by remember {
        mutableStateOf(maxIndicatorValue)
    }
    allowedIndicatorValue = if (indicatorValue <= maxIndicatorValue) {
        indicatorValue
    } else {
        maxIndicatorValue
    }

    val animatedIndicatorValue = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue.animateTo(allowedIndicatorValue.toFloat())
    }
    val percentage = (animatedIndicatorValue.value / maxIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (3.6 * percentage).toFloat(),
        label = "",
        animationSpec = tween(1000)
    )

    val shadowSize = animateShadowSize(isWalking)
    val shadowColor = if (isWalking) ProgressColor2 else Color.Transparent

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorWidth
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth
                )
            }
            .shadow(
                shadowSize.value.toDp(),
                spotColor = shadowColor,
                shape = CircleShape
            )
    ) {

    }
}

@Composable
private fun animateShadowSize(isWalking: Boolean): Animatable<Float, AnimationVector1D> {
    val animatableSize = remember { Animatable(initialValue = if (isWalking) 200f else 0f) }
    LaunchedEffect(isWalking) {
        animatableSize.animateTo(if (isWalking) 200f else 80f, animationSpec = tween(300))
    }
    return animatableSize
}

@Composable
private fun Float.toDp(): Dp {
    return with(LocalDensity.current) { this@toDp.dp }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f,
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Brush,
    indicatorStrokeWidth: Float,
    capWidth: Float = 25f
) {
    val center = Offset(size.width / 2f, size.height / 2f)

    // Calculate the position of the cap based on the current sweep angle
    val capPosition = Offset(
        x = center.x + (componentSize.width / 2f) * cos(Math.toRadians(sweepAngle.toDouble() - 90)).toFloat(),
        y = center.y + (componentSize.height / 2f) * sin(Math.toRadians(sweepAngle.toDouble() - 90)).toFloat()
    )

    // Draw the cap
    drawArc(
        color = ProgressColor2,
        startAngle = 0f,
        sweepAngle = 360f,
        useCenter = true,
        style = Stroke(
            width = capWidth,
            cap = StrokeCap.Round
        ),
        topLeft = capPosition - Offset(capWidth / 2f, capWidth / 2f),
        size = Size(capWidth, capWidth)
    )

    // Draw the progress arc
    drawArc(
        size = componentSize,
        brush = indicatorColor,
        startAngle = -90f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = center - Offset(componentSize.width / 2f, componentSize.height / 2f)
    )
}

