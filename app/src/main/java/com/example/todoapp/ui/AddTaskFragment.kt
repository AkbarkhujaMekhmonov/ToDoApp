package com.example.todoapp.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.Time
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.adapters.ListAdapter
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.db.Model
import com.example.todoapp.db.MyDataBase
import com.example.todoapp.models.List2
import com.example.todoapp.models.Task
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS


class AddTaskFragment : Fragment() , Observer{
    lateinit var binding : FragmentAddTaskBinding
    var myModel : Model? = null
    var date:String?=null
    var time:String?=null
    var list2 : List2? = null
    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentAddTaskBinding.inflate(layoutInflater)
        myModel = Model(binding.root.context)
        myModel!!.addObserver(this)
        binding.cancelBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        val myData = MyDataBase.getInstants(binding.root.context).dao()
        val allList = myData.getAllList()
        binding.listArray.adapter =
            ListAdapter(allList, binding.root.context, listener, del, edit, 2)
        var isChecked = binding.isDone.isChecked
        when (myModel?.getIndicator()?.color) {
            1 -> {
                binding.indicatorLis.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.black
                    )
                )
            }
            2 -> {
                binding.indicatorLis.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.blue
                    )
                )
            }
            3 -> {
                binding.indicatorLis.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.purple
                    )
                )
            }
            4 -> {
                binding.indicatorLis.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.yellow
                    )
                )
            }
            5 -> {
                binding.indicatorLis.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.red
                    )
                )
            }
            6 -> {
                binding.indicatorLis.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.green
                    )
                )
            }
            7 -> {
                binding.indicatorLis.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.white_back
                    )
                )
            }

        }
        val mycalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            mycalendar.set(Calendar.YEAR, year)
            mycalendar.set(Calendar.MONTH, month)
            mycalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(mycalendar)
        }
        binding.calendarBtn.setOnClickListener {
            DatePickerDialog(
                binding.root.context, datePicker, mycalendar.get(Calendar.YEAR), mycalendar.get(Calendar.MONTH),
                mycalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        getTime(binding.timeBtn,binding.root.context)
        binding.doneBtn.setOnClickListener {
            var task=Task(binding.titleTask.text.toString(),date,time,list2!!.id,isChecked)
            myData.addTask(task)
            findNavController().popBackStack()
        }






        return binding.root
    }

    override fun update(p0 : Observable?, p1 : Any?) {
       when(myModel?.getIndicator()?.color){
           1 -> {
               binding.indicatorLis.setBackgroundColor(
                   ContextCompat.getColor(
                       binding.root.context,
                       R.color.black
                   )
               )
           }
           2 -> {
               binding.indicatorLis.setBackgroundColor(
                   ContextCompat.getColor(
                       binding.root.context,
                       R.color.blue
                   )
               )
           }
           3 -> {
               binding.indicatorLis.setBackgroundColor(
                   ContextCompat.getColor(
                       binding.root.context,
                       R.color.purple
                   )
               )
           }
           4 -> {
               binding.indicatorLis.setBackgroundColor(
                   ContextCompat.getColor(
                       binding.root.context,
                       R.color.yellow
                   )
               )
           }
           5 -> {
               binding.indicatorLis.setBackgroundColor(
                   ContextCompat.getColor(
                       binding.root.context,
                       R.color.red
                   )
               )
           }
           6 -> {
               binding.indicatorLis.setBackgroundColor(
                   ContextCompat.getColor(
                       binding.root.context,
                       R.color.green
                   )
               )
           }
           7 -> {
               binding.indicatorLis.setBackgroundColor(
                   ContextCompat.getColor(
                       binding.root.context,
                       R.color.white_back
                   )
               )
           }
       }
        binding.listTitle.text=myModel?.getIndicator()?.title
    }


    var listener = object : ListAdapter.SetOnItemclickListener {
        override fun setOnClicklistener(list : List2) {
            myModel?.setIndicator(list)
        }
    }
    var del = object : ListAdapter.DeleteListItem {
        override fun deleteLst(list : List2) {

        }
    }
    var edit = object : ListAdapter.EditListItem {
        override fun editList(list : List2) {

        }
    }
    private fun updateLable(mycalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat)
        date=sdf.format(mycalendar.time)
    }
    fun getTime(textView: ImageView, context: Context){

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            time = SimpleDateFormat("HH:mm").format(cal.time)
        }

        textView.setOnClickListener {
            TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }
}