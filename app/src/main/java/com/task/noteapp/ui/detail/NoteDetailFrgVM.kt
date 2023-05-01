package com.task.noteapp.ui.detail

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.CoroutinesRoom
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NoteDetailFrgVM @Inject constructor(
    application: Application,
    private val noteRepository: NoteRepository
) :
    AndroidViewModel(application) {

    var noteData = MutableLiveData<NoteModel>()
    var success = MutableLiveData<Boolean>(false)

    fun getDetail(noteId: Int) {
        viewModelScope.launch {
            try {
                noteData.value = noteRepository.getNote(noteId)
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
            }
        }
    }

    fun insert(noteModel: NoteModel) {
        viewModelScope.launch {
            try {
                noteRepository.insertNote(noteModel)
                success.value = true
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
            }
        }
    }

    fun update(noteModel: NoteModel) {
        viewModelScope.launch {
            try {
                noteRepository.update(noteModel)
                success.value = true
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
            }
        }
    }

    fun delete(noteModel: NoteModel) {
        viewModelScope.launch {
            try {
                noteRepository.delete(noteModel.id)
                success.value=true
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
            }
        }


    }
}