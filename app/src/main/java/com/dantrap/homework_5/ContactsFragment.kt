package com.dantrap.homework_5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dantrap.homework_5.databinding.FragmentContactsBinding

class ContactsFragment : Fragment(), ContactsAdapter.OnContactClickListener {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactsViewModel by activityViewModels()

    private val contactAdapter by lazy { ContactsAdapter(viewModel.contactList.value!!, this) }

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

        viewModel.contactList.observe(viewLifecycleOwner, { data ->
            contactAdapter.updateData(0, data)
        })
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
        val destination = ContactEditFragment()
        destination.arguments = bundleOf("position" to position)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, destination)
            .addToBackStack(null)
            .commit()
    }
}