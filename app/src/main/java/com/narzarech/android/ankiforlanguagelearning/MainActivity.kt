package com.narzarech.android.ankiforlanguagelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.narzarech.android.ankiforlanguagelearning.database.AppDao
import com.narzarech.android.ankiforlanguagelearning.database.AppDatabase
import com.narzarech.android.ankiforlanguagelearning.folderscreen.FolderViewModel
import com.narzarech.android.ankiforlanguagelearning.ui.theme.AnkiForLanguagelearningTheme


class MainActivity : ComponentActivity() {
    private lateinit var database: AppDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = AppDatabase.getInstance(this).dao

        setContent {
            AnkiForLanguagelearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyNavHost(database = database)
                }
            }
        }
    }
}


@Preview
@Composable
fun AppPreview() {
    AnkiForLanguagelearningTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
        }
    }
}