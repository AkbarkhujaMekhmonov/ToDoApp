package com.example.todoapp.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ListItemBinding
import com.example.todoapp.db.Model
import com.example.todoapp.db.MyDataBase
import com.example.todoapp.models.List2
import com.example.todoapp.models.Task
import java.util.*
import kotlin.collections.ArrayList


class ListAdapter(
    var list : List<List2>,
    var context : Context,
    var listener : SetOnItemclickListener,
    var del : DeleteListItem,
    var edit:EditListItem,
    var type:Int
) : RecyclerView.Adapter<ListAdapter.MyVH>() {
      inner class MyVH(var itemBinding : ListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun onBind(list : List2) {

            if(type==2){
                itemBinding.more.isVisible=false
            }

            itemBinding.titleList.text = list.title
            val data = MyDataBase.getInstants(context).dao()
            val allTasks = data.getAllTasks(list.id) as ArrayList<Task>
            var i = 0
            allTasks.forEach {
                i++
            }
            itemBinding.countTasks.text = "$i Tasks"
            when (list.color) {
                1 -> {
                    itemBinding.color.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.black
                        )
                    )
                    itemBinding.titleList.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.white
                        )
                    )
                    itemBinding.countTasks.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.white
                        )
                    )
                    itemBinding.more.setImageResource(R.drawable.ic_baseline_more_vert_24_white)
                }
                2 -> {
                    itemBinding.color.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.blue
                        )
                    )
                }
                3 -> {
                    itemBinding.color.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.purple
                        )
                    )
                    itemBinding.titleList.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.white
                        )
                    )
                    itemBinding.countTasks.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.white
                        )
                    )
                    itemBinding.more.setImageResource(R.drawable.ic_baseline_more_vert_24_white)

                }
                4 -> {
                    itemBinding.color.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.yellow
                        )
                    )
                }
                5 -> {
                    itemBinding.color.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.red
                        )
                    )
                }
                6 -> {
                    itemBinding.color.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.green
                        )
                    )
                }
                7 -> {
                    itemBinding.color.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.white_back
                        )
                    )
                }
            }
            itemBinding.root.setOnClickListener {
                listener.setOnClicklistener(list)
            }
            itemBinding.more.setOnClickListener {
                var popupMenu = PopupMenu(context, itemBinding.more)
                popupMenu.menuInflater.inflate(R.menu.more_list_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.delete -> {
                            val builder = AlertDialog.Builder(context)
                            val dialog2 = builder.create()
                            val view : View =
                                LayoutInflater.from(context)
                                    .inflate(R.layout.delete_dialog, null, false)
                            view.findViewById<Button>(R.id.no)
                                .setOnClickListener { dialog2.cancel() }
                            view.findViewById<Button>(R.id.yes).setOnClickListener {
                                del.deleteLst(list)
                                dialog2.cancel()
                            }
                            dialog2.setView(view)
                            dialog2.show()
                        }
                        R.id.edit->{
                            val builder = AlertDialog.Builder(context)
                            val dialog2 = builder.create()
                            val view : View =
                                LayoutInflater.from(context)
                                    .inflate(R.layout.add_list_dialog, null, false)
                            var button = view.findViewById<Button>(R.id.add_btn)
                            button.setText("Edit")
                            var color : Int = 1
                            when(list.color){
                                1->{
                                    button.setBackgroundColor(
                                        ContextCompat.getColor(
                                            context,
                                            R.color.black
                                        )
                                    )
                                    button.setTextColor(
                                        ContextCompat.getColor(
                                            context,
                                            R.color.white
                                        )
                                    )
                                }
                                2->{
                                    view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                        ContextCompat.getColor(
                                            context,
                                            R.color.blue
                                        )
                                    )
                                }
                                3->{
                                    view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                        ContextCompat.getColor(
                                            context,
                                            R.color.purple
                                        )
                                    )
                                }
                                4->{
                                    view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                        ContextCompat.getColor(
                                            context,
                                            R.color.yellow
                                        )
                                    )
                                }
                                5->{
                                    view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                        ContextCompat.getColor(
                                            context,
                                            R.color.red
                                        )
                                    )
                                }
                                6->{
                                    view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                        ContextCompat.getColor(
                                            context,
                                            R.color.green
                                        )
                                    )
                                }
                                7->{
                                    view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                        ContextCompat.getColor(
                                            context,
                                            R.color.white_back
                                        )
                                    )
                                }
                            }

                            view.findViewById<View>(R.id.black).setOnClickListener {
                                var button = view.findViewById<Button>(R.id.add_btn)
                                button.setBackgroundColor(
                                    ContextCompat.getColor(
                                        context,
                                        R.color.black
                                    )
                                )
                                button.setTextColor(
                                    ContextCompat.getColor(
                                        context,
                                        R.color.white
                                    )
                                )

                                color = 1
                            }
                            view.findViewById<View>(R.id.blue).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        context,
                                        R.color.blue
                                    )
                                )
                                color = 2
                            }
                            view.findViewById<View>(R.id.purple).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        context,
                                        R.color.purple
                                    )
                                )
                                color = 3
                            }
                            view.findViewById<View>(R.id.yellow).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        context,
                                        R.color.yellow
                                    )
                                )
                                color = 4
                            }
                            view.findViewById<View>(R.id.red).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        context,
                                        R.color.red
                                    )
                                )
                                color = 5
                            }
                            view.findViewById<View>(R.id.green).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        context,
                                        R.color.green
                                    )
                                )
                                color = 6
                            }
                            view.findViewById<View>(R.id.white).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        context,
                                        R.color.white_back
                                    )
                                )
                                color = 7
                            }
                            view.findViewById<Button>(R.id.add_btn).setOnClickListener {
                                var title = view.findViewById<EditText>(R.id.title_list).text.toString()
                                var list2 = List2(title, color)
                                edit.editList(list2)
                                dialog2.cancel()

                            }

                            dialog2.setView(view)

                            dialog2.show()
                        }
                    };true
                }
                popupMenu.show()
            }

        }
    }


    interface SetOnItemclickListener {
        fun setOnClicklistener(list : List2)
    }

    interface DeleteListItem {
        fun deleteLst(list : List2)
    }

    interface EditListItem{
        fun editList(list : List2)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyVH =
        MyVH(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder : MyVH, position : Int) {
        return holder.onBind(list[position])
    }

    override fun getItemCount() : Int = list.size


}