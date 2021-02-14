package com.example.eim_coursepack

import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
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

        val sharedPref = this.activity?.getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE)

        val score = sharedPref?.getInt("quiz1_score",0)

        Toast.makeText(context, "Quiz1 Score : $score",Toast.LENGTH_LONG).show()

        binding.quiz1Button.setOnClickListener { view : View ->
            view.findNavController().navigate(Unit1Lesson1Directions.actionUnit1Lesson1ToUnit1Quiz1MCFragment())
        }

        return binding.root
    }

}