package com.revomvpandriodapp.app.modules.gasmanorderpage.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowListordercodeBinding
import com.revomvpandriodapp.app.modules.gasmanorderpage.`data`.model.ListordercodeRowModel
import kotlin.Int
import kotlin.collections.List

class ListordercodeAdapter(
  var list: List<ListordercodeRowModel>
) : RecyclerView.Adapter<ListordercodeAdapter.RowListordercodeVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListordercodeVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_listordercode,parent,false)
    return RowListordercodeVH(view)
  }

  override fun onBindViewHolder(holder: RowListordercodeVH, position: Int) {
    val listordercodeRowModel = ListordercodeRowModel()
    // TODO uncomment following line after integration with data source
    // val listordercodeRowModel = list[position]
    holder.binding.listordercodeRowModel = listordercodeRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListordercodeRowModel>) {
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
      item: ListordercodeRowModel
    ) {
    }
  }

  inner class RowListordercodeVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListordercodeBinding = RowListordercodeBinding.bind(itemView)
  }
}
