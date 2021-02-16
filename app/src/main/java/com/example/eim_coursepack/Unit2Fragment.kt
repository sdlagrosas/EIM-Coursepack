package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit2Binding


class Unit2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit2Binding>(
            inflater,
            R.layout.fragment_unit2,
            container,
            false
        )
        binding.unit2Lesson1Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit2Fragment_to_unit2Lesson1)
        }
        binding.unit2Lesson2Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit2Fragment_to_unit2Lesson2)
        }
        binding.unit2Lesson3Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit2Fragment_to_unit2Lesson3)
        }
        binding.unit2Lesson4Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit2Fragment_to_unit2Lesson4)
        }

        return binding.root
    }

}