package de.dechasa.tyche

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeCallback(private val adapter: OptionAdapter) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    // Draw Delete Background
    private val icon: Drawable? = ContextCompat.getDrawable(adapter.getContext(), R.drawable.ic_delete_24)
    private val background: ColorDrawable = ColorDrawable(Color.RED)


    override fun onChildDraw(
        canvas: Canvas, rv: RecyclerView, holder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(canvas, rv, holder, dX, dY, actionState, isCurrentlyActive)
        val itemView = holder.itemView
        val bgCornerOffset = 20
        val iconMargin = (itemView.height - icon!!.intrinsicHeight) / 2
        val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
        val iconBottom = iconTop + icon.intrinsicHeight

        // Swiping to the left
        if (dX < 0) {
            val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
            val iconRight = itemView.right - iconMargin
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            background.setBounds(
                itemView.right + dX.toInt() - bgCornerOffset,
                itemView.top,
                itemView.right,
                itemView.bottom
            )
            background.draw(canvas)
            icon.draw(canvas)
        } else { // view is not swiped
            background.setBounds(0, 0, 0, 0)
        }
    }

    override fun onSwiped(holder: RecyclerView.ViewHolder, direction: Int) {
        val pos = holder.adapterPosition
        adapter.deleteItem(pos)
    }

    override fun onMove(
        rv: RecyclerView,
        holder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

}