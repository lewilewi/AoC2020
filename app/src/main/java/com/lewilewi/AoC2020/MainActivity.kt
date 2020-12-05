package com.lewilewi.AoC2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_list_item_1, // Layout
            List<Int>(25) { it + 1 } // List of days 1..25
        )

        simpleGridView.adapter = adapter

        simpleGridView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val calc = AoCFactory().CreateAoC(position + 1, applicationContext)
                Result.text = "Result 1: " + calc.Result1() + "\nResult 2: " + calc.Result2()
            }
        }
    }
}