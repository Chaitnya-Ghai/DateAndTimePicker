package cg.tutorials.dateandtimepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cg.tutorials.dateandtimepicker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        binding.dateBtn.setOnClickListener {
//            DatePickerDialog(this,{_,year,month,date->},
//                Calendar.getInstance().get(Calendar.YEAR),
//                Calendar.getInstance().get(Calendar.MONTH),
//                Calendar.getInstance().get(Calendar.DATE)
//                ).show()
//        }
binding.tvDate.setOnClickListener {
    val calendar = Calendar.getInstance()
    val simpleDateFormat = SimpleDateFormat("dd/MMM/yyyy",Locale.getDefault())
//    setting calendar date to (current date - 10) and storing it into minDate
    calendar.add(Calendar.DATE,-10)
    val minDate=calendar.time
    //    setting calendar date to (min date + 20) i.e (current date +10) and storing it into manDate
    calendar.add(Calendar.DATE,20)
    val maxDate = calendar.time
    //    reset calendar date to original date/current date
    calendar.add(Calendar.DATE,-10)
    DatePickerDialog(this,{_,year,month,date->
        calendar.set(year,month,date)
        val selectedDate = calendar.time
        val formatDate = simpleDateFormat.format(selectedDate)
        binding.tvDate.text =formatDate
    },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DATE)
        ).apply {
        datePicker.minDate = minDate.time
        datePicker.maxDate = maxDate.time
        show()
    }

}
        binding.tvTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val simpletimeFormat = SimpleDateFormat("hh.mm/a",Locale.getDefault())
            TimePickerDialog(this,{_,hour,minute->
                if( (hour < 9 || hour > 18)||(hour == 18 && minute > 0)){
                    Toast.makeText(this,"Our Active Hours are from 9 AM to 6 PM", Toast.LENGTH_SHORT).show()
                }else{
                    calendar.set(Calendar.HOUR_OF_DAY,hour)
                    calendar.set(Calendar.MINUTE,minute)
                    val timeFormatString = simpletimeFormat.format(calendar.time)
                    binding.tvTime.text=timeFormatString
                }
                                  },

                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true).show()
        }

    }
}