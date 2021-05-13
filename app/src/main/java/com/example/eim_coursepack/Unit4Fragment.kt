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
import com.example.eim_coursepack.databinding.FragmentUnit4Binding

class Unit4Fragment : Fragment() {
    private val lessonFlag : List<String> = listOf(
        "Unit4Lesson1",
        "Unit4Lesson2",
        "Unit4Lesson3",
    )

    private var passingScore = 8
    private val enableLock = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit4Binding>(inflater,R.layout.fragment_unit4,container,false)
        val clickableViews : List<View> = listOf(
            binding.unit4Lesson1Button,
            binding.unit4Lesson2Button,
            binding.unit4Lesson3Button,

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
            R.id.unit4Lesson1Button -> {
                view.findNavController()
                    .navigate(Unit4FragmentDirections
                        .actionUnit4FragmentToAllLessonFragment(lessonFlag[0]))
            }
            R.id.unit4Lesson2Button -> {
                view.findNavController()
                    .navigate(Unit4FragmentDirections
                        .actionUnit4FragmentToAllLessonFragment(lessonFlag[1]))
            }
            R.id.unit4Lesson3Button -> {
                view.findNavController()
                    .navigate(Unit4FragmentDirections
                        .actionUnit4FragmentToAllLessonFragment(lessonFlag[2]))
            }

        }
    }

    private fun whatButtonsLocked(view : View, sharedPref: SharedPreferences?) {
        var unitQuizScore: Int

        when (view.id) {
            R.id.unit4Lesson1Button -> {
                view.findNavController()
                    .navigate(Unit4FragmentDirections
                        .actionUnit4FragmentToAllLessonFragment(lessonFlag[0]))
            }
            R.id.unit4Lesson2Button -> {
                unitQuizScore = sharedPref?.getInt("unit4Quiz1Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit4FragmentDirections
                            .actionUnit4FragmentToAllLessonFragment(lessonFlag[1]))
                } else { lockedMessage() }
            }
            R.id.unit4Lesson3Button -> {
                unitQuizScore = sharedPref?.getInt("unit4Quiz2Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit4FragmentDirections
                            .actionUnit4FragmentToAllLessonFragment(lessonFlag[2]))
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