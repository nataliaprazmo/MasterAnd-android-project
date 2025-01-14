package com.example.masterand.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun OutlinedTextFieldWithError(
    value: MutableState<String>,
    label: String,
    validator: (String) -> Boolean,
    errorMessage: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val isError = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value.value,
        onValueChange = {
            value.value = it
            isError.value = !validator(it)
        },
        trailingIcon = {
            if (isError.value) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Error",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },
        label = { Text(label) },
        singleLine = true,
        isError = isError.value,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        supportingText = {
            if (isError.value) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}