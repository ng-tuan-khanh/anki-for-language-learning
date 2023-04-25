package com.narzarech.android.ankiforlanguagelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
                    Screen("Folders")
                }
            }
        }
    }
}

@Composable
fun Card() {
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        tonalElevation = 1.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxSize()
            .shadow(1.dp)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                style = MaterialTheme.typography.headlineMedium,
                text = "Language"
            )
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = "Last Reviewed: Never"
            )
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = "Number of Decks: 0"
            )
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = "Reviewed: 0 / 0"
            )
        }
    }
}

@Composable
fun Screen(title: String) {
    Column() {
        Text(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp),
            text = title,
            style = MaterialTheme.typography.displayMedium
        )
        LazyColumn(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(0.dp))
            }
            items(6) {
                Card()
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
            Screen("Folders")
        }
    }
}