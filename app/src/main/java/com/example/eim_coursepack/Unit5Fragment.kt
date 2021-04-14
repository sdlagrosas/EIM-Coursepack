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

    private val lessonFlags : List<String> = listOf(
        "Unit5Lesson1",
        "Unit5Lesson2",
        "Unit5Lesson3"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit5Binding>(inflater,R.layout.fragment_unit5,container,false)

        val lessonButtons : List<View> = listOf(
            binding.unit5Lesson1Button,
            binding.unit5Lesson2Button,
            binding.unit5Lesson3Button,
        )

        lessonButtons.forEach{ it ->
            it.setOnClickListener { handleClick(it) }
        }

        return binding.root
    }

    private fun handleClick(view : View) {
        when(view.id) {
            R.id.unit5Lesson1Button -> {
                view.findNavController().navigate(
                    Unit5FragmentDirections.actionUnit5FragmentToAllLessonFragment(lessonFlags[0])
                )
            }
            R.id.unit5Lesson2Button -> {
                view.findNavController().navigate(
                    Unit5FragmentDirections.actionUnit5FragmentToAllLessonFragment(lessonFlags[1])
                )
            }
            R.id.unit5Lesson3Button -> {
                view.findNavController().navigate(
                    Unit5FragmentDirections.actionUnit5FragmentToAllLessonFragment(lessonFlags[2])

                )
            }
        }
    }

}