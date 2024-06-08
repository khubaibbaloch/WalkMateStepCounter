package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue


@Composable
fun ProfileDetailCard(
    onProfileImg: String,
    userName: String,
    age: String)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(TwilightBlue)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Image(
                painter = painterResource(id = if (onProfileImg == "Male") R.drawable.man else R.drawable.female),
                contentDescription = "",
                modifier = Modifier.clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = userName,
                    color = Color.White.copy(.9f),
                    fontSize = 16.sp
                )


                Text(
                    text = age,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MidnightBlue
                ),
                onClick = { /*TODO*/ })
            {
                Icon(
                    painter = painterResource(id = R.drawable.editicon),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        val fullText = "Congo! You have completed your today's goal"
        val coloredText = "Congo!"

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Yellow,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                ) {
                    append(coloredText)
                }
                append(fullText.substring(coloredText.length))
            },
            fontSize = 12.sp,
            color = Color.LightGray
        )

    }
}