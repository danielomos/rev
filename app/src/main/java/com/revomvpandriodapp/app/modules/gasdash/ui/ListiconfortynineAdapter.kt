package com.revomvpandriodapp.app.modules.gasdash.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowListiconfortynine2Binding
import com.revomvpandriodapp.app.modules.gasdash.`data`.model.Listiconfortynine2RowModel
import kotlin.Int
import kotlin.collections.List

class ListiconfortynineAdapter(
  var list: List<Listiconfortynine2RowModel>
) : RecyclerView.Adapter<ListiconfortynineAdapter.RowListiconfortynine2VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListiconfortynine2VH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_listiconfortynine2,parent,false)
    return RowListiconfortynine2VH(view)
  }

  override fun onBindViewHolder(holder: RowListiconfortynine2VH, position: Int) {
    val listiconfortynine2RowModel = Listiconfortynine2RowModel()
    // TODO uncomment following line after integration with data source
    // val listiconfortynine2RowModel = list[position]
    holder.binding.listiconfortynine2RowModel = listiconfortynine2RowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Listiconfortynine2RowModel>) {
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
      item: Listiconfortynine2RowModel
    ) {
    }
  }

  inner class RowListiconfortynine2VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListiconfortynine2Binding = RowListiconfortynine2Binding.bind(itemView)
    init {
      binding.btnDetails.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, Listiconfortynine2RowModel())
      }
    }
  }
}
