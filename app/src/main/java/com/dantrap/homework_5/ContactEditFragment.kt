package com.dantrap.homework_5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.dantrap.homework_5.databinding.FragmentEditContactBinding

class ContactEditFragment : Fragment() {

    private var _binding: FragmentEditContactBinding? = null
    private val binding get() = _binding!!

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

        with (binding) {
            numberEdit.doAfterTextChanged {
                checkFields()
            }

            firstNameEdit.doAfterTextChanged {
                checkFields()
            }

            secondNameEdit.doAfterTextChanged {
                checkFields()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkFields() {
        with (binding) {
            saveButton.isEnabled = numberEdit.text.isNotEmpty() and firstNameEdit.text.isNotEmpty() and secondNameEdit.text.isNotEmpty()
        }
    }
}