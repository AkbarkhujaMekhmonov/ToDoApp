package com.example.todoapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class List2 {
    @PrimaryKey(autoGenerate = true)
    var id:Int=0

    @ColumnInfo
    var title:String?=null

    @ColumnInfo
    var color:Int=0

    constructor(title :String, color : Int){
        this.title=title
        this.color=color
    }
    constructor()
}