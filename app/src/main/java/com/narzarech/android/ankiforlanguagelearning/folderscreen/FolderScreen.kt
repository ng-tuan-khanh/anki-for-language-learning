package com.narzarech.android.ankiforlanguagelearning.folderscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.narzarech.android.ankiforlanguagelearning.database.Folder
import com.narzarech.android.ankiforlanguagelearning.utilities.CustomDialog

enum class ShowDialog {
    NONE, ADD, SEARCH, EDIT, DELETE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderScreen(
    title: String = "Folders",
    folderViewModel: FolderViewModel = viewModel(),
    onNavigateToDeckScreen: () -> Unit
) {

    // Data from the database
    val listFolders by folderViewModel.listFolders.observeAsState()

    // UI states of the dialog
    var showDialog by remember { mutableStateOf(ShowDialog.NONE) }
    var inputFolderName by remember { mutableStateOf("") }

    // Store the index of the item currently expanded.
    // The value of -1 means that there is no item expanded.
    var indexSelected by remember { mutableStateOf(-1) }
    Scaffold(bottomBar = {
        if (indexSelected == -1) {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { showDialog = ShowDialog.SEARCH }) {
                        Icon(Icons.Outlined.Search, contentDescription = "Search Button")
                    }
                }, floatingActionButton = {
                    FloatingActionButton(
                        onClick = { showDialog = ShowDialog.ADD },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Add new folders")
                    }
                })
        } else {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { showDialog = ShowDialog.SEARCH }) {
                        Icon(Icons.Outlined.Search, contentDescription = "Search Button")
                    }
                    IconButton(onClick = { showDialog = ShowDialog.DELETE }) {
                        Icon(Icons.Outlined.Delete, contentDescription = "Delete Button")
                    }
                    IconButton(onClick = { showDialog = ShowDialog.EDIT }) {
                        Icon(Icons.Outlined.Edit, contentDescription = "Rename Button")
                    }
                }, floatingActionButton = {
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

            if (showDialog == ShowDialog.ADD) {
                CustomDialog(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    dialogName = "Add",
                    folderName = inputFolderName,
                    onValueChange = { inputFolderName = it },
                    onDismiss = { showDialog = ShowDialog.NONE },
                    onSubmit = {
                        folderViewModel.insertFolder(Folder(name = inputFolderName))
                        inputFolderName = ""
                        showDialog = ShowDialog.NONE
                    }
                )
            } else if (showDialog == ShowDialog.EDIT) {
                CustomDialog(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    dialogName = "Rename",
                    folderName = inputFolderName,
                    onValueChange = { inputFolderName = it },
                    onDismiss = { showDialog = ShowDialog.NONE },
                    onSubmit = {
                        folderViewModel.updateFolder(
                            folder = listFolders?.get(indexSelected),
                            name = inputFolderName
                        )
                        inputFolderName = ""
                        showDialog = ShowDialog.NONE
                    }
                )
            } else if (showDialog == ShowDialog.DELETE) {
                CustomDialog(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    dialogName = "Delete",
                    showInput = false,
                    onDismiss = { showDialog = ShowDialog.NONE },
                    onSubmit = {
                        folderViewModel.removeFolder(folder = listFolders?.get(indexSelected))
                        showDialog = ShowDialog.NONE
                        indexSelected = -1
                    }
                )
            } else if (showDialog == ShowDialog.SEARCH) {
                CustomDialog(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    dialogName = "Search",
                    onDismiss = { showDialog = ShowDialog.NONE },
                    onSubmit = {
                        showDialog = ShowDialog.NONE
                        indexSelected = -1
                    }
                )
            }

        }
    }
}
