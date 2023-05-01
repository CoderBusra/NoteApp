package com.task.noteapp.data.repository

import com.task.noteapp.data.local.NoteDao
import com.task.noteapp.data.model.NoteModel
import javax.inject.Inject

class NoteRepositoryImp @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun getAllNotes(): List<NoteModel> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNote(noteId: Int): NoteModel {
        return noteDao.getNote(noteId)
    }

    override suspend fun insertNote(noteModel: NoteModel): List<Long> {
        return noteDao.insertAll(noteModel)
    }

    override suspend fun update(noteModel: NoteModel): List<Long> {
        return noteDao.updateAll(noteModel)
    }

    override suspend fun delete(noteId: Int){
        return noteDao.delete(noteId)
    }
}