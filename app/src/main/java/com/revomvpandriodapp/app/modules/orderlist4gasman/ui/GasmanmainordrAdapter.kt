package com.revomvpandriodapp.app.modules.orderlist4gasman.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowGasmanmainordrBinding
import com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.model.GasmanmainordrRowModel
import kotlin.Int
import kotlin.collections.List

class GasmanmainordrAdapter(
  var list: List<GasmanmainordrRowModel>
) : RecyclerView.Adapter<GasmanmainordrAdapter.RowGasmanmainordrVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowGasmanmainordrVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_gasmanmainordr,parent,false)
    return RowGasmanmainordrVH(view)
  }

  override fun onBindViewHolder(holder: RowGasmanmainordrVH, position: Int) {
    val gasmanmainordrRowModel = GasmanmainordrRowModel()
    // TODO uncomment following line after integration with data source
    // val gasmanmainordrRowModel = list[position]
    holder.binding.gasmanmainordrRowModel = gasmanmainordrRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<GasmanmainordrRowModel>) {
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
      item: GasmanmainordrRowModel
    ) {
    }
  }

  inner class RowGasmanmainordrVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowGasmanmainordrBinding = RowGasmanmainordrBinding.bind(itemView)
    init {
      binding.btnDetails.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, GasmanmainordrRowModel())
      }
    }
  }
}
