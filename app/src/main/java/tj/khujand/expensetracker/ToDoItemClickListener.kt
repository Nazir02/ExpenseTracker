package tj.khujand.expensetracker

import com.encom.todo.data.model.ToDoModel

interface ToDoItemClickListener {
    fun onItemClick(todoItem: Int, item: ToDoModel)
}