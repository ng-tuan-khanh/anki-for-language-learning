package com.narzarech.android.ankiforlanguagelearning

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import com.narzarech.android.ankiforlanguagelearning.ui.theme.AnkiForLanguagelearningTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckScreen(title: String = "Decks") {
    // Store the index of the item currently expanded.
    // The value of -1 means that there is no item expanded.
    var indexSelected by remember { mutableStateOf(-1) }
    Scaffold(bottomBar = {
        if (indexSelected == -1) {
            BottomAppBar(actions = {}, floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* do something */ },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Icon(Icons.Filled.Add, "Add new folders")
                }
            })
        } else {
            BottomAppBar(actions = {}, floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { /* do something */ },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Icon(Icons.Filled.ChevronRight, "View Deck FAB")
                    Text(
                        text = "View Deck",
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            })
        }
    }) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(IntrinsicSize.Max)
            ) {
                Text(
                    modifier = Modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 24.dp,
                            bottom = 4.dp
                        ), text = title, style = MaterialTheme.typography.displaySmall
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 24.dp, end = 24.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(6) { index ->
                    Item(isItemSelected = (index == indexSelected), onClick = {
                        indexSelected = if (indexSelected != index) index else -1
                    })
                }
            }
        }
    }
}

@Preview
@Composable
fun DeckScreenPreview() {
    AnkiForLanguagelearningTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            DeckScreen()
        }
    }
}