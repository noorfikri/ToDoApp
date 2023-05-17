package ac.id.ubaya.informatika.s160419137.todoapp.ViewModel

import ac.id.ubaya.informatika.s160419137.todoapp.Model.ToDoDatabase
import ac.id.ubaya.informatika.s160419137.todoapp.Model.Todo
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ToDoDetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope{

    private val job = Job()
    fun addTodo(list:List<Todo>) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(), ToDoDatabase::class.java,
                "newtododb").build()
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}