package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit6Binding


class Unit6Fragment : Fragment() {

    private val lessonFlags : List<String> = listOf(
        "Unit6Lesson1",
        "Unit6Lesson2",
        "Unit6Lesson3",
        "Unit6Lesson4"
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit6Binding>(inflater,R.layout.fragment_unit6,container,false)

        val lessonButtons : List<View> = listOf(
            binding.unit6Lesson1Button,
            binding.unit6Lesson2Button,
            binding.unit6Lesson3Button,
            binding.unit6Lesson4Button
            )

        lessonButtons.forEach{ it ->
            it.setOnClickListener { handleClick(it) }
        }

        return binding.root
    }

    private fun handleClick(view : View) {
        when(view.id) {
            R.id.unit6Lesson1Button -> {
                view.findNavController().navigate(
                    Unit6FragmentDirections.actionUnit6FragmentToAllLessonFragment(lessonFlags[0])
                )
            }
            R.id.unit6Lesson2Button -> {
                view.findNavController().navigate(
                    Unit6FragmentDirections.actionUnit6FragmentToAllLessonFragment(lessonFlags[1])
                )
            }
            R.id.unit6Lesson3Button -> {
                view.findNavController().navigate(
                    Unit6FragmentDirections.actionUnit6FragmentToAllLessonFragment(lessonFlags[2])
                )
            }
            R.id.unit6Lesson4Button -> {
                view.findNavController().navigate(
                    Unit6FragmentDirections.actionUnit6FragmentToAllLessonFragment(lessonFlags[3])
                )
            }
        }
    }


}