package com.narzarech.android.ankiforlanguagelearning.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folder_table")
data class Folder(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0L,
    var name: String = "",
    //var deckList : List<Int> = mutableListOf()
)

@Entity(tableName = "deck_table")
data class Deck(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var name: String,
    //var cardList : List<Int> = mutableListOf()
)

@Entity(tableName = "card_table")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var name: String,
    var meaning: String,
    //var related: List<String> = mutableListOf()
)
