package com.task.noteapp.util

import java.text.SimpleDateFormat
import java.util.*

class DatetimeHeper {
    companion object {
        fun getcurrentDate(): String {
            val currentTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale("tr"))
            return dateFormat.format(currentTime)
        }
    }

}