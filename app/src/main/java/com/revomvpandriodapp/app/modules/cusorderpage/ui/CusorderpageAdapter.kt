package com.revomvpandriodapp.app.modules.cusorderpage.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowCusorderpageBinding
import com.revomvpandriodapp.app.modules.cusorderpage.`data`.model.CusorderpageRowModel
import kotlin.Int
import kotlin.collections.List

class CusorderpageAdapter(
  var list: List<CusorderpageRowModel>
) : RecyclerView.Adapter<CusorderpageAdapter.RowCusorderpageVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowCusorderpageVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_cusorderpage,parent,false)
    return RowCusorderpageVH(view)
  }

  override fun onBindViewHolder(holder: RowCusorderpageVH, position: Int) {
    val cusorderpageRowModel = CusorderpageRowModel()
    // TODO uncomment following line after integration with data source
    // val cusorderpageRowModel = list[position]
    holder.binding.cusorderpageRowModel = cusorderpageRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<CusorderpageRowModel>) {
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
      item: CusorderpageRowModel
    ) {
    }
  }

  inner class RowCusorderpageVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowCusorderpageBinding = RowCusorderpageBinding.bind(itemView)
  }
}
