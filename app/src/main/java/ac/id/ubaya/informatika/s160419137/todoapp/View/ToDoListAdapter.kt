package ac.id.ubaya.informatika.s160419137.todoapp.View

import ac.id.ubaya.informatika.s160419137.todoapp.Model.Todo
import ac.id.ubaya.informatika.s160419137.todoapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(val todoList:ArrayList<Todo>):RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>() {
    class ToDoListViewHolder(var view:View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_listitem_layout,parent,false)

        return ToDoListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        var checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        checktask.text = todoList[position].title
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}