package com.example.kotlin_geeks.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kotlin_geeks.App
import com.example.kotlin_geeks.R
import com.example.kotlin_geeks.databinding.FragmentTaskBinding
import com.example.kotlin_geeks.model.Task
import com.example.kotlin_geeks.ui.home.HomeFragment

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null
    private lateinit var data: Task

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        task = arguments?.getSerializable(HomeFragment.TASK_KEY) as Task?
        fillEditTexts()
        buttonHandleClick()
    }

    private fun fillEditTexts() {
        if (task != null) {
            binding.EditTextTitle.setText(task!!.title)
            binding.EditTextDescription.setText(task!!.description)
            binding.BtnSave.text = getString(R.string.update)
        } else {
            binding.BtnSave.text = getString(R.string.save)
        }
    }

    private fun buttonHandleClick() {
        binding.BtnSave.setOnClickListener {
            data = Task(
                title = binding.EditTextTitle.text.toString(),
                description = binding.EditTextDescription.text.toString()
            )
            if (task != null) {
                updateTask()
            } else {
                saveTask()
            }
            findNavController().navigateUp()
        }
    }

    private fun saveTask() {
        task = Task(data.id, data.title, data.description)
        App.db.taskDao().insert(task!!)
    }

    private fun updateTask() {
        task!!.title = data.title
        task!!.description = data.description
        App.db.taskDao().update(task!!)
    }
}
