package com.revomvpandriodapp.app.modules.gasmancontiner.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowListsellingPriceBinding
import com.revomvpandriodapp.app.modules.gasmancontiner.`data`.model.ListsellingPriceRowModel
import kotlin.Int
import kotlin.collections.List

class ListsellingPriceAdapter(
  var list: List<ListsellingPriceRowModel>
) : RecyclerView.Adapter<ListsellingPriceAdapter.RowListsellingPriceVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListsellingPriceVH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_listselling_price,parent,false)
    return RowListsellingPriceVH(view)
  }

  override fun onBindViewHolder(holder: RowListsellingPriceVH, position: Int) {
    val listsellingPriceRowModel = ListsellingPriceRowModel()
    // TODO uncomment following line after integration with data source
    // val listsellingPriceRowModel = list[position]
    holder.binding.listsellingPriceRowModel = listsellingPriceRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListsellingPriceRowModel>) {
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
      item: ListsellingPriceRowModel
    ) {
    }
  }

  inner class RowListsellingPriceVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListsellingPriceBinding = RowListsellingPriceBinding.bind(itemView)
    init {
      binding.btnSellingPrice.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, ListsellingPriceRowModel())
      }
    }
  }
}
