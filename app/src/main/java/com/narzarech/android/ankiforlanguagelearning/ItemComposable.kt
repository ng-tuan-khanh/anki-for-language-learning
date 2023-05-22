package com.narzarech.android.ankiforlanguagelearning

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Item(isItemSelected: Boolean, onClick: (Boolean) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxSize()
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, MaterialTheme.shapes.medium)
            .toggleable(
                value = isItemSelected, onValueChange = onClick
            )
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 10.dp, vertical = 16.dp
            ), verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                style = MaterialTheme.typography.headlineMedium, text = "Language"
            )
            Text(
                style = MaterialTheme.typography.bodyMedium, text = "Last Reviewed: Never"
            )
            if (isItemSelected) {
                Text(
                    style = MaterialTheme.typography.bodyMedium, text = "Number of Decks: 0"
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium, text = "Reviewed: 0 / 0"
                )
            }
        }
    }
}