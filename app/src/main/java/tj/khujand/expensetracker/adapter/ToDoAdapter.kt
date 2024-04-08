package tj.khujand.expensetracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.encom.todo.data.model.ToDoModel
import tj.khujand.expensetracker.R
import tj.khujand.expensetracker.ToDoItemClickListener

class ToDoAdapter(private val context: Context, private var toDoList: MutableList<ToDoModel>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private var itemClickListener: ToDoItemClickListener? = null

    fun setItemClickListener(listener: ToDoItemClickListener) {
        itemClickListener = listener
    }
    fun updateToDoList(newToDoList: List<ToDoModel>) {
        toDoList.clear()
        toDoList.addAll(newToDoList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentItem = toDoList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewSubTitle)
        private val delete: ImageView = itemView.findViewById(R.id.delete)
        private var currentItem: ToDoModel? = null

        init {
            delete.setOnClickListener(this)
        }

        fun bind(todoItem: ToDoModel) {
            currentItem = todoItem
            titleTextView.text = todoItem.title
            descriptionTextView.text = todoItem.description
        }

        override fun onClick(v: View?) {
            currentItem?.let { item ->
                adapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let { position ->
                    itemClickListener?.onItemClick(position, item)
                }
            }
        }
    }
}
