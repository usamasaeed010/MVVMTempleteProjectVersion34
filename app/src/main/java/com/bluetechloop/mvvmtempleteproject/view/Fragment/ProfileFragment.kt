package com.example.zameedar.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluetechloop.mvvmtempleteproject.databinding.FragmentProfileBinding
import com.move.zoom.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class ProfileFragment : BaseFragment(){

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(requireActivity()), container, false)
        return binding.root
    }
}