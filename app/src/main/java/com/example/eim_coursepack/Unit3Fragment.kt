package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit3Binding

class Unit3Fragment : Fragment() {

    private val lessonFlags : List<String> = listOf(
        "Unit3Lesson1",
        "Unit3Lesson2",
        "Unit3Lesson3",
        "Unit3Lesson4"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit3Binding>(inflater,R.layout.fragment_unit3,container,false)
        val clickableViews : List<View> = listOf(
            binding.unit3Lesson1Button,
            binding.unit3Lesson2Button,
            binding.unit3Lesson3Button,
            binding.unit3Lesson4Button
        )

        clickableViews.forEach { it ->
            it.setOnClickListener { whatButtons(it) }
        }

        return binding.root
    }

    private fun whatButtons(view : View) {
        when (view.id) {
            R.id.unit3Lesson1Button -> {
                view.findNavController()
                    .navigate(Unit3FragmentDirections
                        .actionUnit3FragmentToAllLessonFragment(lessonFlags[0]))
            }
            R.id.unit3Lesson2Button -> {
                view.findNavController()
                    .navigate(Unit3FragmentDirections
                        .actionUnit3FragmentToAllLessonFragment(lessonFlags[1]))
            }
            R.id.unit3Lesson3Button -> {
                view.findNavController()
                    .navigate(Unit3FragmentDirections
                        .actionUnit3FragmentToAllLessonFragment(lessonFlags[2]))
            }
            R.id.unit3Lesson4Button -> {
                view.findNavController()
                    .navigate(Unit3FragmentDirections
                        .actionUnit3FragmentToAllLessonFragment(lessonFlags[3]))
            }

        }
    }


}