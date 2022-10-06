package com.example.todoapp.db

import android.content.Context
import com.example.todoapp.models.List2
import java.util.*
import kotlin.collections.ArrayList


class Model(var context : Context) : Observable() {
    // declaring a list of integer
    val List : MutableList<List2>
    var list2:List2
    val myData = MyDataBase.getInstants(context).dao()

    // constructor to initialize the list
    init {
        // reserving the space for list elements
        List = ArrayList()
        List.addAll(myData.getAllList())
        list2= List2()

        // adding elements into the list
    }

    // defining getter and setter functions
    // function to return appropriate count
    // value at correct index
    @Throws(IndexOutOfBoundsException::class)
    fun getValueAll() : MutableList<List2> {
        return List
    }

    // function to make changes in the activity button's
    // count value when user touch it
    @Throws(IndexOutOfBoundsException::class)
    fun setValue(list : List2) {
        myData.addList(list)
        List.add(list)
        setChanged()
        notifyObservers()
    }

    @Throws(IndexOutOfBoundsException::class)
    fun removeList(list : List2) {
        myData.deleteList(list)
        List.remove(list)
        setChanged()
        notifyObservers()
    }

    @Throws(IndexOutOfBoundsException::class)
    fun editList(list : List2){
        myData.updateList(list)
        var i=0
        List.forEach {
            if(it.id==list.id){
                List[i]=list
            }
            i++
        }
        setChanged()
        notifyObservers()
    }

    @Throws(IndexOutOfBoundsException::class)
    fun setIndicator(list : List2){
        list2=list

        setChanged()
        notifyObservers()
    }
    @Throws(IndexOutOfBoundsException::class)
    fun getIndicator():List2{
        return list2
    }
}