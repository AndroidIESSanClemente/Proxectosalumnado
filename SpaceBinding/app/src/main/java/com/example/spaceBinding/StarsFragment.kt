package com.example.spaceBinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.spaceBinding.databinding.FragmentStarsBinding

class StarsFragment : Fragment() {

    private var _binding: FragmentStarsBinding? = null
    private val binding: FragmentStarsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStarsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.buttonLendas.setOnClickListener {
                findNavController().navigate(R.id.action_starsFragment_to_lendasFragment)
            }
            binding.buttonObservacion.setOnClickListener {
                findNavController().navigate(R.id.action_starsFragment_to_observacionFragment)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}