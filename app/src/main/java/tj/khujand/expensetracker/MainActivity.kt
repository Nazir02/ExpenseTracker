package tj.khujand.expensetracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.encom.todo.data.model.ToDoModel
import com.encom.todo.ui.Dialogs.OnToDoInputListener
import com.encom.todo.ui.Dialogs.ToDoInputBottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tj.khujand.expensetracker.adapter.ToDoAdapter

class MainActivity : AppCompatActivity(), OnToDoInputListener {
    lateinit var adapter:ToDoAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            ToDoInputBottomSheetDialog.newInstance(this).show(this.supportFragmentManager, "ToDoInputBottomSheetDialog")

        }
        val loadedToDoList = ToDoSharedPreferencesHelper.getToDoList(this)
        val recyclerView: RecyclerView = findViewById(R.id.rvMain)
         adapter = ToDoAdapter(this, loadedToDoList.toMutableList())
        adapter.setItemClickListener(object : ToDoItemClickListener {
            override fun onItemClick(position: Int, todoItem: ToDoModel) {
                ToDoSharedPreferencesHelper.removeToDoItem(this@MainActivity, position)
                val updatedToDoList = ToDoSharedPreferencesHelper.getToDoList(this@MainActivity)
                adapter.updateToDoList(updatedToDoList)
                updateSum()
            }
        })
        recyclerView.adapter = adapter
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onToDoInputed(title: String, description: String) {
        val todoList: List<ToDoModel> = listOf(ToDoModel(1,title,description,false))
            ToDoSharedPreferencesHelper.saveToDoList(this, todoList)
        val newToDoList = ToDoSharedPreferencesHelper.getToDoList(this)
            adapter.updateToDoList(newToDoList)
        updateSum()
    }

    fun updateSum(){
        val allToDoItems: List<ToDoModel> = ToDoSharedPreferencesHelper.getToDoList(this)
        val totalSum: Float = allToDoItems.fold(0f) { acc, todo ->
            acc + todo.title.toFloatOrNull()!! ?: 0f
        }
        findViewById<TextView>(R.id.tvSumOutcome).text= totalSum.toString()
    }
}
