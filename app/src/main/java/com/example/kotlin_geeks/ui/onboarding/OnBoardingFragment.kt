package com.example.kotlin_geeks.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kotlin_geeks.R
import com.example.kotlin_geeks.data.local.Pref
import com.example.kotlin_geeks.databinding.FragmentOnBoardingBinding
import com.example.kotlin_geeks.ui.onboarding.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator3


class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private val adapter = OnBoardingAdapter(this :: onClick)
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)

    }
    private  fun onClick(){
        pref.saveSeen()
        findNavController().navigateUp()
    }

}