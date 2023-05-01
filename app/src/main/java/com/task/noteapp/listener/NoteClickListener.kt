package com.task.noteapp.listener

import android.provider.ContactsContract
import com.task.noteapp.data.model.NoteModel

interface NoteClickListener {
    fun itemClick(noteModel: NoteModel)
}