package com.narzarech.android.ankiforlanguagelearning.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folder_table")
data class Folder(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "deck_list") var deckList: List<Int> = mutableListOf()
)

@Entity(tableName = "deck_table")
data class Deck(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "card_list") var cardList: List<Int> = mutableListOf()
)

@Entity(tableName = "card_table")
data class Card(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "meaning") var meaning: String,
    @ColumnInfo(name = "related_word_list") var relatedWordList: List<String> = mutableListOf()
)
