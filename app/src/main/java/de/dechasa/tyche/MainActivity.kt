package de.dechasa.tyche

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var optionListView : RecyclerView
    private lateinit var adapter : OptionAdapter
    private lateinit var edOption: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        optionListView = findViewById(R.id.rvOptions)
        edOption = findViewById(R.id.edAddOption)

        adapter = OptionAdapter(ArrayList(), this)
        optionListView.adapter = adapter
        optionListView.layoutManager = LinearLayoutManager(applicationContext)
        optionListView.itemAnimator = DefaultItemAnimator()
        optionListView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        val swipeListener = ItemTouchHelper(SwipeCallback(adapter))
        swipeListener.attachToRecyclerView(optionListView)


        edOption.setOnEditorActionListener { _, _, event ->
            if (event == null) {
                onClickAddOption(null)
                return@setOnEditorActionListener true
            }
            false
        }

    }

    fun onClickAddOption(view: View?) {
        val inputText = findViewById<EditText>(R.id.edAddOption)
        val input = inputText.text.toString()
        if(input.isNotEmpty()) {
            adapter.options.add(input)
            inputText.setText("")
            adapter.notifyDataSetChanged()
        }
    }

    fun onClickReset(view: View){
        adapter.clear()
    }

    fun onClickRandom(view: View) {
        val optionValues = adapter.options

        if(optionValues.size > 0) {
            val selected = (0 until optionValues.size).random()
            adapter.setSelected(selected)
        }
    }
}
