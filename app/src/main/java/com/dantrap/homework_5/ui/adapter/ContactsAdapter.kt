package com.dantrap.homework_5.ui.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dantrap.homework_5.data.ContactInfo
import com.dantrap.homework_5.databinding.ItemContactBinding

class ContactsAdapter(
    private var contactList: MutableList<ContactInfo>,
    private val listener: OnContactClickListener
) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size

    fun updateData(position: Int, data: MutableList<ContactInfo>?) {
        contactList = data!!
        notifyItemChanged(position)
    }

    inner class ViewHolder(private val itemBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(contact: ContactInfo) {
            with(itemBinding) {
                firstName.text = contact.firstName
                secondName.text = contact.secondName
                number.text = contact.number
                personImage.backgroundTintList =
                    ColorStateList.valueOf(itemBinding.root.context.resources.getColor(contact.iconBackground))
            }
        }

        override fun onClick(p0: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onClickItem(adapterPosition)
            }
        }

    }

    interface OnContactClickListener {
        fun onClickItem(position: Int)
    }

}