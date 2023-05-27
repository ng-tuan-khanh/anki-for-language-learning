package com.narzarech.android.ankiforlanguagelearning.utilities

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    modifier: Modifier,
    dialogName: String,
    showInput: Boolean = true,
    folderName: String = "",
    onValueChange: (String) -> Unit = {},
    onDismiss: () -> Unit,
    onSubmit: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = dialogName,
                    style = MaterialTheme.typography.titleLarge
                )
                if (showInput) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = folderName,
                        onValueChange = { newFolderName -> onValueChange(newFolderName) }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OutlinedButton(
                        onClick = onDismiss
                    ) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = onSubmit
                    ) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomDialogPreview() {
    CustomDialog(Modifier, dialogName = "Folder", onDismiss = {}, onSubmit = {})
}