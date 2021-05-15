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
import com.example.eim_coursepack.databinding.FragmentUnit5Binding


class Unit5Fragment : Fragment() {

    private val lessonFlag : List<String> = listOf(
        "Unit5Lesson1",
        "Unit5Lesson2",
        "Unit5Lesson3"
    )

    lateinit var unit5LessonScoreText : MutableList<String>
    private val scoreTexts : MutableList<String> = mutableListOf(
        "0 / 15",
        "0 / 15",
        "0 / 15"
    )

    private var passingScore = 8
    private val enableLock = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit5Binding>(inflater,R.layout.fragment_unit5,container,false)

        binding.unit = this

        val lessonButtons : List<View> = listOf(
            binding.unit5Lesson1Button,
            binding.unit5Lesson2Button,
            binding.unit5Lesson3Button,
        )

        // SharedPreference Object (for accessing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

        val unit5Lesson1Score = sharedPref?.getInt("unit5Quiz1Score", 0)!!
        val unit5Lesson1Items = sharedPref?.getInt("unit5Quiz1NumQuestions", 15)!!
        scoreTexts[0] = "$unit5Lesson1Score / $unit5Lesson1Items"

        val unit5Lesson2Score = sharedPref?.getInt("unit5Quiz2Score", 0)!!
        val unit5Lesson2Items = sharedPref?.getInt("unit5Quiz2NumQuestions", 15)!!
        scoreTexts[1] = "$unit5Lesson2Score / $unit5Lesson2Items"

        val unit5Lesson3Score = sharedPref?.getInt("unit5Quiz3Score", 0)!!
        val unit5Lesson3Items = sharedPref?.getInt("unit5Quiz3NumQuestions", 15)!!
        scoreTexts[2] = "$unit5Lesson3Score / $unit5Lesson3Items"

        unit5LessonScoreText = scoreTexts

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
            R.id.unit5Lesson1Button -> {
                view.findNavController().navigate(
                    Unit5FragmentDirections.actionUnit5FragmentToAllLessonFragment(lessonFlag[0])
                )
            }
            R.id.unit5Lesson2Button -> {
                view.findNavController().navigate(
                    Unit5FragmentDirections.actionUnit5FragmentToAllLessonFragment(lessonFlag[1])
                )
            }
            R.id.unit5Lesson3Button -> {
                view.findNavController().navigate(
                    Unit5FragmentDirections.actionUnit5FragmentToAllLessonFragment(lessonFlag[2])

                )
            }
        }
    }

    private fun whatButtonsLocked(view : View, sharedPref : SharedPreferences?) {
        var unitQuizScore: Int

        when(view.id) {
            R.id.unit5Lesson1Button -> {
                view.findNavController().navigate(
                    Unit5FragmentDirections.actionUnit5FragmentToAllLessonFragment(lessonFlag[0])
                )
            }
            R.id.unit5Lesson2Button -> {
                unitQuizScore = sharedPref?.getInt("unit5Quiz1Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit5FragmentDirections
                            .actionUnit5FragmentToAllLessonFragment(lessonFlag[1]))
                } else { lockedMessage() }
            }
            R.id.unit5Lesson3Button -> {
                unitQuizScore = sharedPref?.getInt("unit5Quiz2Score", 0)!!

                if (unitQuizScore >= passingScore) {
                    view.findNavController()
                        .navigate(Unit5FragmentDirections
                            .actionUnit5FragmentToAllLessonFragment(lessonFlag[2]))
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