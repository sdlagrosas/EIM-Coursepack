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
import com.example.eim_coursepack.databinding.FragmentUnit6Quiz3Binding
import com.example.eim_coursepack.databinding.FragmentUnit6Quiz4Binding

class Unit6Quiz4Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "Read each benefit that one can get when implementing the methods " +
                "in 5S. Identify what is employed by the method\n" +
                "1. Your equipment lifespan will be prolonged, and breakdowns will be less.",
            answers = mutableListOf(
                "Sort",
                "Set in order",
                "Shine",
                "Standardize",
                "Sustain"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "Read each benefit that one can get when implementing the methods " +
                "in 5S. Identify what is employed by the method\n" +
                "2.	You reduce searching time.",
            answers = mutableListOf(
                "Sort",
                "Set in order",
                "Shine",
                "Standardize",
                "Sustain"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2),
        MulChoQuestion(text = "Read each benefit that one can get when implementing the methods " +
                "in 5S. Identify what is employed by the method\n" +
                "3.	Provides you with data for improving 5S.",
            answers = mutableListOf(
                "Sort",
                "Set in order",
                "Shine",
                "Standardize",
                "Sustain"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "Read each benefit that one can get when implementing the methods " +
                "in 5S. Identify what is employed by the method\n" +
                "4.	You will have consistency in the work practices.",
            answers = mutableListOf(
                "Sort",
                "Set in order",
                "Shine",
                "Standardize",
                "Sustain"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 4),
        MulChoQuestion(text = "Read each benefit that one can get when implementing the methods " +
                "in 5S. Identify what is employed by the method\n" +
                "5.	Creates healthy atmosphere and a good workplace.",
            answers = mutableListOf(
                "Sort",
                "Set in order",
                "Shine",
                "Standardize",
                "Sustain"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "Read each benefit that one can get when implementing the methods " +
                "in 5S. Identify what is employed by the method\n" +
                "6.	You will avoid mistakes.",
            answers = mutableListOf(
                "Sort",
                "Set in order",
                "Shine",
                "Standardize",
                "Sustain"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 4),
        MulChoQuestion(text = "Read each benefit that one can get when implementing the methods " +
                "in 5S. Identify what is employed by the method\n" +
                "7.	You have better flow of work.",
            answers = mutableListOf(
                "Sort",
                "Set in order",
                "Shine",
                "Standardize",
                "Sustain"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "Read each benefit that one can get when implementing the methods " +
                "in 5S. Identify what is employed by the method\n" +
                "8.	You make lesser mistakes.",
            answers = mutableListOf(
                "Sort",
                "Set in order",
                "Shine",
                "Standardize",
                "Sustain"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "Read each benefit that one can get when implementing the methods " +
                "in 5S. Identify what is employed by the method\n" +
                "9.	Creates a pleasant environment. Prevents accidents.",
            answers = mutableListOf(
                "Sort",
                "Set in order",
                "Shine",
                "Standardize",
                "Sustain"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "Read each benefit that one can get when implementing the methods " +
                "in 5S. Identify what is employed by the method\n" +
                "10. You easily find what you are looking for.",
            answers = mutableListOf(
                "Sort",
                "Set in order",
                "Shine",
                "Standardize",
                "Sustain"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2),
        MulChoQuestion(text = "11. The emphasis of stratification management and being able to " +
                "spot the unwanted and unnecessary before they become problematic.",
            answers = mutableListOf(
                "Seiri",
                "Seiton",
                "Seiso",
                "Seiketsu",
                "Shitsuke"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "12. Places emphasis on being able to forge a workplace with good " +
                "habits and discipline. Demonstrating to others what needs to be done and " +
                "encouraging practice amongst them. This is mainly a management responsibility.",
            answers = mutableListOf(
                "Seiri",
                "Seiton",
                "Seiso",
                "Seiketsu",
                "Shitsuke"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 4),
        MulChoQuestion(text = "13. Defined as neatness, having things in the right places, or " +
                "set up so that they are readily available for use, eliminating the need to search.",
            answers = mutableListOf(
                "Seiri",
                "Seiton",
                "Seiso",
                "Seiketsu",
                "Shitsuke"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "14. The emphasis here is on visual management, an important " +
                "aspect to attain and maintain standardized conditions to enable the individuals " +
                "always act quickly.",
            answers = mutableListOf(
                "Seiri",
                "Seiton",
                "Seiso",
                "Seiketsu",
                "Shitsuke"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "15. Emphasis on cleaning so that things are clean; in other " +
                "words, carrying out cleaning as a form of inspection i.e. getting rid of " +
                "waste, and foreign matter. ",
            answers = mutableListOf(
                "Seiri",
                "Seiton",
                "Seiso",
                "Seiketsu",
                "Shitsuke"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2)
    )

    private lateinit var currentMulChoQuestion: MulChoQuestion
    private lateinit var currentIdenQuestion: IdenQuestion
    lateinit var answers: MutableList<String>
    lateinit var questionText : String
    lateinit var enteredAns : String
    private var questionIndex = 0
    private val numQuestions = mulChoQuestions.size
    private var score = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentUnit6Quiz4Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit6_quiz4, container, false
        )

        // SharedPreference Object (for storing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

        // Initialize questions and set the first question
        initQuestions(binding)

        // Attach quiz variable from quiz 1 layout
        binding.quiz = this

        // Set the onClickListener for the nextButton
        binding.nextButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            // Handle quiz layout logic and scoring
            handleQuizProper(binding)

            // Show back button after 1st question
            if (questionIndex > 0) {
                binding.backButton.visibility = View.VISIBLE
            }

            // Show submit button and hide next button in last question
            if (questionIndex == numQuestions-1) {
                binding.nextButton.visibility = View.INVISIBLE
                binding.submitButton.visibility = View.VISIBLE
            }

            if (questionIndex >= mulChoQuestions.size) {
                binding.questionRadioGroup.visibility = View.GONE
                binding.answerText.visibility = View.VISIBLE
            }


        }

        // Set the onClickListener for the backButton
        binding.backButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            // Save answer in multiple choice
            when (binding.questionRadioGroup.checkedRadioButtonId) {
                R.id.firstChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 0
                R.id.secondChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 1
                R.id.thirdChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 2
                R.id.fourthChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 3
                R.id.fifthChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 4
            }

            questionIndex--

            if (questionIndex < mulChoQuestions.size) {
                currentMulChoQuestion = mulChoQuestions[questionIndex]
                binding.questionRadioGroup.clearCheck()

                setMulChoQuestion(binding)

            }

            // Reset fields
            binding.invalidateAll()

            // Change to multiple choice if on questions 1-10
            if (questionIndex < mulChoQuestions.size) {
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

            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            // Check if a radio button is selected
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondChoiceRadioButton -> answerIndex = 1
                    R.id.thirdChoiceRadioButton -> answerIndex = 2
                    R.id.fourthChoiceRadioButton -> answerIndex = 3
                    R.id.fifthChoiceRadioButton -> answerIndex = 4
                }

                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                currentMulChoQuestion.isCorrect = answerIndex == currentMulChoQuestion.correctIdx

                // To make sure button only works once
                if (score == 0) {

                    // Count each correct answer
                    mulChoQuestions.forEach {
                        if (it.isCorrect){
                            score++
                        }
                    }

                    // Update if new score > prev score
                    val prevScore = sharedPref?.getInt("unit6Quiz3Score", 0)!!
                    if (prevScore < score) {
                        // Save score and number of questions in shared preferences
                        with (sharedPref?.edit()) {
                            this?.putInt("unit6Quiz4Score", score)
                            this?.putInt("unit6Quiz4NumQuestions", numQuestions)
                            this?.apply()
                        }
                    }

                    // Navigate to score screen
                    view.findNavController().navigate(
                        Unit6Quiz4FragmentDirections
                            .actionUnit6Quiz4FragmentToQuizScoreFragment(
                                numQuestions,
                                score,
                                "Unit 6: Lesson 4 Quiz"
                            ))

                }

            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit6Quiz4Binding) {

        // Multiple choice questions 1-10
        if (questionIndex in 0 until mulChoQuestions.size) {

            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            // Check if a radio button is selected
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondChoiceRadioButton -> answerIndex = 1
                    R.id.thirdChoiceRadioButton -> answerIndex = 2
                    R.id.fourthChoiceRadioButton -> answerIndex = 3
                    R.id.fifthChoiceRadioButton -> answerIndex = 4
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


                if (questionIndex in 0 until mulChoQuestions.size) {
                    currentMulChoQuestion = mulChoQuestions[questionIndex]
                    setMulChoQuestion(binding)
                }
                binding.invalidateAll()


                // Prompt user to select an answer
            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }

            // Identification questions 11-15
        } else {
            // For error checking only
            Toast.makeText(context, "Error index out of range: $questionIndex", Toast.LENGTH_LONG).show()
        }
    }

    // Initialize the questions and set the first question
    private fun initQuestions(binding : FragmentUnit6Quiz4Binding) {
        questionIndex = 0

        enteredAns = ""


        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit6Quiz4Binding) {
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text

        binding.quizImage.setImageResource(0)

        answers = currentMulChoQuestion.answers

        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

    }

}