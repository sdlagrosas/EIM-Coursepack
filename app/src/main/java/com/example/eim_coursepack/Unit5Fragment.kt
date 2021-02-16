package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit5Binding


class Unit5Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit5Binding>(inflater,R.layout.fragment_unit5,container,false)
        binding.unit5Lesson1Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit5Fragment_to_unit5Lesson1)
        }
        binding.unit5Lesson2Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit5Fragment_to_unit5Lesson2)
        }
        binding.unit5Lesson3Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit5Fragment_to_unit5Lesson3)
        }
        return binding.root
    }

}