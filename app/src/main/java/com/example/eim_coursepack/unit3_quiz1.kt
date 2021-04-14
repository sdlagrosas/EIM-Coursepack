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
import com.example.eim_coursepack.databinding.FragmentUnit3Quiz1Binding


class Unit3_quiz1 : Fragment() {
    private lateinit var binding : FragmentUnit3Quiz1Binding

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1.\tSystem of measure first used by the ancient people.",
            answers = mutableListOf("Metric System", "English System", "International System", "Scientific System"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "2.\t20 millimeter is equivalent to how many centimeters?",
            answers = mutableListOf("2 cm", "200 cm", "0.02 cm", "0.20cm"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "3.\tDecimalized system of measurement.",
            answers = mutableListOf("Metric System ", "English System", "International System", "Scientific System"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "4.\tA unit of linear measure equal to 1,760 yards",
            answers = mutableListOf("mile", "foot", "yard", "inch"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "5.\tTenth of a meter",
            answers = mutableListOf("decimeter","centimeter", "millimeter", "meter"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "6.\tA unit of linear measure equal to 3 feet",
            answers = mutableListOf("yard", "mile", "foot", "inch"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "7.\tOne-thousandth of a meter",
            answers = mutableListOf(
                "millimeter",
                "centimeter",
                "decimeter",
                "meter"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(text = "8.\tHundredth of a meter ",
            answers = mutableListOf("centimeter", "millimeter", "decimeter", "meter"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "9.\tA unit of linear measure equal to one twelfth of a foot",
            answers = mutableListOf("inch","mile", "foot", "yard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "10.\tManuel is 6’4 tall, what is the equivalent of his height in inches?",
            answers = mutableListOf(
                "76 inches",
                "78.6 inches",
                "64 inches",
                "640 inches"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "1. Put your answer here.(Input the number only) ______mm",
            answers = mutableListOf("1"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "2. Put your answer here.(Input the number only) ______cm",
            answers = mutableListOf("1"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "3. Put your answer here.(Input the number only) ______mm",
            answers = mutableListOf("25"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "4. Put your answer here.(Input the number only) ______inch",
            answers = mutableListOf("1/8", "0.125"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "5. Put your answer here.(Input the number and the unit) ______",
            answers = mutableListOf("1/2 inch", "0.5 inch"),
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
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit3_quiz1, container, false)
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
            if (questionIndex > 9) {
                binding.questionRadioGroup.visibility = View.GONE
                binding.answerText.visibility = View.VISIBLE
            }


        }

        // Set the onClickListener for the backButton
        binding.backButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->

            // Save answer in multiple choice
            if (questionIndex in 0 until 11) {
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

            } else if (questionIndex in 11 until numQuestions) {
                currentIdenQuestion.enteredAns = binding.answerText.text.toString()

                // move to previous question
                questionIndex--
                currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                binding.answerText.text.clear()
                setIdenQuestion()
            }

            // Reset fields
            binding.invalidateAll()

            // Change to multiple choice if on questions 1-10
            if (questionIndex < 10) {
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

                Toast.makeText(context, "Scores: $score", Toast.LENGTH_SHORT).show()

                // Save score and number of questions in shared preferences
                with (sharedPref?.edit()) {
                    this?.putString("unit3Quiz1Score", score.toString())
                    this?.putString("unit3Quiz1NumQuestions", numQuestions.toString())
                    this?.apply()
                }

                // Navigate to score screen
                view.findNavController()
                    .navigate(Unit3_quiz1Directions
                        .actionUnit3Quiz12ToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 3: Lesson 1 Quiz"
                        ))
            }


        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper() {

        // Multiple choice questions 1-10
        if (questionIndex in 0 until 10) {

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


                if (questionIndex in 0 until 10) {
                    currentMulChoQuestion = mulChoQuestions[questionIndex]
                    setMulChoQuestion()

                } else if (questionIndex in 10 until numQuestions) {

                    currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                    setIdenQuestion()
                }
                binding.invalidateAll()


                // Prompt user to select an answer
            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }

            // Identification questions 11-15
        } else if (questionIndex in 10 until numQuestions) {

            val answerText = binding.answerText.text

            if (answerText.isNotEmpty()) {
                currentIdenQuestion.isCorrect = answerText
                    .toString().toLowerCase().trim() in currentIdenQuestion.answers

//                if (currentIdenQuestion.isCorrect) {
//                    Toast.makeText(context, "CORRECT! Score:", Toast.LENGTH_SHORT).show()
//                }

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


        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

    }

    private fun setIdenQuestion() {
        binding.quiz1Image.visibility=View.VISIBLE
        currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]

        questionText = currentIdenQuestion.text
        answers = currentMulChoQuestion.answers.toMutableList()

        enteredAns = currentIdenQuestion.enteredAns
    }


}