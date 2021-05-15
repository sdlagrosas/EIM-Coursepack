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
import com.example.eim_coursepack.databinding.FragmentUnit1Quiz2Binding
import com.example.eim_coursepack.databinding.FragmentUnit2Binding
import com.example.eim_coursepack.databinding.FragmentUnit2Quiz1Binding


class Unit2Quiz1Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. This is used for griping, holding, cutting electrical wires and cables and even small nails. Usually used by linemen in doing heavy tasks.",
            answers = mutableListOf(
                "Combination Pliers",
                "Screwdriver",
                "Fuse ",
                "Switch"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "2. Used for cutting and holding fine wires. This can reach tight space or small opening where other pliers cannot reach and also used in making terminal loops of copper wires.",
            answers = mutableListOf(
                "Long Nose Pliers",
                "Phillip Screwdriver",
                "Fuse",
                "Switch"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "3. This has a cross tip resembling a positive (+) sign. It is used to drive screws with cross slot heads.",
            answers = mutableListOf(
                "Phillip Screwdriver",
                "Fuse",
                "Long Nose Pliers",
                "Switch"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "4. This is tools used in driving or pounding and pulling out nails.",
            answers = mutableListOf(
                "Hammer",
                "Long Nose Pliers",
                "Switch",
                "Phillip Screwdriver"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "5. A tool used for removing insulation of medium sized wires ranging from gauge #10 to gauge #16.",
            answers = mutableListOf(
                "Wire Stripper",
                "Hammer",
                "Switch",
                "Phillip Screwdriver"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "6. Identify the tool in the figure above.",
            answers = mutableListOf("pull-push rule", "pull-push ruler", "push-pull rule", "push-pull ruler"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "7. Identify the tool in the figure above.",
            answers = mutableListOf("hammer"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "8. Identify the tool in the figure above.",
            answers = mutableListOf("multimeter"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "9. Identify the tool in the figure above.",
            answers = mutableListOf("allen screwdriver"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "10. Identify the tool in the figure above.",
            answers = mutableListOf("wire gauge tool", "wire measuring tool"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "11. Identify the tool in the figure above.",
            answers = mutableListOf("ruler", "rule"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. Identify the tool in the figure above.",
            answers = mutableListOf("wire stripper"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. Identify the tool in the figure above.",
            answers = mutableListOf("combination pliers", "lineman's pliers"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. Identify the tool in the figure above.",
            answers = mutableListOf("standard screwdriver", "flat screwdriver"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Identify the tool in the figure above.",
            answers = mutableListOf("micro caliper", "micrometer caliper"),
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
        val binding : FragmentUnit2Quiz1Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit2_quiz1, container, false
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

            // Hide radio group and show edit text in identification part of the quiz
            if (questionIndex > mulChoQuestions.size-1) {
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


            if (questionIndex in 0 until mulChoQuestions.size) {
                currentMulChoQuestion = mulChoQuestions[questionIndex]
                binding.questionRadioGroup.clearCheck()

                setMulChoQuestion(binding)

            } else if (questionIndex in mulChoQuestions.size until numQuestions) {
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
            val answerText = binding.answerText.text.toString()
            currentIdenQuestion.isCorrect = answerText
                .toLowerCase()
                .replace("\\s+".toRegex(), " ")
                .trim() in currentIdenQuestion.answers

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

                // Update if new score > prev score
                val prevScore = sharedPref?.getInt("unit2Quiz1Score", 0)!!
                if (prevScore < score) {
                    // Save score and number of questions in shared preferences
                    with (sharedPref?.edit()) {
                        this?.putInt("unit2Quiz1Score", score)
                        this?.putInt("unit2Quiz1NumQuestions", numQuestions)
                        this?.apply()
                    }
                }

                // Navigate to score screen
                view.findNavController().navigate(
                    Unit2Quiz1FragmentDirections
                        .actionUnit2Quiz1FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 2: Lesson 1 Quiz"
                        ))
            }


        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit2Quiz1Binding) {

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
                    .toString().toLowerCase().trim() in currentIdenQuestion.answers

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
    private fun initQuestions(binding : FragmentUnit2Quiz1Binding) {
        questionIndex = 0

        mulChoQuestions.forEach {
            val correct = it.answers[0]

            it.answers.shuffle()

            it.correctIdx = it.answers.indexOf(correct)
        }

        enteredAns = ""

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit2Quiz1Binding) {
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

    private fun setIdenQuestion(binding: FragmentUnit2Quiz1Binding) {
        val idenQuestionIndex = questionIndex - mulChoQuestions.size
        currentIdenQuestion = idenQuestions[idenQuestionIndex]

        binding.quizImage.visibility = View.VISIBLE

        when (idenQuestionIndex) {
            0 -> binding.quizImage.setImageResource(R.drawable.ic_unit2_quiz1_q6)
            1 -> binding.quizImage.setImageResource(R.drawable.ic_unit2_quiz1_q7)
            2 -> binding.quizImage.setImageResource(R.drawable.ic_unit2_quiz1_q8)
            3 -> binding.quizImage.setImageResource(R.drawable.ic_unit2_quiz1_q9)
            4 -> binding.quizImage.setImageResource(R.drawable.ic_unit2_quiz1_q10)
            5 -> binding.quizImage.setImageResource(R.drawable.ic_unit2_quiz1_q11)
            6 -> binding.quizImage.setImageResource(R.drawable.ic_unit2_quiz1_q12)
            7 -> binding.quizImage.setImageResource(R.drawable.ic_unit2_quiz1_q13)
            8 -> binding.quizImage.setImageResource(R.drawable.ic_unit2_quiz1_q14)
            9 -> binding.quizImage.setImageResource(R.drawable.ic_unit2_quiz1_q15)
        }

        questionText = currentIdenQuestion.text
        // randomize the answers into a copy of the array

        enteredAns = currentIdenQuestion.enteredAns
    }

}