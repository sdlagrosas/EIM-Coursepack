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
import com.example.eim_coursepack.databinding.FragmentUnit3Quiz4Binding

class Unit3Quiz4 : Fragment() {
    private lateinit var binding : FragmentUnit3Quiz4Binding

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1.\tThe device used in measuring , current, voltage and resistance.",
            answers = mutableListOf("Multimeter", "Voltmeter", "Ohmmeter", "Ammeter"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "2.\tThese refer to handles used to hold tip on the tested connection.",
            answers = mutableListOf( "Probes", "Pins", "Red meter lead", "Black meter lead"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "3.\tWhat type of multimeter has needle/pointer gauge?",
            answers = mutableListOf("Analog multimeter",
                "Digital multimeter",
                "Electrical multimeter",
                "Mechanical dual multimeter"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "4.\tThese refers to the part of the multimeter that select particular range to be measured",
            answers = mutableListOf("Range selector knob", "Zero Ohm adjusting knob",
                "Scale",
                "Test probes"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "5.\t--- this type of representation, stands for what specific range",
            answers = mutableListOf("AC Voltage",
                "Resistance",
                "DC voltage",
                "Current"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "6.\tWhich of the following statement is Correct?",
            answers = mutableListOf("In measuring resistance, the device should not be connected to voltage source.",
                "c.\tIn measuring the current, the device should be connected to the voltage source.",
                "b.\tIn measuring DC Voltage, range selector knob must be pointed to ACV range",
                "a.\tIn measuring resistance, it is important that the device to be measured is connected to the voltage source."),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "7.\tTo identified the resistance on the scale, what symbol you need to look at?",
            answers = mutableListOf("Ω",
                "mA",
                "DCV",
                "ACV"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "8.\tIf you are going to measure the amount of voltage of the battery what specific range you are going to select?",
            answers = mutableListOf("DCV",
                "mA",
                "ACV",
                "Ω"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "Parts of Multimeter: \n1. ",
            answers = mutableListOf("Scale"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Parts of Multimeter: \n" +
                    "2. ",
            answers = mutableListOf("Adjustment screw"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Parts of Multimeter: \n" +
                    "3. ",
            answers = mutableListOf("Range selector knob"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Parts of Multimeter: \n" +
                    "4. ",
            answers = mutableListOf("Pointer"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Parts of Multimeter: \n" +
                    "5. ",
            answers = mutableListOf("Zero-ohm adjustment knob"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Parts of Multimeter: \n" +
                    "6. ",
            answers = mutableListOf("Test probe"),
            isCorrect = false,
            enteredAns = ""
        )
    )

    lateinit var currentMulChoQuestion: MulChoQuestion
    lateinit var currentIdenQuestion: IdenQuestion
    lateinit var answers: MutableList<String>
    lateinit var questionText : String
    lateinit var enteredAns : String
    private var questionIndex = 0
    private val numQuestions = mulChoQuestions.size + idenQuestions.size
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit3_quiz4, container, false
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
            if (questionIndex > 7) {
                binding.questionRadioGroup.visibility = View.GONE
                binding.answerText.visibility = View.VISIBLE
            }


        }

        // Set the onClickListener for the backButton
        binding.backButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->

            // Save answer in multiple choice
            if (questionIndex in 0 until 9) {
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

            } else if (questionIndex in 9 until numQuestions) {
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
            if (questionIndex < 8) {
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

                Toast.makeText(context, "Scores: $score", Toast.LENGTH_SHORT).show()

                // Save score and number of questions in shared preferences
                with(sharedPref?.edit()) {
                    this?.putString("unit3Quiz4Score", score.toString())
                    this?.putString("unit3Quiz4NumQuestions", numQuestions.toString())
                    this?.apply()
                }

                // Navigate to score screen
                view.findNavController()
                    .navigate(
                        Unit3Quiz4Directions
                            .actionUnit3Quiz4ToQuizScoreFragment(
                                numQuestions,
                                score,
                                "Unit 3: Lesson 4 Quiz"
                            )
                    )
            }


        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper() {

        // Multiple choice questions 1-5
        if (questionIndex in 0 until 8) {

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


                if (questionIndex in 0 until 8) {
                    currentMulChoQuestion = mulChoQuestions[questionIndex]
                    setMulChoQuestion()

                } else if (questionIndex in 8 until numQuestions) {

                    currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                    setIdenQuestion()
                }
                binding.invalidateAll()


                // Prompt user to select an answer
            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }

            // Identification questions 11-15
        } else if (questionIndex in 8 until numQuestions) {

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
        binding.quiz1Image.visibility=View.GONE
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
        binding.quiz1Image.visibility=View.GONE
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text
        // randomize the answers into a copy of the array
        answers = currentMulChoQuestion.answers.toMutableList()

//        if (questionIndex==1) {
//            binding.quiz1Image.visibility = View.VISIBLE
//            binding.quiz1Image.setImageResource(R.drawable.ic_unit3_quiz3_q2)
//        }else binding.quiz1Image.visibility = View.GONE

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
        questionText = currentIdenQuestion.text
        answers = currentMulChoQuestion.answers.toMutableList()

        enteredAns = currentIdenQuestion.enteredAns
    }


}