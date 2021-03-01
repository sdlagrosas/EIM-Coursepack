package com.example.eim_coursepack

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit4Quiz1Binding
import com.example.eim_coursepack.databinding.FragmentUnit4Quiz2Binding
import com.example.eim_coursepack.databinding.FragmentUnit4Quiz3Binding

class Unit4Quiz3Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. The following are the advantages of an electrical floor plan, " +
                "except _______.",
            answers = mutableListOf(
                "Illustrates the design and area of the establishment/building.",
                "Demonstrate the actual location of the devices to be installed.",
                "Itemized the electrical devices to be used in particular area.",
                "It includes the water distribution at the certain building/establishment."),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "2. Which of the following is found in the electrical floor plan?",
            answers = mutableListOf(
                "Legend",
                "Cost",
                "Materials Cost",
                "Tools used"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "3. Electrical floor plan consists of the different electrical " +
                "symbol, which of the following is an example of incandescent lamp?",
            answers = mutableListOf(
                "",
                "",
                "",
                ""),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "4. Based from the floor plan above, how many convenience duplex " +
                "outlet are there?",
            answers = mutableListOf(
                "8",
                "6",
                "2",
                "7"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "5. How many Single Pole Switch in the electrical floor plan are " +
                "found?",
            answers = mutableListOf(
                "4",
                "6",
                "2",
                "5"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "6. 2 gang switch is found on what part of the house according to " +
                "floor plan?",
            answers = mutableListOf(
                "Porch",
                "Dining Room",
                "Living Room",
                "Kitchen"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2),
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "7. Identify an electrical device found in the floorplan above. Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "8. Identify an electrical device found in the floorplan above. Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "9. Identify an electrical device found in the floorplan above. Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "10. Identify an electrical device found in the floorplan above. Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "11. Identify an electrical device found in the floorplan above. Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. Identify an electrical device found in the floorplan above. Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. Identify an electrical device found in the floorplan above. Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. Identify an electrical device found in the floorplan above. Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Identify an electrical device found in the floorplan above. Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        )
    )

    private val idenAnswerSet : MutableList<String> = mutableListOf(
        "ceiling mounted lamp",
        "duplex convenience outlet",
        "wall mounted light",
        "fan",
        "duplex receptacle outlet",
        "telephone outlet",
        "switch",
        "dimmer switch",
        "door bell"
    )

    private lateinit var currentMulChoQuestion: MulChoQuestion
    private lateinit var currentIdenQuestion: IdenQuestion
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
        val binding : FragmentUnit4Quiz3Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit4_quiz3, container, false
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

            if (questionIndex < mulChoQuestions.size) {
                binding.questionRadioGroup.visibility = View.VISIBLE
                binding.answerText.visibility = View.GONE
            } else {
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
            }

            // Save answer in identification
            currentIdenQuestion.enteredAns = binding.answerText.text.toString()

            questionIndex--


            if (questionIndex < mulChoQuestions.size) {
                currentMulChoQuestion = mulChoQuestions[questionIndex]
                binding.questionRadioGroup.clearCheck()

                setMulChoQuestion(binding)

            } else {
                currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                binding.answerText.text.clear()

                setIdenQuestion(binding)
            }

            // Reset fields
            binding.invalidateAll()

            // Change to multiple choice if on questions 1-10
            if (questionIndex < mulChoQuestions.size) {
                binding.questionRadioGroup.visibility = View.VISIBLE
                binding.answerText.visibility = View.GONE
            } else {
                binding.questionRadioGroup.visibility = View.GONE
                binding.answerText.visibility = View.VISIBLE
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

            // To make sure button only works once
            if (score == 0) {

                currentIdenQuestion.enteredAns = binding.answerText.text.toString()

                idenQuestions.forEach {
                    if (it.enteredAns
                            .toLowerCase()
                            .replace("\\s+".toRegex(), " ")
                            .trim() in idenAnswerSet) {
                        idenAnswerSet.remove(it.enteredAns)
                        score++
                    }
                }

                // Count each correct answer
                mulChoQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }

//              Save score and number of questions in shared preferences
                with (sharedPref?.edit()) {
                    this?.putString("unit4Quiz3Score", score.toString())
                    this?.putString("unit4Quiz3NumQuestions", numQuestions.toString())
                    this?.apply()
                }
//
                // Navigate to score screen
                view.findNavController().navigate(
                    Unit4Quiz3FragmentDirections
                        .actionUnit4Quiz3FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 4: Lesson 3 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit4Quiz3Binding) {

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
                }

                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                currentMulChoQuestion.isCorrect = answerIndex == currentMulChoQuestion.correctIdx

                if (currentMulChoQuestion.isCorrect) {
                    Toast.makeText(context, "CORRECT!", Toast.LENGTH_SHORT).show()
                }

                currentMulChoQuestion.clickedIdx = answerIndex
                binding.questionRadioGroup.clearCheck()


                // Advance to the next question
                questionIndex++


                if (questionIndex in 0 until mulChoQuestions.size) {
                    currentMulChoQuestion = mulChoQuestions[questionIndex]
                    setMulChoQuestion(binding)

                } else if (questionIndex in mulChoQuestions.size until numQuestions) {

                    currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                    setIdenQuestion(binding)
                }
                binding.invalidateAll()


                // Prompt user to select an answer
            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }

            // Identification questions 11-15
        } else if (questionIndex in mulChoQuestions.size until numQuestions) {

            val answerText = binding.answerText.text

            if (answerText.isNotEmpty()) {

                currentIdenQuestion.enteredAns = answerText.toString()

                // Advance to the next question
                questionIndex++
                currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                setIdenQuestion(binding)

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
    private fun initQuestions(binding : FragmentUnit4Quiz3Binding) {
        questionIndex = 0

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit4Quiz3Binding) {
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text
        // randomize the answers into a copy of the array
        answers = currentMulChoQuestion.answers

        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

        setImageChoices(binding)
        setQuestionImage(binding)

    }

    private fun setQuestionImage(binding : FragmentUnit4Quiz3Binding) {
        val quizImage = binding.quizImage
        quizImage.adjustViewBounds = true
        when (questionIndex) {
            in 3 until 6 -> {
                quizImage.setImageResource(R.drawable.ic_unit4_quiz3_q4to6)
            }
            in 6 until numQuestions -> {
                quizImage.setImageResource(R.drawable.ic_unit4_quiz3_q7to15)
            }
            else -> {
                quizImage.setImageResource(0)
            }
        }
    }

    private fun setImageChoices(binding: FragmentUnit4Quiz3Binding) {
        if (questionIndex == 2) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                binding.firstChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_unit4_quiz3_q3_choice1, 0
                )
                binding.secondChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_unit4_quiz3_q3_choice2, 0
                )
                binding.thirdChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_unit4_quiz3_q3_choice3, 0
                )
                binding.fourthChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_unit4_quiz3_q3_choice4, 0
                )
            }

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                binding.firstChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0
                )
                binding.secondChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0
                )
                binding.thirdChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0
                )
                binding.fourthChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0
                )
            }
        }
    }

    private fun setIdenQuestion(binding: FragmentUnit4Quiz3Binding) {
        val idenQuestionIndex = questionIndex - mulChoQuestions.size
        currentIdenQuestion = idenQuestions[idenQuestionIndex]

        val quizImage = binding.quizImage

        quizImage.visibility = View.VISIBLE

        setQuestionImage(binding)

        questionText = currentIdenQuestion.text
        // randomize the answers into a copy of the array

        enteredAns = currentIdenQuestion.enteredAns
    }

}