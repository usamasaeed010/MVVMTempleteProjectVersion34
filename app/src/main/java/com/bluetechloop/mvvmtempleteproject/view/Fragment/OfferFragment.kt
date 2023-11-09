package com.example.zameedar.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluetechloop.mvvmtempleteproject.databinding.FragmentOfferBinding
import com.move.zoom.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OfferFragment : BaseFragment() {

    lateinit var binding: FragmentOfferBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentOfferBinding.inflate(LayoutInflater.from(requireActivity()), container, false)
        return binding.root
    }
}