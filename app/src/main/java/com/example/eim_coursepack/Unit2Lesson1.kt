package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit2Lesson1Binding


class Unit2Lesson1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding : FragmentUnit2Lesson1Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit2_lesson1, container, false
        )

        binding.quiz21Button.setOnClickListener { view : View ->
            view.findNavController()
                .navigate(Unit2Lesson1Directions
                    .actionUnit2Lesson1ToUnit2Quiz1Fragment())
        }

        return binding.root
    }

}