package com.narzarech.android.ankiforlanguagelearning

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderScreen(title: String = "Folders") {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {},
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Add new folders")
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(IntrinsicSize.Max)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 4.dp),
                    text = title,
                    style = MaterialTheme.typography.displaySmall
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 24.dp, end = 24.dp)
                )
            }

            // TODO: Hard-coded for a list of 6 elements, need to fix later
            val tmpData = (1..6).map { "Item $it" }
            val isSelected = remember {
                mutableStateListOf(*tmpData.map { false }.toTypedArray())
            }
            LazyColumn(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(0.dp))
                }
                items(6) {index ->
                    Item(
                        isItemSelected = isSelected[index],
                        onClick = {
                            for (i in 0..5) {
                                isSelected[i] = false
                            }
                            isSelected[index] = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Item(isItemSelected: Boolean, onClick: (Boolean) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        tonalElevation = 1.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxSize()
            .toggleable(
                value = isItemSelected,
                onValueChange = onClick
            )
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
            if (isItemSelected) {
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
}