package com.example.todoapp.db

import androidx.room.*
import com.example.todoapp.models.List2
import com.example.todoapp.models.Task

@Dao
interface MyDao {
    @Insert
    fun addList(list : List2)

    @Delete
    fun deleteList(list : List2)

    @Update
    fun updateList(list : List2)

    @Insert
    fun addTask(task:Task)

    @Delete
    fun deleteTask(task:Task)

    @Update
    fun updatedTask(task:Task)

    @Query("select * from List2")
    fun getAllList():List<List2>

    @Query("select * from Task where list_id=:id")
    fun getAllTasks(id:Int):List<Task>

    @Query("select * from Task where date=:date")
    fun getDayTasks(date: String):List<Task>

}