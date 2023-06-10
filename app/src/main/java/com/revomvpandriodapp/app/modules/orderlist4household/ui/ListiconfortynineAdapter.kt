package com.revomvpandriodapp.app.modules.orderlist4household.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowListiconfortynine1Binding
import com.revomvpandriodapp.app.modules.orderlist4household.`data`.model.Listiconfortynine1RowModel
import kotlin.Int
import kotlin.collections.List

class ListiconfortynineAdapter(
  var list: List<Listiconfortynine1RowModel>
) : RecyclerView.Adapter<ListiconfortynineAdapter.RowListiconfortynine1VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListiconfortynine1VH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_listiconfortynine1,parent,false)
    return RowListiconfortynine1VH(view)
  }

  override fun onBindViewHolder(holder: RowListiconfortynine1VH, position: Int) {
    val listiconfortynine1RowModel = list[position]
    holder.binding.listiconfortynine1RowModel = listiconfortynine1RowModel
  }

  override fun getItemCount(): Int = list.size

  public fun updateData(newData: List<Listiconfortynine1RowModel>) {
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
      item: Listiconfortynine1RowModel
    ) {
    }
  }

  inner class RowListiconfortynine1VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListiconfortynine1Binding = RowListiconfortynine1Binding.bind(itemView)
    init {
      binding.btnDetails.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, Listiconfortynine1RowModel())
      }
    }
  }
}
