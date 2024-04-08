package com.encom.todo.ui.Dialogs

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import tj.khujand.expensetracker.R

class CustomDialogs() {

    companion object{
        @SuppressLint("MissingInflatedId")
        fun showInputDialog(context: Context,inputListener: OnToDoInputListener){

            val builder = AlertDialog.Builder(context)
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.input_dialog, null)

            val editTextTitle = view.findViewById<EditText>(R.id.editTextTitle)
            val editTextDescription = view.findViewById<EditText>(R.id.editTextDescription)

            builder.setView(view)
                .setPositiveButton("OK") { _, _ ->
                    val title = editTextTitle.text.toString()
                    val description = editTextDescription.text.toString()
                    inputListener.onToDoInputed(title, description)

                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }
    }

}
