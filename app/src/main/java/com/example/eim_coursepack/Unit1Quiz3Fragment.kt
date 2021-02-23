package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit1Quiz2Binding
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

    private lateinit var currentIdenQuestion : IdenQuestion
    private var questionIndex = 0
    lateinit var questionText : String
    lateinit var answers : MutableList<String>
    lateinit var enteredAns : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit1Quiz3Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_quiz3, container, false
        )

        // Initialize questions and set the first question
        initQuestions(binding)

        // Attach quiz variable from quiz 1 layout
        binding.quiz = this

        // Set the onClickListener for the nextButton
        binding.nextButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->

        }

        // Set the onClickListener for the backButton
        binding.backButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->


        }

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view : View ->



        }

        return binding.root
    }

    private fun initQuestions(binding: FragmentUnit1Quiz3Binding) {
        questionIndex = 0

        setIdenQuestion(binding)
    }


    private fun setIdenQuestion(binding: FragmentUnit1Quiz3Binding) {
        currentIdenQuestion = idenQuestions[questionIndex]

        questionText = currentIdenQuestion.text

        answers = currentIdenQuestion.answers

        enteredAns = currentIdenQuestion.enteredAns

    }


}