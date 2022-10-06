package com.example.todoapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity
class Task {
    @PrimaryKey(autoGenerate = true)
    var id:Int=0

    @ColumnInfo
    var text:String?=null

    @ColumnInfo
    var date:String?=null

    @ColumnInfo
    var time: String?=null

    @ColumnInfo
    var list_id:Int=0

    @ColumnInfo
    var done:Boolean?=null

    constructor(
        text : String?,
        date : String?,
        time : String?,
        list_id : Int,
        done : Boolean
    ) {
        this.text = text
        this.date = date
        this.time = time
        this.list_id = list_id
        this.done = done
    }
    constructor()
}