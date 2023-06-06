package com.revomvpandriodapp.app.modules.gasmancontiner.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowGasmanorderBinding
import com.revomvpandriodapp.app.modules.gasmancontiner.`data`.model.GasmanorderRowModel
import kotlin.Int
import kotlin.collections.List

class GasmanorderAdapter(
  var list: List<GasmanorderRowModel>
) : RecyclerView.Adapter<GasmanorderAdapter.RowGasmanorderVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowGasmanorderVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_gasmanorder,parent,false)
    return RowGasmanorderVH(view)
  }

  override fun onBindViewHolder(holder: RowGasmanorderVH, position: Int) {
    val gasmanorderRowModel = list[position]
    holder.binding.gasmanorderRowModel = gasmanorderRowModel
  }

  override fun getItemCount(): Int = list.size

  public fun updateData(newData: List<GasmanorderRowModel>) {
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
      item: GasmanorderRowModel
    ) {
    }
  }

  inner class RowGasmanorderVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowGasmanorderBinding = RowGasmanorderBinding.bind(itemView)
    init {
      binding.btnDetails.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, GasmanorderRowModel())
      }
    }
  }
}
