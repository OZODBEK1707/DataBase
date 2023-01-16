package com.example.database.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.database.databinding.FragmentAddEditBinding
import com.example.database.db.MyDbHelper
import com.example.database.models.MyContact

class AddEditFragment : Fragment() {
    private var isEdit: Boolean = false
    private var myContact: MyContact? = null

    private val binding: FragmentAddEditBinding by lazy { FragmentAddEditBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        myDbHelper = MyDbHelper(binding.root.context)

        isEdit = arguments?.getBoolean("isEdit")!!
        if (isEdit){
            myContact = arguments?.getSerializable("keyMyContact") as MyContact
        }

        binding.apply {

            if (isEdit){
                edtName.setText(myContact?.name)
                edtNumber.setText(myContact?.number)
            }

            btnSave.setOnClickListener {
                if (isEdit){
                    myContact?.name = edtName.text.toString()
                    myContact?.number = edtNumber.text.toString()
                    myDbHelper.editContact(myContact!!)
                    Toast.makeText(context, "Edited", Toast.LENGTH_SHORT).show()
                }else{
                    val myContact = MyContact(
                        edtName.text.toString(),
                        edtNumber.text.toString()
                    )
                    myDbHelper.addContact(myContact)
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                }
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}