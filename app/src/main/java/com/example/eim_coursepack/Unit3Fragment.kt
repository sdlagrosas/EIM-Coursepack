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
import com.example.eim_coursepack.databinding.FragmentUnit3Binding

class Unit3Fragment : Fragment() {

    private val lessonFlags : List<String> = listOf(
        "Unit3Lesson1",
        "Unit3Lesson2",
        "Unit3Lesson3",
        "Unit3Lesson4"
    )

    lateinit var unit3LessonScoreText : MutableList<String>
    private val scoreTexts : MutableList<String> = mutableListOf(
        "0 / 15",
        "0 / 15",
        "0 / 15",
        "0 / 14"
    )

    private val passingScore = 8
    private val enableLock = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit3Binding>(inflater,R.layout.fragment_unit3,container,false)

        binding.unit = this

        val clickableViews : List<View> = listOf(
            binding.unit3Lesson1Button,
            binding.unit3Lesson2Button,
            binding.unit3Lesson3Button,
            binding.unit3Lesson4Button
        )

        // SharedPreference Object (for accessing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

        val unit3Lesson1Score = sharedPref?.getInt("unit3Quiz1Score", 0)!!
        val unit3Lesson1Items = sharedPref?.getInt("unit3Quiz1NumQuestions", 15)!!
        scoreTexts[0] = "$unit3Lesson1Score / $unit3Lesson1Items"

        val unit3Lesson2Score = sharedPref?.getInt("unit3Quiz2Score", 0)!!
        val unit3Lesson2Items = sharedPref?.getInt("unit3Quiz2NumQuestions", 15)!!
        scoreTexts[1] = "$unit3Lesson2Score / $unit3Lesson2Items"

        val unit3Lesson3Score = sharedPref?.getInt("unit3Quiz3Score", 0)!!
        val unit3Lesson3Items = sharedPref?.getInt("unit3Quiz3NumQuestions", 15)!!
        scoreTexts[2] = "$unit3Lesson3Score / $unit3Lesson3Items"

        val unit3Lesson4Score = sharedPref?.getInt("unit3Quiz4Score", 0)!!
        val unit3Lesson4Items = sharedPref?.getInt("unit3Quiz4NumQuestions", 14)!!
        scoreTexts[3] = "$unit3Lesson4Score / $unit3Lesson4Items"

        unit3LessonScoreText = scoreTexts

        clickableViews.forEach { it ->
            if (enableLock) {
                it.setOnClickListener { whatButtonsLocked(it, sharedPref) }
            }
            else{
                it.setOnClickListener { whatButtons(it) }
            }
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

    private fun whatButtonsLocked(view : View, sharedPref : SharedPreferences?) {
        var unitQuizScore: Int

        when (view.id) {
            R.id.unit3Lesson1Button -> {

                view.findNavController()
                    .navigate(Unit3FragmentDirections
                        .actionUnit3FragmentToAllLessonFragment(lessonFlags[0]))
            }
            R.id.unit3Lesson2Button -> {
                unitQuizScore = sharedPref?.getInt("unit3Quiz1Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit3FragmentDirections
                            .actionUnit3FragmentToAllLessonFragment(lessonFlags[1]))
                } else { lockedMessage() }
            }
            R.id.unit3Lesson3Button -> {
                unitQuizScore = sharedPref?.getInt("unit3Quiz2Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit3FragmentDirections
                            .actionUnit3FragmentToAllLessonFragment(lessonFlags[2]))
                } else { lockedMessage() }
            }
            R.id.unit3Lesson4Button -> {
                unitQuizScore = sharedPref?.getInt("unit3Quiz3Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit3FragmentDirections
                            .actionUnit3FragmentToAllLessonFragment(lessonFlags[3]))
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