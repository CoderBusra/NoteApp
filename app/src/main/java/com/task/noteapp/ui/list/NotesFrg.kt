package com.task.noteapp.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.task.noteapp.R
import com.task.noteapp.adapter.NoteAdapter
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.databinding.NotesFragmentBinding
import com.task.noteapp.listener.NoteClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFrg : Fragment() , NoteClickListener{

    companion object {
        fun newInstance() = NotesFrg()
    }

    private lateinit var binding: NotesFragmentBinding
    private val viewModel: NotesFrgVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.notes_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.list()
        var adapter = NoteAdapter(arrayListOf(), this)
        binding.characterList.adapter= adapter
        viewModel.noteLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateNoteList(it)
            }
        })


        binding.floatingActionButton.setOnClickListener {
            val action = NotesFrgDirections.actionNotesFrgToNoteDetailFrg()
            findNavController().navigate(action)
        }
    }

    override fun itemClick(noteModel: NoteModel) {
        val action = NotesFrgDirections.actionNotesFrgToNoteDetailFrg(noteId = noteModel.id)
        findNavController().navigate(action)
    }

}