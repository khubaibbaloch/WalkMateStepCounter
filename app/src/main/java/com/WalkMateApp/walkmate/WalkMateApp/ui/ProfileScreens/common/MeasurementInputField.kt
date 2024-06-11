package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeasurementInputField(value: String, onValueChange: (String) -> Unit, label: String, isError: Boolean) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\$"))) {
                onValueChange(newValue)
            }
        },
        label = { Text(text = label, color = if (isError) Color.Red else WalkMateThemes.colorScheme.textColor) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 0.dp),
        textStyle = TextStyle(color = WalkMateThemes.colorScheme.textColor),
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if (isError) Color.Red else WalkMateThemes.colorScheme.textColor,
            unfocusedBorderColor = if (isError) Color.Red else WalkMateThemes.colorScheme.textColor,
            cursorColor = if (isError) Color.Red else WalkMateThemes.colorScheme.textColor
        )
    )
}
