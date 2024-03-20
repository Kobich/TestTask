package com.example.testtask.Activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.APi.ApiCall
import com.example.testtask.APi.DataUserModel.DataModel
import com.example.testtask.R
import com.example.testtask.RecycleView.RecycleViewAdapter
import com.example.testtask.SQLiteBaseHelper.DatabaseHelper


class MainActivity : ComponentActivity() {


    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val addUser:Button = findViewById(R.id.add_user)
        val progressBar: ProgressBar = findViewById(R.id.idLoadingPB)

        dbHelper = DatabaseHelper(this)

        val data = mutableListOf<DataModel>()
        val adapter = RecycleViewAdapter(data)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        data.addAll(dbHelper.getAllUsers())

        addUser.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            ApiCall().getUserData(this) { parse ->

                dbHelper.addUser(parse)

                data.add(parse)
                recyclerView.adapter = adapter
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}

