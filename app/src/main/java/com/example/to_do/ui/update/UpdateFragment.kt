package com.example.to_do.ui.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.to_do.R
import com.example.to_do.database.TaskEntry
import com.example.to_do.databinding.FragmentUpdateBinding
import com.example.to_do.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUpdateBinding.inflate(inflater)

        val args = UpdateFragmentArgs.fromBundle(requireArguments())

        binding.apply {
            updateEdtTask.setText(args.taskEntry.title)
            updateSpinner.setSelection(args.taskEntry.priority)

            binding.btnUpdate.setOnClickListener {
                if (TextUtils.isEmpty(binding.updateEdtTask.text)) {
                    Toast.makeText(requireContext(), "Adicione uma tarefe!", Toast.LENGTH_SHORT).show()
                } else {
                    val taskStr = binding.updateEdtTask.text.toString()
                    val priority = binding.updateSpinner.selectedItemPosition

                    val taskEntry = TaskEntry(
                        args.taskEntry.id,
                        taskStr,
                        priority,
                        args.taskEntry.timestamp
                    )
                    viewModel.update(taskEntry)
                    Toast.makeText(requireContext(), "Updated!", Toast.LENGTH_SHORT).show()

                    val direction = UpdateFragmentDirections.actionUpdateFragmentToTaskFragment()
                    findNavController().navigate(direction)
                }
            }
        }

        return binding.root
    }

}