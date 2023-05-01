package com.task.noteapp.ui.detail

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.task.noteapp.R
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.databinding.NoteDetailFragmentBinding
import com.task.noteapp.util.DatetimeHeper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.note_detail_fragment.*
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class NoteDetailFrg : Fragment() {
    private var noteModel: NoteModel? = null
    private var byteArrayImage: ByteArray? = null
    private lateinit var binding: NoteDetailFragmentBinding
    private var noteId = 0
    private val viewModel: NoteDetailFrgVM by viewModels()


    companion object {
        fun newInstance() = NoteDetailFrg()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.note_detail_fragment, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments.let {
            noteId = NoteDetailFrgArgs.fromBundle(it!!).noteId
        }
        viewModel.getDetail(noteId)
        observer()
        click()
    }

    private fun click() {
        binding.imgBtnSave.setOnClickListener {
            insertData()
        }
        binding.imgBtnCamera.setOnClickListener {
            getContent.launch("image/*")
        }
        binding.imgBtnEdit.setOnClickListener {
            updateData()
        }

        binding.imgBtnDelete.setOnClickListener {
            deleteData()
        }
    }

    private fun deleteData() {

        var noteModel = NoteModel(
            id = noteId,
            title = binding.edtTitle.text.toString(),
            description = binding.edtDescription.text.toString(),
            createDate = DatetimeHeper.getcurrentDate(),
        )
        viewModel.delete(noteModel)
    }

    private fun updateData() {

        var noteModel = NoteModel(
            id = noteId,
            title = binding.edtTitle.text.toString(),
            description = binding.edtDescription.text.toString(),
            createDate = DatetimeHeper.getcurrentDate(),
            edit = true,
            picture = noteModel?.picture
        )
        viewModel.update(noteModel)
    }

    private fun insertData() {

        var noteModel = NoteModel(
            title = binding.edtTitle.text.toString(),
            description = binding.edtDescription.text.toString(),
            createDate = DatetimeHeper.getcurrentDate(),
            picture = byteArrayImage
        )
        viewModel.insert(noteModel)
    }

    private fun observer() {
        viewModel.noteData.observe(viewLifecycleOwner, Observer {
            binding.data = it
            noteModel = it
        })

        viewModel.success.observe(viewLifecycleOwner, Observer {
            if (it)
                findNavController().popBackStack()

        })
    }


    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        try {
            var bitmap = MediaStore.Images.Media.getBitmap(
                requireActivity().contentResolver,
                uri
            )
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            byteArrayImage = stream.toByteArray()
            noteModel?.picture = stream.toByteArray()
            binding.imageView.setImageBitmap(bitmap)

        } catch (e: Exception) {

        }
    }

}