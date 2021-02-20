package com.example.eim_coursepack

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit1Lesson1Binding


class Unit1Lesson1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit1Lesson1Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_lesson1, container, false)

        binding.quizButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit1Lesson1_to_unit1Quiz1MCFragment)
        }

        return binding.root
    }

}