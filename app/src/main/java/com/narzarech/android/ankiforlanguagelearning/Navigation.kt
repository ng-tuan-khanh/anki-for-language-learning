package com.narzarech.android.ankiforlanguagelearning

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.narzarech.android.ankiforlanguagelearning.database.AppDao
import com.narzarech.android.ankiforlanguagelearning.folderscreen.FolderScreen
import com.narzarech.android.ankiforlanguagelearning.folderscreen.FolderViewModel

@Composable
fun MyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "folders",
    database: AppDao
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("folders") {
            FolderScreen(
                folderViewModel = viewModel(
                factory = FolderViewModel.Factory(database)
            ),
                onNavigateToDeckScreen = {
                navController.navigate("decks")
            })
        }
        composable("decks") {
            DeckScreen()
        }
    }
}