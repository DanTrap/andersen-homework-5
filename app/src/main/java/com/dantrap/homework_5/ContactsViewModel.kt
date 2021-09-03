package com.dantrap.homework_5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dantrap.homework_5.data.ContactInfo

class ContactsViewModel : ViewModel() {

    private val _contactLists: MutableLiveData<MutableList<ContactInfo>> =
        MutableLiveData(ContactInfo.contactList.toMutableList())

    val contactList: LiveData<MutableList<ContactInfo>> = _contactLists

    var updatedPosition: Int = 0

    fun editContact(position: Int, contact: ContactInfo) {
        updatedPosition = position
        _contactLists.value?.set(position, contact)
    }

}