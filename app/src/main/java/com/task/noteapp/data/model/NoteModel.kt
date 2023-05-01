package com.task.noteapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "edit")
    var edit: Boolean = false,

    @ColumnInfo(name = "date")
    var createDate: String,

    @ColumnInfo(name = "picture", typeAffinity = ColumnInfo.BLOB)
    var picture: ByteArray? = null
)