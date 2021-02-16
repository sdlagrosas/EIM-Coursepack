package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit6Binding


class Unit6 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit6Binding>(inflater,R.layout.fragment_unit6,container,false)
        binding.unit6Lesson1Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit6_to_unit6Lesson1)
        }
        binding.unit6Lesson2Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit6_to_unit6Lesson2)
        }
        binding.unit6Lesson3Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit6_to_unit6Lesson3)
        }
        binding.unit6Lesson4Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit6_to_unit6Lesson4)
        }
        return binding.root
    }


}