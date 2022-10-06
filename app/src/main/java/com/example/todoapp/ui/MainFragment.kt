package com.example.todoapp.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.adapters.ListAdapter
import com.example.todoapp.adapters.TaskAdapter
import com.example.todoapp.databinding.FragmentMainBinding
import com.example.todoapp.db.Model
import com.example.todoapp.db.MyDataBase
import com.example.todoapp.models.List2
import java.util.*
import kotlin.collections.ArrayList


class MainFragment : Fragment(),Observer ,View.OnClickListener{
    lateinit var binding : FragmentMainBinding
    var myModel: Model? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentMainBinding.inflate(layoutInflater)

        val mydatabase = MyDataBase.getInstants(binding.root.context).dao()
        myModel = Model(binding.root.context)
        myModel!!.addObserver(this)
        val lists = myModel?.getValueAll()as ArrayList<List2>
        binding.listRv.adapter = ListAdapter(lists, binding.root.context,listener,delListener,editListener,1)
        val date = Date()
        val s : CharSequence = DateFormat.format("MMMM d, yyyy ", date.time)
        var d = s as String
        val tasks = mydatabase.getDayTasks(d)
        binding.rvTadayPlans.adapter = TaskAdapter(tasks)
        binding.addBtn.setOnClickListener(this)



        return binding.root
    }

    override fun update(p0 : Observable?, p1 : Any?) {
        val lists = myModel?.getValueAll()as ArrayList<List2>
        binding.listRv.adapter = ListAdapter(lists, binding.root.context,listener,delListener,editListener,1)
    }

    override fun onClick(v : View?) {
        when (v?.id) {
            R.id.add_btn->{
                var popupMenu = PopupMenu(binding.root.context, binding.addBtn)
                popupMenu.menuInflater.inflate(R.menu.category, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { item : MenuItem? ->
                    when (item!!.itemId) {
                        R.id.task -> {
                            findNavController().navigate(R.id.addTaskFragment)
                        }
                        R.id.list -> {
                            val builder = AlertDialog.Builder(binding.root.context)
                            val dialog2 = builder.create()
                            val view : View =
                                LayoutInflater.from(binding.root.context)
                                    .inflate(R.layout.add_list_dialog, null, false)

                            var color : Int = 1
                            view.findViewById<View>(R.id.black).setOnClickListener {
                                var button = view.findViewById<Button>(R.id.add_btn)
                                button.setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.black
                                    )
                                )
                                button.setTextColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.white
                                    )
                                )

                                color = 1
                            }
                            view.findViewById<View>(R.id.blue).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.blue
                                    )
                                )
                                color = 2
                            }
                            view.findViewById<View>(R.id.purple).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.purple
                                    )
                                )
                                color = 3
                            }
                            view.findViewById<View>(R.id.yellow).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.yellow
                                    )
                                )
                                color = 4
                            }
                            view.findViewById<View>(R.id.red).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.red
                                    )
                                )
                                color = 5
                            }
                            view.findViewById<View>(R.id.green).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.green
                                    )
                                )
                                color = 6
                            }
                            view.findViewById<View>(R.id.white).setOnClickListener {
                                view.findViewById<Button>(R.id.add_btn).setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.white_back
                                    )
                                )
                                color = 7
                            }
                            view.findViewById<Button>(R.id.add_btn).setOnClickListener {
                                var title = view.findViewById<EditText>(R.id.title_list).text.toString()
                                var list = List2(title, color)
                                myModel?.setValue(list)
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

    var listener = object : ListAdapter.SetOnItemclickListener {
        override fun setOnClicklistener(list : List2) {

        }
    }
    var delListener=object :ListAdapter.DeleteListItem{
        override fun deleteLst(list : List2) {
            myModel?.removeList(list)
        }
    }

    var editListener=object:ListAdapter.EditListItem{
        override fun editList(list : List2) {
            myModel?.editList(list)
        }
    }

}