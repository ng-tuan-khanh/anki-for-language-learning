package com.narzarech.android.ankiforlanguagelearning.folderscreen

import androidx.lifecycle.*
import com.narzarech.android.ankiforlanguagelearning.database.AppDao
import com.narzarech.android.ankiforlanguagelearning.database.Folder
import kotlinx.coroutines.launch

class FolderViewModel(database: AppDao): ViewModel() {
    var listFolders: LiveData<List<Folder>> = database.getAllFolders()

    init {
        viewModelScope.launch {
            database.insertFolder(Folder(name = "Korean"))
        }
    }

    class Factory(val database: AppDao) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FolderViewModel::class.java)) {
                return FolderViewModel(database) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}