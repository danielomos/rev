package com.revomvpandriodapp.app.modules.gasmandashb.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowListcalculatorBinding
import com.revomvpandriodapp.app.modules.gasmandashb.`data`.model.ListcalculatorRowModel
import kotlin.Int
import kotlin.collections.List

class ListcalculatorAdapter(
  var list: List<ListcalculatorRowModel>
) : RecyclerView.Adapter<ListcalculatorAdapter.RowListcalculatorVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListcalculatorVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_listcalculator,parent,false)
    return RowListcalculatorVH(view)
  }

  override fun onBindViewHolder(holder: RowListcalculatorVH, position: Int) {
    val listcalculatorRowModel = ListcalculatorRowModel()
    // TODO uncomment following line after integration with data source
    // val listcalculatorRowModel = list[position]
    holder.binding.listcalculatorRowModel = listcalculatorRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListcalculatorRowModel>) {
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
      item: ListcalculatorRowModel
    ) {
    }
  }

  inner class RowListcalculatorVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListcalculatorBinding = RowListcalculatorBinding.bind(itemView)
    init {
      binding.linearPaybill.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, ListcalculatorRowModel())
      }
    }
  }
}
