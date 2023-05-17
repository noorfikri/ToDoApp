package ac.id.ubaya.informatika.s160419137.todoapp.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Todo::class), version = 1)
    abstract class ToDoDatabase:RoomDatabase(){
        abstract fun todoDao(): ToDoDAO

        companion object{
            @Volatile private var instance: ToDoDatabase ?= null
            private val LOCK = Any()
            private fun buildDatabase(context:Context) =
                Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "newtododb").build()

            operator fun invoke(context:Context) {
                if (instance != null) {
                    synchronized(LOCK) {
                        instance ?: buildDatabase(context).also {
                            instance = it
                        }
                    }
                }
            }
        }
    }
