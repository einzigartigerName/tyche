package de.dechasa.tyche

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OptionAdapter(
    val options: ArrayList<String>,
    private val context: Context
    ) : RecyclerView.Adapter<OptionAdapter.ViewHolder>() {

    private var selected = Int.MIN_VALUE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_option, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val label = holder.textView

        label.text = options[position]

        if (position == selected) {
            holder.root.setBackgroundColor(Color.GREEN)
        } else {
            holder.root.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }

    /**
     * Delete Item by Position
     * @param pos Position of Item to be deleted
     */
    fun deleteItem(pos: Int) {
        selected = Int.MIN_VALUE
        options.removeAt(pos)
        notifyItemRemoved(pos)
    }

    /**
     * Return current Context
     * @return Context of Adapter
     */
    fun getContext(): Context {
        return context
    }

    fun setSelected(selected: Int) {
        this.selected = selected
        notifyDataSetChanged()
    }

    fun clear() {
        options.clear()
        selected = Int.MIN_VALUE
        notifyDataSetChanged()
    }

    /**
     * Ref to TextView
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.txtOption)
        val root: View = view
    }
}