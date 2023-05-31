package ac.id.ubaya.informatika.s160419137.todoapp.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    fun selectTodo(id:Int): Todo

    @Delete
    fun deleteTodo(todo:Todo)

    @Query("UPDATE todo SET title=:title, note=:notes, priority=:priority WHERE uuid = :uuid")
    fun update(title:String, notes:String, priority:Int, uuid:Int)
}