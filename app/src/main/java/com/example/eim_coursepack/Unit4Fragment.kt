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

    private val lessonFlags : List<String> = listOf(
        "Unit4Lesson1",
        "Unit4Lesson2",
        "Unit4Lesson3"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit4Binding>(inflater,R.layout.fragment_unit4,container,false)

        val lessonButtons : List<View> = listOf(
            binding.unit4Lesson1Button,
            binding.unit4Lesson2Button,
            binding.unit4Lesson3Button
        )

        lessonButtons.forEach{ it ->
            it.setOnClickListener { handleClick(it) }
        }

        return binding.root
    }

    private fun handleClick(view: View) {
        when(view.id) {
            R.id.unit4Lesson1Button -> {
                view.findNavController()
                    .navigate(Unit4FragmentDirections
                        .actionUnit4FragmentToAllLessonFragment(lessonFlags[0]))
            }
            R.id.unit4Lesson2Button -> {
                view.findNavController()
                    .navigate(Unit4FragmentDirections
                        .actionUnit4FragmentToAllLessonFragment(lessonFlags[1]))
            }
            R.id.unit4Lesson3Button -> {
                view.findNavController()
                    .navigate(Unit4FragmentDirections
                        .actionUnit4FragmentToAllLessonFragment(lessonFlags[2]))
            }
        }
    }


}