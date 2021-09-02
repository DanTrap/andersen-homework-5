package com.dantrap.homework_5

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dantrap.homework_5.databinding.ItemContactBinding

class ContactsAdapter(private val contactList: List<ContactInfo>) :
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

    class ViewHolder(private val itemBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(contact: ContactInfo) {
            with(itemBinding) {
                firstName.text = contact.firstName
                secondName.text = contact.secondName
                number.text = contact.number
                personImage.backgroundTintList = ColorStateList.valueOf(itemBinding.root.context.resources.getColor(contact.iconBackground))
            }
        }
    }

}