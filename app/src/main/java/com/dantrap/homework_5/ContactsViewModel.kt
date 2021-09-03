package com.dantrap.homework_5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactsViewModel : ViewModel() {

    private val _contactLists: MutableLiveData<MutableList<ContactInfo>> =
        MutableLiveData(ContactInfo.contactList.toMutableList())

    val contactList: LiveData<MutableList<ContactInfo>> = _contactLists

    fun editContact(position: Int, contact: ContactInfo) {
        _contactLists.value?.set(position, contact)
    }

}