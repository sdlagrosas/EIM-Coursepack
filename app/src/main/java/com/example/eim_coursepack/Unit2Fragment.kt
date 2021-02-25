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

    private val lessonFlags : List<String> = listOf(
        "Unit2Lesson1",
        "Unit2Lesson2",
        "Unit2Lesson3",
        "Unit2Lesson4"
    )

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

        val clickableViews : List<View> = listOf(
            binding.unit2Lesson1Button,
            binding.unit2Lesson2Button,
            binding.unit2Lesson3Button,
            binding.unit2Lesson4Button
        )

        clickableViews.forEach { it ->
            it.setOnClickListener { whatButtons(it) }
        }


        return binding.root
    }

    private fun whatButtons(view : View) {
        when (view.id) {
            R.id.unit2Lesson1Button -> {
                view.findNavController()
                    .navigate(Unit2FragmentDirections
                        .actionUnit2FragmentToAllLessonFragment(lessonFlags[0]))
            }
            R.id.unit2Lesson2Button -> {
                view.findNavController()
                    .navigate(Unit2FragmentDirections
                        .actionUnit2FragmentToAllLessonFragment(lessonFlags[1]))
            }
            R.id.unit2Lesson3Button -> {
                view.findNavController()
                    .navigate(Unit2FragmentDirections
                        .actionUnit2FragmentToAllLessonFragment(lessonFlags[2]))
            }
            R.id.unit2Lesson4Button -> {
                view.findNavController()
                    .navigate(Unit2FragmentDirections
                        .actionUnit2FragmentToAllLessonFragment(lessonFlags[3]))
            }

        }
    }

}