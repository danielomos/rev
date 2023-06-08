package com.revomvpandriodapp.app.modules.housedash.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowGasmanorder2Binding
import com.revomvpandriodapp.app.modules.housedash.`data`.model.Gasmanorder2RowModel
import kotlin.Int
import kotlin.collections.List

class GasmanorderAdapter(
  var list: List<Gasmanorder2RowModel>
) : RecyclerView.Adapter<GasmanorderAdapter.RowGasmanorder2VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowGasmanorder2VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_gasmanorder2,parent,false)
    return RowGasmanorder2VH(view)
  }

  override fun onBindViewHolder(holder: RowGasmanorder2VH, position: Int) {
    val gasmanorder2RowModel = Gasmanorder2RowModel()
    // TODO uncomment following line after integration with data source
    // val gasmanorder2RowModel = list[position]
    holder.binding.gasmanorder2RowModel = gasmanorder2RowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Gasmanorder2RowModel>) {
    list = newData
    notifyDataSetChanged()
  }

  fun setOnItemClickListener(clickListener: OnItemClickListener) {
    this.clickListener = clickListener
  }

  interface OnItemClickListener {
    fun onItemClick(
      view: View,
      position: Int,
      item: Gasmanorder2RowModel
    ) {
    }
  }

  inner class RowGasmanorder2VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowGasmanorder2Binding = RowGasmanorder2Binding.bind(itemView)
    init {
      binding.btnDetails.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, Gasmanorder2RowModel())
      }
    }
  }
}
