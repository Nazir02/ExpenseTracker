package com.encom.todo.ui.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tj.khujand.expensetracker.MainActivity
import tj.khujand.expensetracker.R

class ToDoInputBottomSheetDialog : BottomSheetDialogFragment() {

    var inputListener: OnToDoInputListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.input_bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextTitle = view.findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextDescription)

        view.findViewById<View>(R.id.buttonAdd).setOnClickListener {
            val title = editTextTitle.text.toString()
            if (title.isNotEmpty()) {
                val description = editTextDescription.text.toString()
                inputListener?.onToDoInputed(title, description)
                dismiss()
            }else{
                Toast.makeText(requireContext(), "пусто", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<View>(R.id.buttonCancel).setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance(inputListener: OnToDoInputListener): ToDoInputBottomSheetDialog {
            val fragment = ToDoInputBottomSheetDialog()
            fragment.inputListener = inputListener
            return fragment
        }
    }
}