package com.narzarech.android.ankiforlanguagelearning.folderscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import com.narzarech.android.ankiforlanguagelearning.database.Folder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderScreen(
    title: String = "Folders",
    folderViewModel: FolderViewModel,
    onNavigateToDeckScreen: () -> Unit
) {
//    var showDialog by remember { mutableStateOf(false) }
//    if (showDialog) {
//        CustomDialog(
//            setShowDialog = {it ->
//                showDialog = it
//            },
//        )
//    }

    val listFolders by folderViewModel.listFolders.observeAsState()

    // Store the index of the item currently expanded.
    // The value of -1 means that there is no item expanded.
    var indexSelected by remember { mutableStateOf(-1) }
    Scaffold(bottomBar = {
        if (indexSelected == -1) {
            BottomAppBar(actions = {}, floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*showDialog = true*/ },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Icon(Icons.Filled.Add, "Add new folders")
                }
            })
        } else {
            BottomAppBar(actions = {}, floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = onNavigateToDeckScreen,
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Icon(Icons.Filled.ChevronRight, "View Folder FAB")
                    Text(
                        text = "View Folder",
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
                itemsIndexed(listFolders ?: mutableListOf()) { index, folder ->
                    FolderItem(
                        folder = folder,
                        isItemSelected = (index == indexSelected),
                        onClick = {
                            indexSelected = if (indexSelected != index) index else -1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FolderItem(folder: Folder, isItemSelected: Boolean, onClick: (Boolean) -> Unit) {
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
                style = MaterialTheme.typography.headlineMedium, text = folder.name
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

//@Composable
//fun CustomDialog(setShowDialog: (Boolean) -> Unit,) {
//    Dialog(
//        onDismissRequest = { setShowDialog(false) }
//    ) {
//        Column {
//        }
//    }
//}

