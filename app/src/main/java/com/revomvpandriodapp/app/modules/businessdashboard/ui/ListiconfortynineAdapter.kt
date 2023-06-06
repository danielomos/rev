package com.revomvpandriodapp.app.modules.businessdashboard.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowListiconfortynineBinding
import com.revomvpandriodapp.app.modules.businessdashboard.`data`.model.ListiconfortynineRowModel
import kotlin.Int
import kotlin.collections.List

class ListiconfortynineAdapter(
  var list: List<ListiconfortynineRowModel>
) : RecyclerView.Adapter<ListiconfortynineAdapter.RowListiconfortynineVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListiconfortynineVH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_listiconfortynine,parent,false)
    return RowListiconfortynineVH(view)
  }

  override fun onBindViewHolder(holder: RowListiconfortynineVH, position: Int) {
    val listiconfortynineRowModel = ListiconfortynineRowModel()
    // TODO uncomment following line after integration with data source
    // val listiconfortynineRowModel = list[position]
    holder.binding.listiconfortynineRowModel = listiconfortynineRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListiconfortynineRowModel>) {
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
      item: ListiconfortynineRowModel
    ) {
    }
  }

  inner class RowListiconfortynineVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListiconfortynineBinding = RowListiconfortynineBinding.bind(itemView)
  }
}
