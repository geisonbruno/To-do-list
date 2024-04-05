package com.example.to_do.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.to_do.R
import com.example.to_do.database.TaskEntry
import com.example.to_do.databinding.FragmentAddBinding
import com.example.to_do.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddBinding.inflate(inflater)

        val myAdapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.priorities)
        )

        binding.apply {
            spinner.adapter = myAdapter
            binding.btnAdd.setOnClickListener {
                if (TextUtils.isEmpty((binding.edtTask.text))) {
                    Toast.makeText(requireContext(), "It's empty!", Toast.LENGTH_SHORT).show()
                } else {
                    val titleStr = binding.edtTask.text.toString()
                    val priority = binding.spinner.selectedItemPosition

                    val taskEntry = TaskEntry(
                        0,
                        titleStr,
                        priority,
                        System.currentTimeMillis()
                    )
                    viewModel.insert(taskEntry)
                    Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT)
                        .show()
                    val direction = AddFragmentDirections.actionAddFragmentToTaskFragment()
                    findNavController().navigate(direction)
                }
            }
        }

        return binding.root
    }
}