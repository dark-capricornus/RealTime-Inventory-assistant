package com.example.androidtutorials

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorials.databinding.ItemStockBinding

class StockAdapter(
    private val stockItems: List<StockItem>,
    private val showHighStock: Boolean // true for Overall Stock, false for Low Stock
) : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val binding = ItemStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val item = stockItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = stockItems.size

    inner class StockViewHolder(private val binding: ItemStockBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StockItem) {
            binding.itemName.text = item.name
            binding.itemPrice.text = "Price: $${item.price}"
            binding.itemQuantity.text = "Quantity: ${item.quantity}"

            // Change color based on stock quantity
            val color = if (showHighStock && item.quantity > 100) {
                Color.GREEN
            } else if (!showHighStock && item.quantity <= 100) {
                Color.RED
            } else {
                Color.GRAY // For any unmatched case (if present)
            }
            binding.itemQuantity.setTextColor(color)
        }
    }
}
