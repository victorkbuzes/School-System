package com.example.e_commerceapp.presentation.components

import android.telecom.StatusHints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StandardTextField(
    value: String = "",
    text: String = "",
    hints: String = "",
    isError: Boolean = false,
    onValueChange:(String) -> Unit

){
    OutlinedTextField(value = text, onValueChange = onValueChange,
    placeholder = {
        Text(
            text = hints,
            style = MaterialTheme.typography.body1
        )
    },
        modifier = Modifier.fillMaxWidth()
    )
    

}