package com.example.homework4
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class Dialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Важное сообщение")
                .setMessage("Ты пидор")
                .setPositiveButton("Гыгык") {
                        dialog, id ->  dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
