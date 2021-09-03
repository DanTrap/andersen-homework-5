package com.dantrap.homework_5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dantrap.homework_5.databinding.FragmentContactsBinding

class ContactsFragment : Fragment(), ContactsAdapter.OnContactClickListener {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    private val contactList = listOf(
        ContactInfo("Aleksandr", "Pushkin", "+79000000001", R.color.purple_500),
        ContactInfo("Aleksandr", "Pushkin", "+79000000002", R.color.teal_200),
        ContactInfo("Aleksandr", "Pushkin", "+79000000003", R.color.orange),
        ContactInfo("Aleksandr", "Pushkin", "+79000000004", R.color.nuclear_waste)
    )

    private val contactAdapter by lazy { ContactsAdapter(contactList, this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)
        }
    }

    override fun onClickItem(position: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ContactEditFragment())
            .addToBackStack(null)
            .commit()
    }
}