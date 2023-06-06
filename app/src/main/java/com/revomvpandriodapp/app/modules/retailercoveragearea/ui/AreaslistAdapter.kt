package com.revomvpandriodapp.app.modules.retailercoveragearea.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowAreaslistBinding
import com.revomvpandriodapp.app.modules.retailercoveragearea.`data`.model.AreaslistRowModel
import kotlin.Int
import kotlin.collections.List

class AreaslistAdapter(
  var list: List<AreaslistRowModel>
) : RecyclerView.Adapter<AreaslistAdapter.RowAreaslistVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowAreaslistVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_areaslist,parent,false)
    return RowAreaslistVH(view)
  }

  override fun onBindViewHolder(holder: RowAreaslistVH, position: Int) {
    val areaslistRowModel = list[position]
    holder.binding.areaslistRowModel = areaslistRowModel
  }

  override fun getItemCount(): Int = list.size

  public fun updateData(newData: List<AreaslistRowModel>) {
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
      item: AreaslistRowModel
    ) {
    }
  }

  inner class RowAreaslistVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowAreaslistBinding = RowAreaslistBinding.bind(itemView)
    init {
      binding.txtLanguage.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, AreaslistRowModel())
      }
    }
  }
}
