package com.example.database.db

import com.example.database.models.MyContact

interface MyDbHelperInterface {

    fun addContact(myContact: MyContact)
    fun getAllContacts():List<MyContact>
    fun deleteContact(myContact: MyContact)
    fun editContact(myContact: MyContact):Int
}