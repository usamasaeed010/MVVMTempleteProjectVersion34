package com.example.zameedar.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluetechloop.mvvmtempleteproject.databinding.FragmentMoreBinding
import com.move.zoom.ui.common.BaseFragment


class MoreFragment : BaseFragment() {

    lateinit var binding: FragmentMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentMoreBinding.inflate(LayoutInflater.from(requireActivity()), container, false)
        return binding.root
    }

}