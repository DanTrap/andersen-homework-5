package com.dantrap.homework_5

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dantrap.homework_5.databinding.FragmentEditContactBinding

class ContactEditFragment : Fragment() {

    private var _binding: FragmentEditContactBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = requireArguments().getInt("position")
        val contact = viewModel.contactList.value!![position]

        setData(contact)

        with(binding) {

            numberEdit.doAfterTextChanged {
                checkFields()
            }

            firstNameEdit.doAfterTextChanged {
                checkFields()
            }

            secondNameEdit.doAfterTextChanged {
                checkFields()
            }

            saveButton.setOnClickListener {
                viewModel.editContact(
                    position,
                    ContactInfo(
                        binding.firstNameEdit.text.toString(),
                        binding.secondNameEdit.text.toString(),
                        binding.numberEdit.text.toString(),
                        contact.iconBackground
                    )
                )
                parentFragmentManager.popBackStack()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkFields() {
        with(binding) {
            saveButton.isEnabled =
                numberEdit.text.isNotEmpty() and firstNameEdit.text.isNotEmpty() and secondNameEdit.text.isNotEmpty()
        }
    }

    private fun setData(contact: ContactInfo) {
        with(binding) {
            firstNameEdit.setText(contact.firstName)
            secondNameEdit.setText(contact.secondName)
            numberEdit.setText(contact.number)
            personImage.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(contact.iconBackground))
        }
    }
}