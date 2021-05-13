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
import com.example.eim_coursepack.databinding.FragmentUnit2Quiz3Binding
import com.example.eim_coursepack.databinding.FragmentUnit4Quiz1Binding

class Unit4Quiz1Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. These are small drawings or " +
                "pictograms used to represent various electrical devices " +
                "in a diagram or plan of an electrical circuit.",
            answers = mutableListOf(
                "Electrical Diagrams",
                "Electrical Symbols",
                "Electrical Sings",
                "Electrical Plan"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "2. The following are electrical symbol " +
                "except ______.",
            answers = mutableListOf(
                "",
                "",
                "",
                ""),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "3. The following are electrical symbol " +
                "of measuring devices, except _______.",
            answers = mutableListOf(
                "",
                "",
                "",
                ""),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "4. Which of the following represents Voltage Danger?",
            answers = mutableListOf(
                "",
                "",
                "",
                ""),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "5. Reading manual before operating the " +
                "machinery helps to avoid accident that can cause minor or " +
                "major injury, which of the following represents the " +
                "mentioned Electrical sign?",
            answers = mutableListOf(
                "",
                "",
                "",
                ""),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "6. Identify the electrical symbol",
            answers = mutableListOf("switch"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "7. Identify the electrical symbol",
            answers = mutableListOf("incandescent lamp", "incandescent light"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "8. Identify the electrical symbol",
            answers = mutableListOf("push button"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "9. Identify the electrical symbol",
            answers = mutableListOf("battery"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "10. Identify the electrical symbol",
            answers = mutableListOf("wires connected"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "11. Identify an electrical sign in the crossword above. Note: Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. Identify an electrical sign in the crossword above. Note: Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. Identify an electrical sign in the crossword above. Note: Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. Identify an electrical sign in the crossword above. Note: Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Identify an electrical sign in the crossword above. Note: Duplicate " +
                    "answers are not counted.",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        )
    )

    private val crossWordAnswerSet : MutableList<String> = mutableListOf(
        "prohibition",
        "caution",
        "voltage danger",
        "safety alert",
        "warning"
    )

    private val imageSets : List<List<Int>> = listOf(
        listOf(
            R.drawable.ic_unit4_quiz1_q2_choice1,
            R.drawable.ic_unit4_quiz1_q2_choice2,
            R.drawable.ic_unit4_quiz1_q2_choice3,
            R.drawable.ic_unit4_quiz1_q2_choice4,
        ),
        listOf(
            R.drawable.ic_unit4_quiz1_q3_choice1,
            R.drawable.ic_unit4_quiz1_q3_choice2,
            R.drawable.ic_unit4_quiz1_q3_choice3,
            R.drawable.ic_unit4_quiz1_q3_choice4,
        ),
        listOf(
            R.drawable.ic_unit4_quiz1_q4_choice1,
            R.drawable.ic_unit4_quiz1_q4_choice2,
            R.drawable.ic_unit4_quiz1_q4_choice3,
            R.drawable.ic_unit4_quiz1_q4_choice4,
        ),
        listOf(
            R.drawable.ic_unit4_quiz1_q4_choice1,
            R.drawable.ic_unit4_quiz1_q4_choice2,
            R.drawable.ic_unit4_quiz1_q4_choice3,
            R.drawable.ic_unit4_quiz1_q4_choice4,
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
        val binding : FragmentUnit4Quiz1Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit4_quiz1, container, false
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

            currentIdenQuestion.enteredAns = binding.answerText.text.toString()



            // To make sure button only works once
            if (score == 0) {



                // Count each correct answer
                mulChoQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }
                idenQuestions.slice(0 until 5).forEach {
                    if (it.isCorrect){
                        score++
                    }
                }
                idenQuestions.slice(5 until 10).forEach {
                    if (it.enteredAns
                            .toLowerCase()
                            .replace("\\s+".toRegex(), " ")
                            .trim() in crossWordAnswerSet) {
                        crossWordAnswerSet.remove(it.enteredAns)
                        score++
                    }
                }

                // Update if new score > prev score
                val prevScore = sharedPref?.getInt("unit4Quiz1Score", 0)!!
                if (prevScore < score) {
                    // Save score and number of questions in shared preferences
                    with (sharedPref?.edit()) {
                        this?.putInt("unit4Quiz1Score", score)
                        this?.putString("unit4Quiz1NumQuestions", numQuestions.toString())
                        this?.apply()
                    }
                }

                // Navigate to score screen
                view.findNavController().navigate(
                    Unit4Quiz1FragmentDirections
                        .actionUnit4Quiz1FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 4: Lesson 1 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit4Quiz1Binding) {

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

                if (questionIndex in 5 until 10) {
                    currentIdenQuestion.isCorrect = answerText
                        .toString()
                        .toLowerCase()
                        .replace("\\s+".toRegex()," ")
                        .trim() in currentIdenQuestion.answers

                    if (currentIdenQuestion.isCorrect) {
                        Toast.makeText(context, "CORRECT! Score:", Toast.LENGTH_SHORT).show()
                    }
                }




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
    private fun initQuestions(binding : FragmentUnit4Quiz1Binding) {
        questionIndex = 0

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit4Quiz1Binding) {
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text
        // randomize the answers into a copy of the array
        answers = currentMulChoQuestion.answers

        setQuestionImage(binding)

        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

        setImageChoices(binding)

    }

    private fun setImageChoices(binding: FragmentUnit4Quiz1Binding) {
        if(questionIndex in 1 until mulChoQuestions.size) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                binding.firstChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, imageSets[questionIndex-1][0], 0
                )
                binding.secondChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, imageSets[questionIndex-1][1], 0
                )
                binding.thirdChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, imageSets[questionIndex-1][2], 0
                )
                binding.fourthChoiceRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, imageSets[questionIndex-1][3], 0
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

    private fun setIdenQuestion(binding: FragmentUnit4Quiz1Binding) {
        val idenQuestionIndex = questionIndex - mulChoQuestions.size
        currentIdenQuestion = idenQuestions[idenQuestionIndex]

        setQuestionImage(binding)

        questionText = currentIdenQuestion.text
        // randomize the answers into a copy of the array

        enteredAns = currentIdenQuestion.enteredAns
    }

    private fun setQuestionImage(binding: FragmentUnit4Quiz1Binding) {
        val quizImage = binding.quizImage
        quizImage.adjustViewBounds = true

        when (questionIndex) {
            5 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz1_q6)
            6 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz1_q7)
            7 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz1_q8)
            8 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz1_q9)
            9 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz1_q10)
            in 10 until 15 -> quizImage.setImageResource(R.drawable.ic_unit4_quiz1_q11to15)
            else -> quizImage.setImageResource(0)
        }
    }

}