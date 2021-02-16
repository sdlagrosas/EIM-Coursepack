package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit3Binding

class Unit3Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit3Binding>(inflater,R.layout.fragment_unit3,container,false)

        binding.unit3Lesson1Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit3Fragment_to_unit3Lesson1)
        }
        binding.unit3Lesson2Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit3Fragment_to_unit3Lesson2)
        }
        binding.unit3Lesson3Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit3Fragment_to_unit3Lesson3)
        }
        binding.unit3Lesson4Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit3Fragment_to_unit3Lesson4)
        }

        return binding.root
    }

}