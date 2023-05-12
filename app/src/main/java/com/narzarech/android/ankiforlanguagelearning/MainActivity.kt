package com.narzarech.android.ankiforlanguagelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.narzarech.android.ankiforlanguagelearning.ui.theme.AnkiForLanguagelearningTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnkiForLanguagelearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FolderScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun ScreenPreview() {
    AnkiForLanguagelearningTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            FolderScreen()
        }
    }
}