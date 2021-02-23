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
        ),
        IdenQuestion(
            text = "6. It is the energy that comes from the sun.",
            answers = mutableListOf("solar", "solar energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "7. It is the energy that involves water.",
            answers = mutableListOf("tidal", "tidal energy", "hydroelectric", "hydroelectric power", "hydroelectric energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "8. It is the energy that comes from the inner core of the earth",
            answers = mutableListOf("geothermal", "geothermal energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "9. It is the result from the splitting or fission of atomic nuclei.",
            answers = mutableListOf("nuclear", "nuclear energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "10. It is the energy formed from the remains of plant and animals which lived thousands of years ago.",
            answers = mutableListOf("fossil fuel", "fossil fuels"),
            isCorrect = false,
            enteredAns = ""
        )

    )

    private val mulChoQuestion : MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(
            text = "11. It is the energy that comes from the sun.",
            answers = mutableListOf("solar", "solar energy"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "12. It is the energy that involves water.",
            answers = mutableListOf("tidal", "tidal energy", "hydroelectric", "hydroelectric power", "hydroelectric energy"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "13. It is the energy that comes from the inner core of the earth",
            answers = mutableListOf("geothermal", "geothermal energy"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "14. It is the result from the splitting or fission of atomic nuclei.",
            answers = mutableListOf("nuclear", "nuclear energy"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "15. It is the energy formed from the remains of plant and animals which lived thousands of years ago.",
            answers = mutableListOf("fossil fuel", "fossil fuels"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        )

    )

    private lateinit var currentIdenQuestion : IdenQuestion
    private var questionIndex = 0
    lateinit var questionText : String
    lateinit var answers : MutableList<String>
    lateinit var enteredAns : String
    private val numQuestions = idenQuestions.size


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
            handleQuizLogic(binding)

            if (questionIndex > 0) {
                binding.backButton.visibility = View.VISIBLE
            }

            if (questionIndex == numQuestions-1) {
                binding.nextButton.visibility = View.INVISIBLE
                binding.submitButton.visibility = View.VISIBLE
            }

        }

        // Set the onClickListener for the backButton
        binding.backButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->

            // Identification 1-10
            if (questionIndex < 10) {
                val answerText = binding.answerText.text

                currentIdenQuestion.enteredAns = answerText.toString()

                questionIndex--

                currentIdenQuestion = idenQuestions[questionIndex]
                answerText.clear()

                setIdenQuestion(binding)
            }

            binding.invalidateAll()

            if (questionIndex == numQuestions-2) {
                binding.submitButton.visibility = View.GONE
                binding.nextButton.visibility = View.VISIBLE
            }

            // Hide back button on 1st question
            if (questionIndex == 0) {
                binding.backButton.visibility = View.INVISIBLE
            }

        }

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view : View ->



        }

        return binding.root
    }

    private fun handleQuizLogic(binding: FragmentUnit1Quiz3Binding) {
        if (questionIndex < 10) {
            val answerText = binding.answerText.text

            if (answerText.isNotEmpty()) {
                currentIdenQuestion.isCorrect = answerText
                    .toString().toLowerCase().replace("\\s+".toRegex(),"") in currentIdenQuestion.answers

                // For checking only
                if (currentIdenQuestion.isCorrect) {
                    Toast.makeText(context, "CORRECT! Score:", Toast.LENGTH_SHORT).show()
                }

                currentIdenQuestion.enteredAns = answerText.toString()

                // Advance to the next question
                questionIndex++
                currentIdenQuestion = idenQuestions[questionIndex]
                setIdenQuestion(binding)

                // reset fields
                binding.invalidateAll()
                answerText.clear()


            } else {
                Toast.makeText(context,"Enter your answer",Toast.LENGTH_SHORT).show()
            }
        }
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