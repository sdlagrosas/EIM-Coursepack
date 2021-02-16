package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit4Binding

class Unit4Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit4Binding>(inflater,R.layout.fragment_unit4,container,false)
        binding.unit4Lesson1Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit4Fragment_to_unit4Lesson1)
        }
        binding.unit4Lesson2Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit4Fragment_to_unit4Lesson2)
        }
        binding.unit4Lesson3Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit4Fragment_to_unit4Lesson3)
        }
        return binding.root
    }


}