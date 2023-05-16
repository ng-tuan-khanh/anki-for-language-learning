package com.narzarech.android.ankiforlanguagelearning.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoomDao {
    @Insert
    suspend fun insertFolder(folder: Folder)

    @Update
    suspend fun updateFolder(folder: Folder)

    @Query("DELETE FROM folder_table WHERE id = :folderId")
    suspend fun removeFolder(folderId: Long)

    @Query("SELECT * FROM folder_table WHERE id = :folderId")
    suspend fun getFolder(folderId: Long): Folder?

    @Query("DELETE FROM folder_table")
    suspend fun clearFolderDB()

    @Insert
    suspend fun insertDeck(deck: Deck)

    @Update
    suspend fun updateDeck(deck: Deck)

    @Query("DELETE FROM deck_table WHERE id = :deckId")
    suspend fun removeDeck(deckId: Long)

    @Query("SELECT * FROM deck_table WHERE id = :deckId")
    suspend fun getDeck(deckId: Long): Deck?

    @Query("DELETE FROM deck_table")
    suspend fun clearDeckDB()

    @Insert
    suspend fun insertCard(card: Card)

    @Update
    suspend fun updateCard(card: Card)

    @Query("DELETE FROM card_table WHERE id = :cardId")
    suspend fun removeCard(cardId: Long)

    @Query("SELECT * FROM card_table WHERE id = :folderId")
    suspend fun getCard(cardId: Long): Card?

    @Query("DELETE FROM card_table")
    suspend fun clearCardDB()
}