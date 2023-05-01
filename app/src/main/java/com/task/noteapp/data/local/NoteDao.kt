package com.task.noteapp.data.local

import androidx.room.*
import com.task.noteapp.data.model.NoteModel

@Dao
interface NoteDao {
    @Insert
    suspend fun insertAll(vararg noteModel: NoteModel): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(vararg noteModel: NoteModel): List<Long>

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<NoteModel>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNote(noteId: Int): NoteModel

    @Query("DELETE FROM notes WHERE id= :noteId")
    suspend fun delete(noteId: Int)

}