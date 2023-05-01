package com.task.noteapp.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.R
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.databinding.ListItemBinding
import com.task.noteapp.listener.NoteClickListener
import java.util.ArrayList

class NoteAdapter(
    val noteList: ArrayList<NoteModel>,
    val listener: NoteClickListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    class NoteViewHolder(var view: ListItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ListItemBinding>(
            inflater,
            R.layout.list_item, parent, false
        )
        return NoteViewHolder(view)

    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.view.data = noteList[position]
        holder.view.listener = listener
    }


    fun updateNoteList(newNoteList: List<NoteModel>) {
        noteList.clear()
        noteList.addAll(newNoteList)
        notifyDataSetChanged()
    }

}