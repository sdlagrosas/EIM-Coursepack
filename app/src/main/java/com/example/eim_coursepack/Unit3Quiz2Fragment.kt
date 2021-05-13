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
import com.example.eim_coursepack.databinding.FragmentUnit3Quiz2Binding


class Unit3Quiz2Fragment : Fragment() {
    private lateinit var binding : FragmentUnit3Quiz2Binding

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1.\tShiela measure the table with 3 ½ feet length, what is the equivalent of this in centimeter?",
            answers = mutableListOf("106.68", "320.04", "8.89", "10.6"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "2.\tWhich of the following statement is TRUE?",
            answers = mutableListOf( "Inch is an English unit and the smallest unit of measurement in English system.",
                "Foot is an English unit and second to the smallest unit in Metric system of measurement.",
                "Millimeter is an English unit and the smallest unit of measurement in English system.",
                "Centimeter in metric unit and the second to the largest unit of metric system."),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "3.\tThe following are correct equivalent, EXCEPT",
            answers = mutableListOf(
                    "10 yards is equal to 30 feet",
                    "60 inches is 6 feet",
                    "8 inches is equal to 20.32",
                    "5 meters is equal to 500 centimeters"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "4.\tAngela has 8 yards of yarn, what is the equivalent of this in centimeter?",
            answers = mutableListOf(
                    "731.52 centimeters",
                    "20.32 centimeters",
                    "243.84 centimeters",
                    "96 centimeters"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "5.\tEstrella needs to measure the height of the windows to purchase correct length of fabric. 36 feet is how many meters?",
            answers = mutableListOf("10.97",
                    "109.7",
                    "1.09",
                    "1,097"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "1. \t1 cm is  \t \t_____ mm ? ",
            answers = mutableListOf("10", "10 mm"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "2.\t1 yard is  \t\t_____ inches ",
            answers = mutableListOf("36", "36 inches"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "3. \t1 dm is  \t \t_____ cm ",
            answers = mutableListOf("100","100 cm"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "4.\t10 mm is  \t\t_____ cm ",
            answers = mutableListOf("1", "1 cm"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "5.\t1 foot is \t\t_____ inches ",
            answers = mutableListOf("12", "12 inches"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "6.\t 10 feet \t\t =  ________cm",
            answers = mutableListOf("304.8", "304.8 cm"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "7.\t 70 cm \t\t =  ________mm",
            answers = mutableListOf("700", "700 cm"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "8.\t 15 inches \t\t =  ________ft",
            answers = mutableListOf("1.25", "1.25 ft"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "9.\t 5 meters \t\t =  ________yard",
            answers = mutableListOf("5.46", "5.46 yard"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "10.\t8 meters \t\t= _____ ft",
            answers = mutableListOf("26.24", "26.24 ft"),
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
            inflater, R.layout.fragment_unit3_quiz2, container, false)

        // SharedPreference Object (for storing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

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
            if (questionIndex == numQuestions-1) {
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
            if (questionIndex < numQuestions-1) {
                binding.submitButton.visibility = View.GONE
            }

            // Hide back button on 1st question
            if (questionIndex == 0) {
                binding.backButton.visibility = View.INVISIBLE
            }

            // Show next button if not on last question
            if (questionIndex == numQuestions-2) {
                binding.nextButton.visibility = View.VISIBLE
            }
        }

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view : View ->
            // Check last question
            val answerText = binding.answerText.text.toString()
            currentMulChoQuestion.isCorrect = answerText.toLowerCase().trim() in currentIdenQuestion.answers

            // To make sure button only works once
            if (score == 0) {

                // Count each correct answer
                mulChoQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }

                idenQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }

//                Toast.makeText(context, "Scores: $score", Toast.LENGTH_SHORT).show()

                // Update if new score > prev score
                val prevScore = sharedPref?.getInt("unit3Quiz2Score", 0)!!
                if (prevScore < score) {
                    // Save score and number of questions in shared preferences
                    with (sharedPref?.edit()) {
                        this?.putInt("unit3Quiz2Score", score)
                        this?.putString("unit3Quiz2NumQuestions", numQuestions.toString())
                        this?.apply()
                    }
                }

                // Navigate to score screen
                view.findNavController()
                    .navigate(Unit3Quiz2FragmentDirections
                        .actionUnit3Quiz2FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 3: Lesson 2 Quiz"
                        ))
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
                Toast.makeText(context,"Enter your answer", Toast.LENGTH_SHORT).show()
            }
        } else {
            // For error checking only
            Toast.makeText(context, "Error index out of range: $questionIndex", Toast.LENGTH_LONG).show()
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


        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

    }

    private fun setIdenQuestion() {
        currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]

        questionText = currentIdenQuestion.text
        answers = currentMulChoQuestion.answers.toMutableList()

        enteredAns = currentIdenQuestion.enteredAns
    }



}