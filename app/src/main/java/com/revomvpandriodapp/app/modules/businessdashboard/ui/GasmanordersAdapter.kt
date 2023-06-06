package com.revomvpandriodapp.app.modules.businessdashboard.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowGasmanordersBinding
import com.revomvpandriodapp.app.modules.businessdashboard.`data`.model.GasmanordersRowModel
import kotlin.Int
import kotlin.collections.List

class GasmanordersAdapter(
  var list: List<GasmanordersRowModel>
) : RecyclerView.Adapter<GasmanordersAdapter.RowGasmanordersVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowGasmanordersVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_gasmanorders,parent,false)
    return RowGasmanordersVH(view)
  }

  override fun onBindViewHolder(holder: RowGasmanordersVH, position: Int) {
    val gasmanordersRowModel = GasmanordersRowModel()
    // TODO uncomment following line after integration with data source
    // val gasmanordersRowModel = list[position]
    holder.binding.gasmanordersRowModel = gasmanordersRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<GasmanordersRowModel>) {
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
      item: GasmanordersRowModel
    ) {
    }
  }

  inner class RowGasmanordersVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowGasmanordersBinding = RowGasmanordersBinding.bind(itemView)
    init {
      binding.btnDetailsOne.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, GasmanordersRowModel())
      }
    }
  }
}
