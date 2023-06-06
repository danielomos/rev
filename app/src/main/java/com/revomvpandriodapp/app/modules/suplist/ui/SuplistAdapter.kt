package com.revomvpandriodapp.app.modules.suplist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowSuplistBinding
import com.revomvpandriodapp.app.modules.suplist.`data`.model.SuplistRowModel
import kotlin.Int
import kotlin.collections.List

class SuplistAdapter(
  var list: List<SuplistRowModel>
) : RecyclerView.Adapter<SuplistAdapter.RowSuplistVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowSuplistVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_suplist,parent,false)
    return RowSuplistVH(view)
  }

  override fun onBindViewHolder(holder: RowSuplistVH, position: Int) {
    val suplistRowModel = list[position]
    holder.binding.suplistRowModel = suplistRowModel
  }

  override fun getItemCount(): Int = list.size

  public fun updateData(newData: List<SuplistRowModel>) {
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
      item: SuplistRowModel
    ) {
    }
  }

  inner class RowSuplistVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowSuplistBinding = RowSuplistBinding.bind(itemView)
    init {
      binding.btnShopsDetails.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, SuplistRowModel())
      }
    }
  }
}
