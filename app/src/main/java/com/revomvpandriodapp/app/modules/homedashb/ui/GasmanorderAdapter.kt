package com.revomvpandriodapp.app.modules.homedashb.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowGasmanorder1Binding
import com.revomvpandriodapp.app.modules.homedashb.`data`.model.Gasmanorder1RowModel
import kotlin.Int
import kotlin.collections.List

class GasmanorderAdapter(
  var list: List<Gasmanorder1RowModel>
) : RecyclerView.Adapter<GasmanorderAdapter.RowGasmanorder1VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowGasmanorder1VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_gasmanorder1,parent,false)
    return RowGasmanorder1VH(view)
  }

  override fun onBindViewHolder(holder: RowGasmanorder1VH, position: Int) {
    val gasmanorder1RowModel = list[position]
    holder.binding.gasmanorder1RowModel = gasmanorder1RowModel
  }

  override fun getItemCount(): Int = list.size

  public fun updateData(newData: List<Gasmanorder1RowModel>) {
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
      item: Gasmanorder1RowModel
    ) {
    }
  }

  inner class RowGasmanorder1VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowGasmanorder1Binding = RowGasmanorder1Binding.bind(itemView)
    init {
      binding.btnDetails.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, Gasmanorder1RowModel())
      }
    }
  }
}
