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
import com.example.eim_coursepack.databinding.FragmentUnit6Binding


class Unit6Fragment : Fragment() {

    private val lessonFlags : List<String> = listOf(
        "Unit6Lesson1",
        "Unit6Lesson2",
        "Unit6Lesson3",
        "Unit6Lesson4"
    )

    lateinit var unit6lessonScoreText : MutableList<String>
    private val scoreTexts : MutableList<String> = mutableListOf(
        "0 / 15",
        "0 / 15",
        "0 / 15",
        "0 / 15"
    )

    private var passingScore = 8
    private val enableLock = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit6Binding>(inflater,R.layout.fragment_unit6,container,false)

        binding.unit = this

        val lessonButtons : List<View> = listOf(
            binding.unit6Lesson1Button,
            binding.unit6Lesson2Button,
            binding.unit6Lesson3Button,
            binding.unit6Lesson4Button
            )

        // SharedPreference Object (for accessing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

        val unit6Lesson1Score = sharedPref?.getInt("unit6Quiz1Score", 0)!!
        val unit6Lesson1Items = sharedPref?.getInt("unit6Quiz1NumQuestions", 15)!!
        scoreTexts[2] = "$unit6Lesson1Score / $unit6Lesson1Items"

        unit6lessonScoreText = scoreTexts

        lessonButtons.forEach{ it ->
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

    private fun whatButtonsLocked(view : View, sharedPref : SharedPreferences?) {
        var unitQuizScore: Int

        when(view.id) {
            R.id.unit6Lesson1Button -> {
                view.findNavController().navigate(
                    Unit6FragmentDirections.actionUnit6FragmentToAllLessonFragment(lessonFlags[0])
                )
            }
            R.id.unit6Lesson2Button -> {
                unitQuizScore = sharedPref?.getInt("unit6Quiz1Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit6FragmentDirections
                            .actionUnit6FragmentToAllLessonFragment(lessonFlags[1]))
                } else { lockedMessage() }
            }
            R.id.unit6Lesson3Button -> {
                unitQuizScore = sharedPref?.getInt("unit6Quiz2Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit6FragmentDirections
                            .actionUnit6FragmentToAllLessonFragment(lessonFlags[2]))
                } else { lockedMessage() }
            }
            R.id.unit6Lesson4Button -> {
                unitQuizScore = sharedPref?.getInt("unit6Quiz3Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit6FragmentDirections
                            .actionUnit6FragmentToAllLessonFragment(lessonFlags[3]))
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