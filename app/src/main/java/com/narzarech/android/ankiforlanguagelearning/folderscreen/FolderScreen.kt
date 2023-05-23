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
import com.narzarech.android.ankiforlanguagelearning.utilities.AddFolderDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderScreen(
    title: String = "Folders",
    folderViewModel: FolderViewModel,
    onNavigateToDeckScreen: () -> Unit
) {
    // Data from the database
    val listFolders by folderViewModel.listFolders.observeAsState()

    // UI states of the dialog
    var showDialog by remember { mutableStateOf(false) }
    var newFolderName by remember { mutableStateOf("") }

    // Store the index of the item currently expanded.
    // The value of -1 means that there is no item expanded.
    var indexSelected by remember { mutableStateOf(-1) }
    Scaffold(bottomBar = {
        if (indexSelected == -1) {
            BottomAppBar(actions = {}, floatingActionButton = {
                FloatingActionButton(
                    onClick = { showDialog = true },
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

            if (showDialog) {
                AddFolderDialog(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    folderName = newFolderName,
                    onValueChange = { newFolderName = it },
                    onDismiss = { showDialog = !showDialog },
                    onSubmit = {
                        folderViewModel.insertFolder(Folder(name = newFolderName))
                        newFolderName = ""
                        showDialog = !showDialog
                    }
                )
            }
        }
    }
}

