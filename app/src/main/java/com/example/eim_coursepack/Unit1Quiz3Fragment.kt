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
import com.example.eim_coursepack.databinding.FragmentUnit1Quiz3Binding


class Unit1Quiz3Fragment : Fragment() {

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "1. Provide the missing quantity: R=5Ω; I=2A; V=?",
            answers = mutableListOf("10", "10v", "10volts"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "2. Provide the missing quantity: I=2A; R=?",
            answers = mutableListOf("10", "10Ω", "10ohms"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "3. Provide the missing quantity: I=?; R=5Ω",
            answers = mutableListOf("2", "2a", "2amperes"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "4. Provide the missing quantity: I=500A; R=?",
            answers = mutableListOf("5", "5ohms", "5Ω"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "5. Provide the missing quantity: R=10Ω; I=0.4A; V=?",
            answers = mutableListOf("4", "4volts", "4v"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "6. Provide the missing quantity: R=25Ω; I=?",
            answers = mutableListOf("2A", "2", "2amperes"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "7. An electric heater is rated at 100V and has a hot resistance of 30 ohms. What current will flow through it? Round your answer to 2 decimal places.",
            answers = mutableListOf("3.33", "3.33a", "3.33amperes"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "8. An ammeter shows a bulb is using 4 amperes from a 120V source. What is the resistance?",
            answers = mutableListOf("30", "30Ω", "30ohms"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "9. An electric appliance with a resistance of 60 ohms must draw 5A to operate correctly. What is the correct voltage to use?",
            answers = mutableListOf("300", "300v", "300volts"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "10. How much power is consumed by the machine having a current flow of 6 ampere supplied by a 220-volt line?",
            answers = mutableListOf("1320","1320w","1320watts"),
            isCorrect = false,
            enteredAns = ""
        )

    )

    private val mulChoQuestions : MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(
            text = "11. The following are examples of conductor EXCEPT _______.",
            answers = mutableListOf(
                "Rubber gloves",
                "Nail",
                "Copper wire",
                "Silver spoon"
            ),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "12. Unit of measurement for resistance is ______.",
            answers = mutableListOf("Ω", "A", "V", "O", "hydroelectric energy"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "13. The electrical pressure which pushes the electrons to flow in a conductor material.",
            answers = mutableListOf(
                "voltage",
                "current",
                "power",
                "resistance"
            ),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "14. In the following situation, which represents the concept of resistance?",
            answers = mutableListOf(
                "Faucet is close to stop the flow of water",
                "Electric motor is turn on to push the water.",
                "Water is flowing with high intensity.",
                "There is no supply of water."
            ),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(
            text = "15. He discover the relationship of voltage, current and resistance.",
            answers = mutableListOf(
                "George Simon Ohm",
                "Andre Marie Ampere",
                "Alessandro Volta",
                "James Watt"
            ),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        )

    )

    private lateinit var currentMulChoQuestion: MulChoQuestion
    private lateinit var currentIdenQuestion : IdenQuestion
    private var questionIndex = 0
    lateinit var questionText : String
    lateinit var answers : MutableList<String>
    lateinit var enteredAns : String
    private val numQuestions = idenQuestions.size + mulChoQuestions.size
    private var score = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit1Quiz3Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_quiz3, container, false
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
            handleQuizLogic(binding)

            if (questionIndex > 0) {
                binding.backButton.visibility = View.VISIBLE
            }

            if (questionIndex == numQuestions-1) {
                binding.nextButton.visibility = View.INVISIBLE
                binding.submitButton.visibility = View.VISIBLE
            }

            if (questionIndex in 10 until numQuestions) {
                binding.questionRadioGroup.visibility = View.VISIBLE
                binding.answerText.visibility = View.GONE
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
            } else if (questionIndex in 10 until numQuestions) {

                when (binding.questionRadioGroup.checkedRadioButtonId) {
                    R.id.firstChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 0
                    R.id.secondChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 1
                    R.id.thirdChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 2
                    R.id.fourthChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 3
                }

                questionIndex--

                if (questionIndex < 10) {
                    currentIdenQuestion = idenQuestions[questionIndex]
                    setIdenQuestion(binding)
                    binding.questionRadioGroup.clearCheck()
                    binding.questionRadioGroup.visibility = View.GONE
                    binding.answerText.visibility = View.VISIBLE
                }

                else {
                    currentMulChoQuestion = mulChoQuestions[questionIndex - idenQuestions.size]
                    binding.questionRadioGroup.clearCheck()
                    setMulChoQuestion(binding)

                }


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

                // Prompt user to select an answer
            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }

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

            // Save score and number of questions in shared preferences
            with (sharedPref?.edit()) {
                this?.putString("unit1Quiz3Score", score.toString())
                this?.putString("unit1Quiz3NumQuestions", numQuestions.toString())
                this?.apply()
            }

            view.findNavController().navigate(
                Unit1Quiz3FragmentDirections
                    .actionUnit1Quiz3FragmentToQuizScoreFragment(
                        numQuestions,
                        score,
                        "Unit 1: Lesson 3 Quiz"
                    )
            )


        }

        return binding.root
    }

    private fun handleQuizLogic(binding: FragmentUnit1Quiz3Binding) {
        if (questionIndex < 10) {
            val answerText = binding.answerText.text

            if (answerText.isNotEmpty()) {
                currentIdenQuestion.isCorrect = answerText
                    .toString().toLowerCase().replace("\\s+".toRegex(),"") in currentIdenQuestion.answers


                currentIdenQuestion.enteredAns = answerText.toString()

                // Advance to the next question
                questionIndex++

                if (questionIndex in 0 until 10) {
                    currentIdenQuestion = idenQuestions[questionIndex]
                    setIdenQuestion(binding)

                } else if (questionIndex in 10 until numQuestions) {
                    currentMulChoQuestion = mulChoQuestions[questionIndex - idenQuestions.size]
                    setMulChoQuestion(binding)

                }

                // reset fields
                binding.invalidateAll()
                answerText.clear()


            } else {
                Toast.makeText(context,"Enter your answer",Toast.LENGTH_SHORT).show()
            }
        } else if (questionIndex in 10 until numQuestions) {
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



                currentMulChoQuestion.clickedIdx = answerIndex
                binding.questionRadioGroup.clearCheck()


                // Advance to the next question
                questionIndex++


                if (questionIndex in 0 until 10) {
                    currentIdenQuestion = idenQuestions[questionIndex]
                    setIdenQuestion(binding)

                } else if (questionIndex in 10 until numQuestions) {
                    currentMulChoQuestion = mulChoQuestions[questionIndex - idenQuestions.size]
                    setMulChoQuestion(binding)

                }
                binding.invalidateAll()


                // Prompt user to select an answer
            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initQuestions(binding: FragmentUnit1Quiz3Binding) {
        questionIndex = 0

        randomizeMulChoAnswers()

        currentMulChoQuestion = mulChoQuestions[0]

        setIdenQuestion(binding)
    }


    private fun setIdenQuestion(binding: FragmentUnit1Quiz3Binding) {
        currentIdenQuestion = idenQuestions[questionIndex]



        questionText = currentIdenQuestion.text

        if (questionIndex < 6) {
            binding.quiz1Image.visibility = View.VISIBLE
            when (questionIndex) {
                0 -> binding.quiz1Image.setImageResource(R.drawable.ic_unit1_quiz3_q1)
                1 -> binding.quiz1Image.setImageResource(R.drawable.ic_unit1_quiz3_q2)
                2 -> binding.quiz1Image.setImageResource(R.drawable.ic_unit1_quiz3_q3)
                3 -> binding.quiz1Image.setImageResource(R.drawable.ic_unit1_quiz3_q4)
                4 -> binding.quiz1Image.setImageResource(R.drawable.ic_unit1_quiz3_q5)
                5 -> binding.quiz1Image.setImageResource(R.drawable.ic_unit1_quiz3_q6)
            }
        } else {
            binding.quiz1Image.visibility = View.GONE

        }

        answers = currentIdenQuestion.answers

        enteredAns = currentIdenQuestion.enteredAns

    }

    private  fun setMulChoQuestion(binding: FragmentUnit1Quiz3Binding) {
        currentMulChoQuestion = mulChoQuestions[questionIndex - idenQuestions.size]

        questionText = currentMulChoQuestion.text

        answers = currentMulChoQuestion.answers

        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }


    }

    private fun randomizeMulChoAnswers() {
        mulChoQuestions.forEach {
            val correctAns = it.answers[0]
            it.answers.shuffle()
            it.correctIdx = it.answers.indexOf(correctAns)
        }
    }


}