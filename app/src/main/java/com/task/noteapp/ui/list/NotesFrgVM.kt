package com.task.noteapp.ui.list

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.repository.NoteRepository
import com.task.noteapp.util.DatetimeHeper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesFrgVM @Inject constructor(
    application: Application,
    private val noteRepository: NoteRepository
) :
    AndroidViewModel(application) {

    val noteLiveData = MutableLiveData<List<NoteModel>>()

    fun list() {
        viewModelScope.launch {
            val notes = noteRepository.getAllNotes()
            noteLiveData.value = notes
        }


    }


}