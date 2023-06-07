package com.revomvpandriodapp.app.modules.householddashboardhome.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.databinding.RowCustomersorderBinding
import com.revomvpandriodapp.app.modules.householddashboardhome.`data`.model.CustomersorderRowModel
import kotlin.Int
import kotlin.collections.List

class CustomersorderAdapter(
  var list: List<CustomersorderRowModel>
) : RecyclerView.Adapter<CustomersorderAdapter.RowCustomersorderVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowCustomersorderVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_customersorder,parent,false)
    return RowCustomersorderVH(view)
  }

  override fun onBindViewHolder(holder: RowCustomersorderVH, position: Int) {
    val customersorderRowModel = list[position]
    holder.binding.customersorderRowModel = customersorderRowModel
  }

  override fun getItemCount(): Int = list.size

  public fun updateData(newData: List<CustomersorderRowModel>) {
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
      item: CustomersorderRowModel
    ) {
    }
  }

  inner class RowCustomersorderVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowCustomersorderBinding = RowCustomersorderBinding.bind(itemView)
    init {
      binding.btnDetails.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, CustomersorderRowModel())
      }
    }
  }
}
