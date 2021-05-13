package com.example.eim_coursepack

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private val passingScore = 8
    private val enableLock = false

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

        // SharedPreference Object (for accessing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

        clickableViews.forEach { it ->
            if (enableLock) {
                it.setOnClickListener { whatButtonsLocked(it, sharedPref) }
            }
            else {
                it.setOnClickListener { whatButtons(it) }
            }
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

    private fun whatButtonsLocked(view : View, sharedPref: SharedPreferences?) {
        var unitQuizScore: Int

        when (view.id) {
            R.id.unit2Lesson1Button -> {
                view.findNavController()
                    .navigate(Unit2FragmentDirections
                        .actionUnit2FragmentToAllLessonFragment(lessonFlags[0]))

            }
            R.id.unit2Lesson2Button -> {
                unitQuizScore = sharedPref?.getInt("unit2Quiz1Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit2FragmentDirections
                            .actionUnit2FragmentToAllLessonFragment(lessonFlags[1]))
                } else { lockedMessage() }
            }
            R.id.unit2Lesson3Button -> {
                unitQuizScore = sharedPref?.getInt("unit2Quiz2Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit2FragmentDirections
                            .actionUnit2FragmentToAllLessonFragment(lessonFlags[2]))
                } else { lockedMessage() }
            }
            R.id.unit2Lesson4Button -> {
                unitQuizScore = sharedPref?.getInt("unit2Quiz3Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit2FragmentDirections
                            .actionUnit2FragmentToAllLessonFragment(lessonFlags[3]))
                } else { lockedMessage() }
            }

        }
    }

    private fun lockedMessage() {
        Toast.makeText(context,
            "Reach a passing score (8+/15) on the previous lesson's quiz to proceed.",
            Toast.LENGTH_LONG).show()
    }

}