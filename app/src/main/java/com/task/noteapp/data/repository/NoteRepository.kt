package com.task.noteapp.data.repository

import android.provider.ContactsContract
import com.task.noteapp.data.model.NoteModel

interface NoteRepository {
    suspend fun getAllNotes(): List<NoteModel>
    suspend fun getNote(noteId: Int): NoteModel
    suspend fun insertNote(noteModel: NoteModel): List<Long>
    suspend fun update(noteModel: NoteModel): List<Long>
    suspend fun delete(noteId: Int)
}