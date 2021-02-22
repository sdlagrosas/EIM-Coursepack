package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit1Lesson3Binding


class Unit1Lesson3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit1Lesson3Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_lesson3, container, false
        )

        binding.quiz3Button.setOnClickListener { view : View ->
            view.findNavController()
                .navigate(Unit1Lesson3Directions.actionUnit1Lesson3ToUnit1Quiz3Fragment())
        }

        return binding.root
    }


}