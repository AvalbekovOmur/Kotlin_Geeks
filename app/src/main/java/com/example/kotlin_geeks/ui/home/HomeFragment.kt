package com.example.kotlin_geeks.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin_geeks.App
import com.example.kotlin_geeks.R
import com.example.kotlin_geeks.databinding.FragmentHomeBinding
import com.example.kotlin_geeks.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter by lazy { TaskAdapter(requireContext()) }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = App.db.taskDao().getAll()
        adapter.setTask(list)

        binding.fub.setOnClickListener { findNavController().navigate(R.id.taskFragment) }
        binding.recyclerView.adapter = adapter

    }

}
