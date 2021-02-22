package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.eim_coursepack.databinding.FragmentUnit1Quiz3Binding


class Unit1Quiz3Fragment : Fragment() {

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "1. It is the energy that comes from the sun.",
            answers = mutableListOf("solar", "solar energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "2. It is the energy that involves water.",
            answers = mutableListOf("tidal", "tidal energy", "hydroelectric", "hydroelectric power", "hydroelectric energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "3. It is the energy that comes from the inner core of the earth",
            answers = mutableListOf("geothermal", "geothermal energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "4. It is the result from the splitting or fission of atomic nuclei.",
            answers = mutableListOf("nuclear", "nuclear energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "5. It is the energy formed from the remains of plant and animals which lived thousands of years ago.",
            answers = mutableListOf("fossil fuel", "fossil fuels"),
            isCorrect = false,
            enteredAns = ""
        )

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit1Quiz3Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_quiz3, container, false
        )

        return binding.root
    }

}