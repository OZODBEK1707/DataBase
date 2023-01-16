package com.example.database.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.database.databinding.ItemRvBinding
import com.example.database.models.MyContact

class RvAdapter(val list: ArrayList<MyContact>, val rvAction: RvAction) : RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(myContact: MyContact, position: Int) {
            itemRvBinding.tvName.text = myContact.name
            itemRvBinding.tvNumber.text = myContact.number
            itemRvBinding.root.setOnClickListener {
                rvAction.menuClicked(myContact, position, itemRvBinding.imageMenu)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}

interface RvAction{
    fun menuClicked(myContact: MyContact, position: Int, imageView: ImageView)
}