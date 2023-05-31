package ac.id.ubaya.informatika.s160419137.todoapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="note")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}