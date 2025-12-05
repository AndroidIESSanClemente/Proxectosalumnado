package com.example.spaceBinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spaceBinding.databinding.FragmentExplorationBinding
import com.example.spaceBinding.databinding.FragmentLendasBinding

class LendasFragment : Fragment() {

    private var _binding: FragmentLendasBinding?=null
    private val binding: FragmentLendasBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLendasBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}