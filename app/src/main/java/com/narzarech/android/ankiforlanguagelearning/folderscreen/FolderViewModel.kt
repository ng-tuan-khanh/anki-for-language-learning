package com.narzarech.android.ankiforlanguagelearning.folderscreen

import androidx.lifecycle.*
import com.narzarech.android.ankiforlanguagelearning.database.AppDao
import com.narzarech.android.ankiforlanguagelearning.database.Folder
import kotlinx.coroutines.launch


class FolderViewModel(val database: AppDao): ViewModel() {
    val listFolders: LiveData<List<Folder>> = database.getAllFolders()
    fun insertFolder(folder: Folder) {
        viewModelScope.launch {
            database.insertFolder(folder)
        }
    }
    fun removeFolder(folder: Folder?) {
        folder?.let {
            viewModelScope.launch {
                database.removeFolder(folder.id)
            }
        }
    }

    fun updateFolder(folder: Folder?, name: String) {
        folder?.let {
            removeFolder(folder)
            insertFolder(Folder(name = name))
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