package tj.khujand.expensetracker

import android.content.Context
import com.encom.todo.data.model.ToDoModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ToDoSharedPreferencesHelper {
    private const val PREF_NAME = "todo_prefs"
    private const val KEY_TODO_LIST = "todo_list"

    fun saveToDoList(context: Context, todoList: List<ToDoModel>) {
        val gson = Gson()
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_TODO_LIST, null)
        val type = object : TypeToken<List<ToDoModel>>() {}.type
        val existingList: MutableList<ToDoModel> = Gson().fromJson(json, type) ?: mutableListOf()

        existingList.addAll(todoList)

        val updatedJson = gson.toJson(existingList)
        val editor = prefs.edit()
        editor.putString(KEY_TODO_LIST, updatedJson)
        editor.apply()
    }

    fun getToDoList(context: Context): List<ToDoModel> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_TODO_LIST, null)
        val type = object : TypeToken<List<ToDoModel>>() {}.type
        return Gson().fromJson(json, type) ?: emptyList()
    }
    fun removeToDoItem(context: Context, position: Int) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val todoListJson = prefs.getString(KEY_TODO_LIST, null)
        val todoListType = object : TypeToken<List<ToDoModel>>() {}.type
        val todoList: MutableList<ToDoModel> = Gson().fromJson(todoListJson, todoListType) ?: mutableListOf()

        if (position >= 0 && position < todoList.size) {
            todoList.removeAt(position)
            val updatedJson = Gson().toJson(todoList)
            val editor = prefs.edit()
            editor.putString(KEY_TODO_LIST, updatedJson)
            editor.apply()
        }
    }
}
