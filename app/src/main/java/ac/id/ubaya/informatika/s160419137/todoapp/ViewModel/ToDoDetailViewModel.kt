package ac.id.ubaya.informatika.s160419137.todoapp.ViewModel

import ac.id.ubaya.informatika.s160419137.todoapp.Model.ToDoDatabase
import ac.id.ubaya.informatika.s160419137.todoapp.Model.Todo
import ac.id.ubaya.informatika.s160419137.todoapp.Util.buildDb
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ToDoDetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope{
    val todoLD = MutableLiveData<Todo>()
    private val job = Job()
    fun addTodo(todo: Todo) {
        launch {
            val db = buildDb(getApplication())

            /*val db = Room.databaseBuilder(
                getApplication(), ToDoDatabase::class.java,
                "newtododb").build()*/

            db.todoDao().insertAll(todo)
        }
    }

    fun fetch(uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            todoLD.value =  db.todoDao().selectTodo(uuid)
        }
    }

    fun update(title:String, notes:String, priority:Int, uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }



    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}