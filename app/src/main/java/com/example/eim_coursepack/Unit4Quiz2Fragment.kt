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
import com.example.eim_coursepack.databinding.FragmentUnit4Quiz1Binding
import com.example.eim_coursepack.databinding.FragmentUnit4Quiz2Binding

class Unit4Quiz2Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. Electrical Diagram which uses actual or realistic " +
                "representation devices in a circuit",
            answers = mutableListOf(
                "Pictorial Diagram",
                "Line Diagram",
                "Wiring Diagram",
                "Actual Connection Diagram"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "2. Diagram uses slashes to determine the number or wire " +
                "inside the conduit pipe.",
            answers = mutableListOf(
                "Pictorial Diagram",
                "Line Diagram",
                "Wiring Diagram",
                "Actual Connection Diagram"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "3. this refers to the diagram illustrate the wiring " +
                "connection and direction of wire in a circuit.",
            answers = mutableListOf(
                "Pictorial Diagram",
                "Line Diagram",
                "Wiring Diagram",
                "Actual Connection Diagram"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2),
        MulChoQuestion(text = "4. This diagram shows the detailed connection of each device in " +
                "a circuit",
            answers = mutableListOf(
                "Pictorial Diagram",
                "Line Diagram",
                "Wiring Diagram",
                "Actual Connection Diagram"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "5. Electrical diagram uses basic electrical symbol to " +
                "represents device in a circuit.",
            answers = mutableListOf(
                "Actual Connection Diagram",
                "Line Diagram",
                "Schematic Diagram",
                "Wiring Diagram"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "6. Identify the type of diagram illustrated above",
            answers = mutableListOf("line diagram"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "7. Identify the type of diagram illustrated above",
            answers = mutableListOf("schematic diagram"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "8. Identify the type of diagram illustrated above",
            answers = mutableListOf("line diagram"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "9. Identify the type of diagram illustrated above",
            answers = mutableListOf("actual connection diagram"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "10. Identify the type of diagram illustrated above",
            answers = mutableListOf("line diagram"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "11. Manoy Bolodoy, wants to know the length of wire he needs for the " +
                    "electrical job, He has 5 different electrical diagrams, which of these " +
                    "diagrams is best to refer in this kind of situation.",
            answers = mutableListOf("line diagram"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. Mang Manolo, wanted to show to his client the actual appearance of the " +
                    "circuit he will be working, what particular diagram is he going to show?",
            answers = mutableListOf("pictorial diagram"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. Manong Gab, draw a diagram which shows the details or wiring " +
                    "connection, including the color coding of wire so that it will easy for " +
                    "him to connect accurately all the devices in the circuit. What do you call " +
                    "in this type of diagram?",
            answers = mutableListOf("wiring diagram"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. Mang Junjun, wants to interpret the job in very easy way, which uses " +
                    "just the simple electrical symbol in illustrating the job. What do you " +
                    "think the diagram the best to draw in this type of situation?",
            answers = mutableListOf("schematic diagram"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Monoy Boy, has several electrical diagram for the job, however he wants " +
                    "to look for the detail of which of the devices on the job are actually " +
                    "connected, which diagram is he going to look at?",
            answers = mutableListOf("actual connection diagram"),
            isCorrect = false,
            enteredAns = ""
        )
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
        val binding : FragmentUnit4Quiz2Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit4_quiz2, container, false
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
            val answerText = binding.answerText.text

            currentIdenQuestion.isCorrect = answerText
                .toString()
                .toLowerCase()
                .replace("\\s+".toRegex()," ")
                .trim() in currentIdenQuestion.answers

            // To make sure button only works once
            if (score == 0) {

                // Count each correct answer
                mulChoQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }
                // Count each correct answer
                idenQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }

                // Update if new score > prev score
                val prevScore = sharedPref?.getInt("unit4Quiz2Score", 0)!!
                if (prevScore < score) {
                    // Save score and number of questions in shared preferences
                    with (sharedPref.edit()) {
                        this?.putInt("unit4Quiz2Score", score)
                        this?.putInt("unit4Quiz2NumQuestions", numQuestions)
                        this?.apply()
                    }
                }

                // Navigate to score screen
                view.findNavController().navigate(
                    Unit4Quiz2FragmentDirections
                        .actionUnit4Quiz2FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 4: Lesson 2 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit4Quiz2Binding) {

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
                currentIdenQuestion.isCorrect = answerText
                    .toString()
                    .toLowerCase()
                    .replace("\\s+".toRegex()," ")
                    .trim() in currentIdenQuestion.answers

//                if (currentIdenQuestion.isCorrect) {
//                    Toast.makeText(context, "CORRECT! Score:", Toast.LENGTH_SHORT).show()
//                }

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
    private fun initQuestions(binding : FragmentUnit4Quiz2Binding) {
        questionIndex = 0

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit4Quiz2Binding) {
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text
        // randomize the answers into a copy of the array
        answers = currentMulChoQuestion.answers

        binding.quizImage.visibility = View.GONE


        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

    }

    private fun setIdenQuestion(binding: FragmentUnit4Quiz2Binding) {
        val idenQuestionIndex = questionIndex - mulChoQuestions.size
        currentIdenQuestion = idenQuestions[idenQuestionIndex]

        val quizImage = binding.quizImage

        quizImage.visibility = View.VISIBLE

        when (idenQuestionIndex) {
            0 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz2_q6)
            1 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz2_q7)
            2 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz2_q8)
            3 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz2_q9)
            4 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz2_q10)
            else -> quizImage.setImageResource(0)
        }

        questionText = currentIdenQuestion.text
        // randomize the answers into a copy of the array

        enteredAns = currentIdenQuestion.enteredAns
    }

}