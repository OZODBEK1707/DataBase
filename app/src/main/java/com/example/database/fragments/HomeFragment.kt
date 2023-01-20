package com.example.database.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceFragment.OnPreferenceStartFragmentCallback
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.database.R
import com.example.database.adapters.RvAdapter
import com.example.database.databinding.FragmentHomeBinding
import com.example.database.db.MyDbHelper
import com.example.database.models.MyContact

class HomeFragment : Fragment(), RvAdapter.RvAction, RvAdapter.RvClick {
    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var rvAdapter: RvAdapter
    private lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setHasOptionsMenu(true)
        myDbHelper = MyDbHelper(binding.root.context)
        rvAdapter = RvAdapter(myDbHelper.getAllContacts() as ArrayList, this, this)
        binding.rv.adapter = rvAdapter


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.my_option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.addEditFragment, bundleOf("isEdit" to false))
        return super.onOptionsItemSelected(item)
    }

    override fun menuClicked(myContact: MyContact, position: Int, imageView: ImageView) {
        val popupMenu = PopupMenu(context, imageView)
        popupMenu.inflate(R.menu.menu_popup_item)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_delete->{
                    Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show()
                    myDbHelper.deleteContact(myContact)
                    rvAdapter.list.remove(myContact)
                    rvAdapter.notifyItemRemoved(position)
                }
                R.id.menu_edit->{
                    Toast.makeText(context, "edit your contact", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.addEditFragment, bundleOf("isEdit" to true, "keyMyContact" to myContact))
                }
            }
            true
        }

        popupMenu.show()
    }

    override fun callAction(contact: MyContact, position: Int) {
        val call = Intent(Intent.ACTION_DIAL)
        call.data = Uri.parse("tel:" +"Number_to_call")
        startActivity(call)
    }

}