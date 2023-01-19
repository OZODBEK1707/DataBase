package com.example.database.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.database.databinding.ItemRvBinding
import com.example.database.models.MyContact

class RvAdapter(val list: ArrayList<MyContact>, val rvAction: RvAction, val rvClick:RvClick) : RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(myContact: MyContact, position: Int) {
            itemRvBinding.tvName.text = myContact.name
            itemRvBinding.tvNumber.text = myContact.number
            itemRvBinding.root.setOnClickListener {
                rvAction.menuClicked(myContact, position, itemRvBinding.imageMenu)
            }

            itemRvBinding.tvNumber.setOnClickListener {
                rvClick.callAction(myContact, position)
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


interface RvAction {
    fun menuClicked(myContact: MyContact, position: Int, imageView: ImageView)
}

    interface RvClick {
        fun callAction(contact: MyContact, position: Int)

}
}