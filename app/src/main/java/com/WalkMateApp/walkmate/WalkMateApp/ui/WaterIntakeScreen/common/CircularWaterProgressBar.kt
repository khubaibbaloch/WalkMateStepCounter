package com.WalkMateApp.walkmate.WalkMateApp.ui.WaterIntakeScreen.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.WalkMateApp.walkmate.R
import kotlin.math.sin

@Composable
fun CircularWaterProgressBar(progress: Float, intake: Float, total: Float) {
    // Animate the progress value
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000)
    )
    // Animate the intake value
    val animatedIntake by animateFloatAsState(
        targetValue = intake,
        animationSpec = tween(durationMillis = 1000)
    )

    // Animate the wave offset
    val waveOffset = remember { Animatable(0f) }
    LaunchedEffect(progress) {
        waveOffset.snapTo(0f)  // Reset wave offset
        waveOffset.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(200.dp)
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val strokeWidth = 20f
            val radius = size.minDimension / 2
            val circleRadius = radius - strokeWidth / 2

            // Draw the border
            drawArc(
                color = Color.Gray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Draw the progress
            val fillHeight = size.height * animatedProgress
            val waveAmplitude = 10f
            val waveFrequency = 1f

            // First wave path (dark blue)
            val darkBlueWavePath = Path().apply {
                moveTo(0f, size.height - fillHeight)
                for (i in 0..size.width.toInt()) {
                    val angle = ((i + waveOffset.value * size.width) / size.width * 2 * Math.PI * waveFrequency).toFloat()
                    val waveOffsetY = waveAmplitude * sin(angle)
                    lineTo(i.toFloat(), size.height - fillHeight + waveOffsetY)
                }
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }


            // Second wave path (light blue)
            val lightBlueWavePath = Path().apply {
                moveTo(0f, size.height - fillHeight)
                for (i in 0..size.width.toInt()) {
                    val angle = ((i + waveOffset.value * size.width + 20) / size.width * 2 * Math.PI * waveFrequency).toFloat()
                    val waveOffsetY = waveAmplitude+10f * sin(angle)
                    lineTo(i.toFloat(), size.height - fillHeight + waveOffsetY)
                }
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            // Create a circular clipping path
            val circularClipPath = Path().apply {
                addOval(Rect(center = Offset(size.width / 2, size.height / 2), radius = circleRadius))
            }

            drawIntoCanvas { canvas ->
                val paint = Paint().apply {
                    isAntiAlias = true
                }
                val clipRect = Rect(center = Offset(size.width / 2, size.height / 2), radius = circleRadius)

                canvas.saveLayer(clipRect, paint)
                canvas.clipPath(circularClipPath)

                // Draw dark blue wave
                drawPath(
                    path = darkBlueWavePath,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF004080), // Dark blue
                            Color(0xFF42b2ff)  // Light blue
                        ),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height - fillHeight)
                    )
                )

                // Draw light blue wave
                drawPath(
                    path = lightBlueWavePath,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF42b2ff), // Light blue
                            Color(0xFFADD8E6)  // Lighter blue
                        ),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height - fillHeight)
                    )
                )

                canvas.restore()
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Aligns items at the top
        ) {
            Image(
                painter = painterResource(id = R.drawable.water_bottle_in_progress),
                contentDescription = "water bottle",
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = String.format("%.0f", animatedIntake),
                color = Color.White,
                fontSize = 24.sp,
            )
            Text(
                text = String.format("/%.0f ml", total),
                color = Color.Gray,
                fontSize = 12.sp,
            )
        }
    }
}


/*@Composable
fun CircularWaterProgressBar(progress: Float, intake: Float, total: Float) {
    // Animate the progress value
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000)
    )
    // Animate the intake value
    val animatedIntake by animateFloatAsState(
        targetValue = intake,
        animationSpec = tween(durationMillis = 1000)
    )

    // Animate the wave offset
    val waveOffset = remember { Animatable(0f) }
    LaunchedEffect(progress) {
        waveOffset.snapTo(0f)  // Reset wave offset
        waveOffset.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(200.dp)
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val strokeWidth = 20f
            val radius = size.minDimension / 2
            val circleRadius = radius - strokeWidth / 2

            // Draw the border
            drawArc(
                color = Color.DarkGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Draw the progress
            val fillHeight = size.height * animatedProgress
            val waveAmplitude = 10f
            val waveFrequency = 2f

            // First wave path (dark blue)
            val darkBlueWavePath = Path().apply {
                moveTo(0f, size.height - fillHeight)
                for (i in 0..size.width.toInt()) {
                    val angle = ((i + waveOffset.value * size.width) / size.width * 2 * Math.PI * waveFrequency).toFloat()
                    val waveOffsetY = waveAmplitude * sin(angle)
                    lineTo(i.toFloat(), size.height - fillHeight + waveOffsetY)
                }
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }


            // Second wave path (light blue)
            val lightBlueWavePath = Path().apply {
                moveTo(0f, size.height - fillHeight)
                for (i in 0..size.width.toInt()) {
                    val angle = ((i + waveOffset.value * size.width + 20) / size.width * 2 * Math.PI * waveFrequency).toFloat()
                    val waveOffsetY = waveAmplitude+10f * sin(angle)
                    lineTo(i.toFloat(), size.height - fillHeight + waveOffsetY)
                }
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            // Create a circular clipping path
            val circularClipPath = Path().apply {
                addOval(Rect(center = Offset(size.width / 2, size.height / 2), radius = circleRadius))
            }

            drawIntoCanvas { canvas ->
                val paint = Paint().apply {
                    isAntiAlias = true
                }
                val clipRect = Rect(center = Offset(size.width / 2, size.height / 2), radius = circleRadius)

                canvas.saveLayer(clipRect, paint)
                canvas.clipPath(circularClipPath)

                // Draw dark blue wave
                drawPath(
                    path = darkBlueWavePath,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF004080), // Dark blue
                            Color(0xFF42b2ff)  // Light blue
                        ),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height - fillHeight)
                    )
                )

                // Draw light blue wave
                drawPath(
                    path = lightBlueWavePath,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF42b2ff), // Light blue
                            Color(0xFFADD8E6)  // Lighter blue
                        ),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height - fillHeight)
                    )
                )

                canvas.restore()
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Aligns items at the top
        ) {
            Image(
                painter = painterResource(id = R.drawable.water_bottle_in_progress),
                contentDescription = "water bottle",
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = String.format("%.0f", animatedIntake),
                color = Color.White,
                fontSize = 24.sp,
            )
            Text(
                text = String.format("/%.0f ml", total),
                color = Color.Gray,
                fontSize = 12.sp,
            )
        }
    }
}
*/