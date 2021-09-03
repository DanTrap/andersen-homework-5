package com.dantrap.homework_5.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dantrap.homework_5.R
import com.dantrap.homework_5.databinding.FragmentContactsBinding
import com.dantrap.homework_5.ui.adapter.ContactsAdapter
import com.dantrap.homework_5.ContactsViewModel

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

        hideSoftKeyboard(requireActivity())

        setupRecyclerView()

        viewModel.contactList.observe(viewLifecycleOwner, { data ->
            contactAdapter.updateData(viewModel.updatedPosition, data)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickItem(position: Int) {
        val destination = ContactEditFragment()
        destination.arguments = bundleOf("position" to position)
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.from_right,
                R.anim.to_left,
                R.anim.from_left,
                R.anim.to_right
            )
            .replace(R.id.fragment_container_view, destination)
            .addToBackStack(null)
            .commit()
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

    private fun hideSoftKeyboard(activity: Activity) {
        if (activity.currentFocus == null) {
            return
        }
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

}