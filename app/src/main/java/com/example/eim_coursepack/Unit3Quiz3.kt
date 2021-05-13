package com.example.eim_coursepack

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit3Quiz3Binding


class Unit3Quiz3 : Fragment() {
    private lateinit var binding: FragmentUnit3Quiz3Binding

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(
            text = "1.\tWhat supplies energy in an electrical circuit",
            answers = mutableListOf("Battery", "Conductor", "Lightbulb", "Switch"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "2.\tWhat type of circuit is the picture below?",
            answers = mutableListOf(
                "Series",
                "Parallel",
                "Perpendicular",
                "Current"
            ),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "3.\tA type of circuit which current flows in one direction only.",
            answers = mutableListOf(
                "Series",
                "Parallel",
                "Perpendicular",
                "Current"
            ),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "4.\tWhich of the following statement is true?",
            answers = mutableListOf(
                "In series circuit, current is equally distributed among the loads",
                "In series circuit voltage is equally divided throughout the circuit",
                "In parallel circuit, total resistance is the of all the resistances in the circuit.",
                "The resistance in parallel circuit is the sum of all the voltage drop in the circuit."
            ),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "5.\tIn Parallel circuit, following are the correct formula, except___",
            answers = mutableListOf(
                "Total current is equal to all the loads in the circuit.",
                "Total voltage is equal throughout the circuit",
                "Resistance is the sum of the reciprocal in the circuit",
                "Total Voltage is the sum of the voltage drop in the circuit"
            ),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
    )

    private val idenQuestions: MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "RT =\t______ Ohms ? ",
            answers = mutableListOf("18"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "R1 =\t_____ Ohms ",
            answers = mutableListOf("10"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "R2 =\t______ Ohms",
            answers = mutableListOf("8"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "ET =\t________ Volts",
            answers = mutableListOf("18V", "18 V", "18"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "IT =\t__________ Amp",
            answers = mutableListOf("1 A", "1A", "1"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "E1 =\t______ Volts",
            answers = mutableListOf("10", "10 V", "10V"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "E2 =_________",
            answers = mutableListOf("8", "8V","8 V"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "I1 =_________",
            answers = mutableListOf("1", "1A", "1 A"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "I2 =_________",
            answers = mutableListOf("1", "1A", "1 A"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Kumusta ka?",
            answers = mutableListOf("ok", "okay"),
            isCorrect = true,
            enteredAns = ""
        )
    )

    lateinit var currentMulChoQuestion: MulChoQuestion
    lateinit var currentIdenQuestion: IdenQuestion
    lateinit var answers: MutableList<String>
    lateinit var questionText: String
    lateinit var enteredAns: String
    private var questionIndex = 0
    private val numQuestions = mulChoQuestions.size + idenQuestions.size
    private var score = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit3_quiz3, container, false
        )

        // SharedPreference Object (for storing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE
        )

        // Initialize questions and set the first question
        initQuestions()

        // Attach quiz variable from quiz 1 layout
        binding.quiz = this

        // Set the onClickListener for the nextButton
        binding.nextButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            // Handle quiz layout logic and scoring
            handleQuizProper()

            // Show back button after 1st question
            if (questionIndex > 0) {
                binding.backButton.visibility = View.VISIBLE
            }

            // Show submit button and hide next button in last question
            if (questionIndex == numQuestions - 1) {
                binding.nextButton.visibility = View.INVISIBLE
                binding.submitButton.visibility = View.VISIBLE
            }

            // Hide radio group and show edit text in identification part of the quiz
            if (questionIndex > 4) {
                binding.questionRadioGroup.visibility = View.GONE
                binding.answerText.visibility = View.VISIBLE
            }


        }

        // Set the onClickListener for the backButton
        binding.backButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->

            // Save answer in multiple choice
            if (questionIndex in 0 until 6) {
                when (binding.questionRadioGroup.checkedRadioButtonId) {
                    R.id.firstChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 0
                    R.id.secondChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 1
                    R.id.thirdChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 2
                    R.id.fourthChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 3
                }
                // move to previous question
                questionIndex--
                currentMulChoQuestion = mulChoQuestions[questionIndex]
                binding.questionRadioGroup.clearCheck()

                setMulChoQuestion()

            } else if (questionIndex in 6 until numQuestions) {
                currentIdenQuestion.enteredAns = binding.answerText.text.toString()

                // move to previous question
                questionIndex--
                currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                binding.answerText.text.clear()
                setIdenQuestion()
            }

            // Reset fields
            binding.invalidateAll()

            // Change to multiple choice if on questions 1-5
            if (questionIndex < 5) {
                binding.questionRadioGroup.visibility = View.VISIBLE
                binding.answerText.visibility = View.GONE
            }

            // Hide submit button if not on last question
            if (questionIndex < numQuestions - 1) {
                binding.submitButton.visibility = View.GONE
            }

            // Hide back button on 1st question
            if (questionIndex == 0) {
                binding.backButton.visibility = View.INVISIBLE
            }

            // Show next button if not on last question
            if (questionIndex == numQuestions - 2) {
                binding.nextButton.visibility = View.VISIBLE
            }
        }

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            // Check last question
            val answerText = binding.answerText.text.toString()
            currentMulChoQuestion.isCorrect =
                answerText.toLowerCase().trim() in currentIdenQuestion.answers

            // To make sure button only works once
            if (score == 0) {

                // Count each correct answer
                mulChoQuestions.forEach {
                    if (it.isCorrect) {
                        score++
                    }
                }

                idenQuestions.forEach {
                    if (it.isCorrect) {
                        score++
                    }
                }

//                Toast.makeText(context, "Scores: $score", Toast.LENGTH_SHORT).show()

                // Update if new score > prev score
                val prevScore = sharedPref?.getInt("unit3Quiz3Score", 0)!!
                if (prevScore < score) {
                    // Save score and number of questions in shared preferences
                    with (sharedPref?.edit()) {
                        this?.putInt("unit3Quiz3Score", score)
                        this?.putString("unit3Quiz3NumQuestions", numQuestions.toString())
                        this?.apply()
                    }
                }

                // Navigate to score screen
                view.findNavController()
                    .navigate(
                        Unit3Quiz3Directions
                            .actionUnit3Quiz3ToQuizScoreFragment(
                                numQuestions,
                                score,
                                "Unit 3: Lesson 3 Quiz"
                            )
                    )
            }


        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper() {

        // Multiple choice questions 1-5
        if (questionIndex in 0 until 5) {

            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            // Check if a radio button is selected
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondChoiceRadioButton -> answerIndex = 1
                    R.id.thirdChoiceRadioButton -> answerIndex = 2
                    R.id.fourthChoiceRadioButton -> answerIndex = 3
                }

                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                currentMulChoQuestion.isCorrect = answerIndex == currentMulChoQuestion.correctIdx

//                if (currentMulChoQuestion.isCorrect) {
//                    Toast.makeText(context, "CORRECT!", Toast.LENGTH_SHORT).show()
//                }

                currentMulChoQuestion.clickedIdx = answerIndex
                binding.questionRadioGroup.clearCheck()


                // Advance to the next question
                questionIndex++


                if (questionIndex in 0 until 5) {
                    currentMulChoQuestion = mulChoQuestions[questionIndex]
                    setMulChoQuestion()

                } else if (questionIndex in 5 until numQuestions) {

                    currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                    setIdenQuestion()
                }
                binding.invalidateAll()


                // Prompt user to select an answer
            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }

            // Identification questions 11-15
        } else if (questionIndex in 5 until numQuestions) {

            val answerText = binding.answerText.text

            if (answerText.isNotEmpty()) {
                currentIdenQuestion.isCorrect = answerText
                    .toString().toLowerCase().trim() in currentIdenQuestion.answers

                if (currentIdenQuestion.isCorrect) {
                    Toast.makeText(context, "CORRECT! Score:", Toast.LENGTH_SHORT).show()
                }

                currentIdenQuestion.enteredAns = answerText.toString()

                // Advance to the next question
                questionIndex++
                currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                setIdenQuestion()

                // reset fields
                binding.invalidateAll()
                answerText.clear()


            } else {
                Toast.makeText(context, "Enter your answer", Toast.LENGTH_SHORT).show()
            }
        } else {
            // For error checking only
            Toast.makeText(context, "Error index out of range: $questionIndex", Toast.LENGTH_LONG)
                .show()
        }
    }

    // Initialize the questions and set the first question
    private fun initQuestions() {
        questionIndex = 0

        mulChoQuestions.forEach {
            val correct = it.answers[0]

            it.answers.shuffle()

            it.correctIdx = it.answers.indexOf(correct)
        }

        enteredAns = ""

        setMulChoQuestion()
    }

    private fun setMulChoQuestion() {
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text
        // randomize the answers into a copy of the array
        answers = currentMulChoQuestion.answers.toMutableList()

        if (questionIndex==1) {
            binding.quiz1Image.visibility = View.VISIBLE
            binding.quiz1Image.setImageResource(R.drawable.ic_unit3_quiz3_q2)
        }else binding.quiz1Image.visibility = View.GONE

        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

    }

    private fun setIdenQuestion() {
        currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
        binding.quiz1Image.visibility = View.VISIBLE
        binding.quiz1Image.setImageResource(R.drawable.ic_unit3_quiz1_iden_allq)

        questionText = currentIdenQuestion.text
        answers = currentMulChoQuestion.answers.toMutableList()

        enteredAns = currentIdenQuestion.enteredAns
    }


}