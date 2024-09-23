package com.example.myp6

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class waktu : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // Apply the spinner style for TimePickerDialog
        val is24Hour = DateFormat.is24HourFormat(activity)

        // Use a specific theme for spinner mode
        return TimePickerDialog(
            requireActivity(),
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar, // Spinner style theme
            activity as TimePickerDialog.OnTimeSetListener,
            hourOfDay,
            minute,
            is24Hour
        )
    }
}
