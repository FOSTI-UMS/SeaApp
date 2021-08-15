package com.fostiums.seaapp.ui.penjual.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fostiums.seaapp.R
import com.fostiums.seaapp.models.ProductData

class ProductPenjualAdapter(private val context: Context?, private val list: List<ProductData>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var name: TextView
        internal var harga: TextView

        init {
            name = itemView.findViewById(R.id.nama_product)
            harga = itemView.findViewById(R.id.harga_product)

        }

        internal fun bind(position: Int) {


            name.text = list?.get(position)?.nama.toString()

            harga.text = list?.get(position)?.harga.toString()


        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.product_penjual_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)

        holder.itemView.setOnClickListener{

        }

    }



    override fun getItemCount(): Int {
        var total:Int = 0
        if (list != null) {
            total = list.size
        }
        return total
    }




}
