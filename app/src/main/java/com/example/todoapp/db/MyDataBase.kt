package com.example.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.models.List2
import com.example.todoapp.models.Task

@Database(entities=[List2::class, Task::class],version=5)
abstract class MyDataBase:RoomDatabase() {

    abstract fun dao(): MyDao

    companion object {
        private var instens: MyDataBase? = null


        fun getInstants(context: Context): MyDataBase {
            if (instens == null) {
                instens = Room.databaseBuilder(context, MyDataBase::class.java, "room")

                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build()
            }

            return instens!!

        }

    }
}