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
    private val passingScore = 8
    private val enableLock = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentUnit1Binding>(inflater,R.layout.fragment_unit1,container,false)

        val lesson1id= binding.unit1Lesson1Button
        val lesson2id= binding.unit1Lesson2Button
        val lesson3id= binding.unit1Lesson3Button

        // SharedPreference Object (for accessing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

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

