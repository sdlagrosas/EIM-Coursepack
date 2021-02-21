package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit1Lesson2Binding


class Unit1Lesson2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit1Lesson2Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_lesson2, container, false
        )

        binding.quiz2Button.setOnClickListener {  view : View ->
            view.findNavController().navigate(Unit1Lesson2Directions.actionUnit1Lesson2ToUnit1Quiz2Fragment())
        }

        return binding.root
    }

}