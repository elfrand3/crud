package com.frand.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.frand.crud.R
import com.frand.crud.model.ProductsItem


class ProductsAdapter (private var listData:  ArrayList<ProductsItem>):
    RecyclerView.Adapter<ProductsAdapter.myviewHolder>() {

    class myviewHolder (itemView: View ) : RecyclerView.ViewHolder(itemView){
        val id : TextView = itemView.findViewById(R.id.iv_id)
        val tl : TextView = itemView.findViewById(R.id.iv_title)
        val ds : TextView = itemView.findViewById(R.id.iv_description)
        val pr : TextView = itemView.findViewById(R.id.iv_price)
        val dc : TextView = itemView.findViewById(R.id.iv_discountPercentage)
        val rt : TextView = itemView.findViewById(R.id.iv_rating)
        val st : TextView = itemView.findViewById(R.id.iv_stock)
        val br : TextView = itemView.findViewById(R.id.iv_brand)
        val ct : TextView = itemView.findViewById(R.id.iv_category)
        val th : ImageView = itemView.findViewById(R.id.iv_thumbnail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_data_products,parent,false)
        return myviewHolder(view)


    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {
        val data = listData[position]
        holder.id.text = data.id.toString()
        holder.tl.text = data.title
        holder.ds.text = data.description
        holder.pr.text = data.price.toString()
        holder.dc.text = data.discountPercentage.toString()
        holder.rt.text = data.rating.toString()
        holder.st.text = data.stock.toString()
        holder.br.text = data.brand
        holder.ct.text = data.category
        Glide.with(holder.itemView)
            .load("${data.thumbnail}")
            .apply (RequestOptions.overrideOf(150,150)).into(holder.th)
    }

    override fun getItemCount() = listData.size

    fun getProducts(data: List<ProductsItem>){
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }
}