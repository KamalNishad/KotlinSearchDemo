package dev.kk.kotlinsearchdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.kk.kotlinsearchdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var TAG:String="activity"
    var mBinding:ActivityMainBinding?=null
    private lateinit var courseAdapter:CourseRVAdapter
    private var courseList: ArrayList<CourseRVModal> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var editTextTextPersonName = findViewById<EditText>(R.id.editTextTextPersonName)

        // on below line we are adding data to our list
        courseList.add(CourseRVModal("Android Development"))
        courseList.add(CourseRVModal("C++ Development"))
        courseList.add(CourseRVModal("Java Development"))
        courseList.add(CourseRVModal("Python Development"))
        courseList.add(CourseRVModal("JavaScript Development"))


        Log.d(TAG,"courseList ::$courseList")

        courseAdapter = CourseRVAdapter(courseList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = courseAdapter
        courseAdapter!!.notifyDataSetChanged()


        editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(po: CharSequence?, start: Int, before: Int, count: Int) {
                filter(po.toString());
                Log.d(TAG,"onTextChange"+filter(po.toString()))

            }

            override fun afterTextChanged(s: Editable?) {
                //filter(s.toString());
                Log.d(TAG,"afterTextChanged"+filter(s.toString()))
            }
        })
    }




    private fun filter(text:String){

        val filteredlist: ArrayList<CourseRVModal> = ArrayList()

        for (items in courseList){

            if (items.courseName.toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(items)
            }
        }

        if (filteredlist.isEmpty()){
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        }else{
            courseAdapter!!.filterList(filteredlist)
        }

    }

}