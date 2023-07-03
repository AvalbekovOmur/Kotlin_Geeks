package com.example.kotlin_geeks.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin_geeks.data.local.Pref
import com.example.kotlin_geeks.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val pref: Pref by lazy {
        Pref(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveImage()
        saveName()
    }

    private fun saveName() {
        binding.editTextName.setText(pref.getName())
        binding.btnSave.setOnClickListener {
            pref.saveUserName(binding.editTextName.text.toString())
        }
    }

    private fun saveImage() {
        Glide.with(binding.ivImage).load(pref.getImage()).into(binding.ivImage)
        binding.ivImage.setOnClickListener {
            chooseImage.launch("image/*")
        }
    }

    private val chooseImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { img ->
            if (img != null) {
                pref.saveImage(img.toString())
                Glide.with(requireContext()).load(img)
                    .into(binding.ivImage)
            }
        }

}