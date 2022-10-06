package com.example.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TaskItemBinding
import com.example.todoapp.models.Task

class TaskAdapter (var list : List<Task>) : RecyclerView.Adapter<TaskAdapter.MyVH>() {
    inner class MyVH(var itemBinding :TaskItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(task : Task) {

        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyVH =
        MyVH(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder : MyVH, position : Int) = holder.onBind(list[position])
    override fun getItemCount() : Int = list.size

}