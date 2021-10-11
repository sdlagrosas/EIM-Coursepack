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
import com.example.eim_coursepack.databinding.FragmentUnit1Binding


class Unit1Fragment : Fragment() {

    private val lesson1flag = "Unit1Lesson1"
    private val lesson2flag = "Unit1Lesson2"
    private val lesson3flag = "Unit1Lesson3"

    lateinit var unit1LessonScoreText : MutableList<String>
    private val scoreTexts : MutableList<String> = mutableListOf(
        "0 / 15",
        "0 / 15",
        "0 / 15"
    )

    private val passingScore = 8
    private val enableLock = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentUnit1Binding>(inflater,R.layout.fragment_unit1,container,false)

        binding.unit = this

        val lesson1id= binding.unit1Lesson1Button
        val lesson2id= binding.unit1Lesson2Button
        val lesson3id= binding.unit1Lesson3Button

        // SharedPreference Object (for accessing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

        val unit1Lesson1Score = sharedPref?.getInt("unit1Quiz1Score", 0)!!
        val unit1Lesson1Items = sharedPref?.getInt("unit1Quiz1NumQuestions", 15)!!
        scoreTexts[0] = "$unit1Lesson1Score / $unit1Lesson1Items"

        val unit1Lesson2Score = sharedPref?.getInt("unit1Quiz2Score", 0)!!
        val unit1Lesson2Items = sharedPref?.getInt("unit1Quiz2NumQuestions", 15)!!
        scoreTexts[1] = "$unit1Lesson2Score / $unit1Lesson2Items"

        val unit1Lesson3Score = sharedPref?.getInt("unit1Quiz3Score", 0)!!
        val unit1Lesson3Items = sharedPref?.getInt("unit1Quiz3NumQuestions", 15)!!
        scoreTexts[2] = "$unit1Lesson3Score / $unit1Lesson3Items"

        unit1LessonScoreText = scoreTexts

        val clickableViews: List<View> = listOf(lesson1id,lesson2id,lesson3id)
        for (items in clickableViews) {
            if (enableLock) {
                items.setOnClickListener{whatButtonsLocked(it, sharedPref)}
            }
            else {
                items.setOnClickListener{whatButtons(it)}
            }
        }

        return binding.root
    }


    private fun whatButtons(view: View) {
        when (view.id) {
            R.id.unit1Lesson1Button -> {
                view.findNavController().navigate(
                    Unit1FragmentDirections.actionUnit1FragmentToAllLessonFragment(lesson1flag)
                )
            }
            R.id.unit1Lesson2Button -> {
                view.findNavController().navigate(
                    Unit1FragmentDirections.actionUnit1FragmentToAllLessonFragment(lesson2flag)
                )
            }
            R.id.unit1Lesson3Button -> {
                view.findNavController().navigate(
                    Unit1FragmentDirections.actionUnit1FragmentToAllLessonFragment(lesson3flag)
                )
            }
        }
    }

    private fun whatButtonsLocked(view : View, sharedPref: SharedPreferences?) {
        var unitQuizScore: Int
        when (view.id) {
            R.id.unit1Lesson1Button-> {
                view.findNavController().navigate(Unit1FragmentDirections.actionUnit1FragmentToAllLessonFragment(lesson1flag))
            }
            R.id.unit1Lesson2Button-> {
                unitQuizScore = sharedPref?.getInt("unit1Quiz1Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController().navigate(Unit1FragmentDirections.actionUnit1FragmentToAllLessonFragment(lesson2flag))
                } else { lockedMessage() }

            }
            R.id.unit1Lesson3Button-> {
                unitQuizScore = sharedPref?.getInt("unit1Quiz2Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController().navigate(Unit1FragmentDirections.actionUnit1FragmentToAllLessonFragment(lesson3flag))
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

