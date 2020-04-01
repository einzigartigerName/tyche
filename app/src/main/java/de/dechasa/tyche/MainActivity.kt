package de.dechasa.tyche

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var optionListView : ListView
    private lateinit var adapter : ArrayAdapter<String>

    private var optionValues = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        optionListView = findViewById<ListView>(R.id.optionListView)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, optionValues)
        optionListView.adapter = adapter
    }

    fun addOption(view: View) {
        val inputText = findViewById<EditText>(R.id.add_text)
        val input = inputText.text.toString()
        if(input.isNotEmpty()) {
            optionValues.add(input)
            inputText.setText("")
            adapter.notifyDataSetChanged()
        }
    }

    fun resetOptions(view: View){
        optionValues.clear()
        adapter.notifyDataSetChanged()
    }

    fun chooseRandomly(view: View) {
        if(optionValues.size > 0) {
            for(i in (0 until (optionValues.size - 1)))
                optionListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT)

            val selected = (0 until optionValues.size).random()
            optionListView.getChildAt(selected).setBackgroundColor(Color.GREEN)
        }
    }
}
