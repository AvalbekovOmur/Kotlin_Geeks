package com.example.kotlin_geeks.ui.auth.accept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_geeks.R
import com.example.kotlin_geeks.databinding.FragmentAcceptBinding

class AcceptFragment : Fragment() {
    private lateinit var binding: FragmentAcceptBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}