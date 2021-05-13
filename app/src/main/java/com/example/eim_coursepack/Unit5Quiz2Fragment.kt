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
import com.example.eim_coursepack.databinding.FragmentUnit5Quiz1Binding
import com.example.eim_coursepack.databinding.FragmentUnit5Quiz2Binding

class Unit5Quiz2Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. It is a component of a solution that dissolves solute and is " +
            "usually present in large proportion or amount",
            answers = mutableListOf(
                "Solution",
                "Solvent",
                "Solute",
                "Oil"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "2. It is a substance introduced to lessen friction between " +
                "moving surfaces",
            answers = mutableListOf(
                "Lubricant",
                "Oil",
                "Solvent",
                "Water"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "3. PPE means _______.",
            answers = mutableListOf(
                "Personal Professional Equipment",
                "Personal Protective Equipment",
                "Protective Personal Equipment",
                "Professional Protective Equipment"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "4. Wash greasy tools/ equipment.",
            answers = mutableListOf(
                "Gasoline",
                "Kerosene",
                "Thinner",
                "Water"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "5. Polar solvents are solvents which dissolve/are soluble in " +
                "water, which of the following is Polar solvents?",
            answers = mutableListOf(
                "Gasoline",
                "Kerosene",
                "Thinner",
                "Detergent Soap"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "6. Which type of lubricant which does not damage insulation?",
            answers = mutableListOf(
                "Anti-rust lubricant spray",
                "Wire pulling lubricant",
                "All-purpose anti-rust lubricant",
                "Lubricant oil and engine oil"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "7. The following are Non Polar Solvent, except _____.",
            answers = mutableListOf(
                "Water",
                "Gasoline",
                "Kerosene",
                "Thinner"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "8. Mang Zaldy, needs to remove the spilled paint on the floor, " +
                "walls and tools. What do you think the best solvent he can use?",
            answers = mutableListOf(
                "Detergent soap",
                "Gasoline",
                "Kerosene",
                "Thinner"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "9. A good lubricant possesses the following characteristics, " +
                "except _____.",
            answers = mutableListOf(
                "High boiling point",
                "Low freezing point",
                "High viscosity index",
                "Thermal instability"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "10. Sir Doodz, wants to remove the screw which already rusted. " +
                "Which of the following is best to use?",
            answers = mutableListOf(
                "Anti-rust lubricant spray",
                "Wire pulling lubricant",
                "All-purpose anti-rust lubricant",
                "Lubricant oil and engine oil"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "11. It is used to wash greasy tools/ equipment.",
            answers = mutableListOf("gasoline"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. It is used to remove dust, grease oil, paint, etc.",
            answers = mutableListOf("kerosene"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. It is used to remove spilled paint on the floor, walls and tools.",
            answers = mutableListOf("thinner"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. It is used to wash dust in the floor, walls.",
            answers = mutableListOf("water"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. It is used to wash/clean benches, tables, cabinets, etc.",
            answers = mutableListOf("detergent soap and water", "detergent soap"),
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
        val binding : FragmentUnit5Quiz2Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit5_quiz2, container, false
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

                setIdenQuestion()
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

//            if (currentIdenQuestion.isCorrect) {
//                Toast.makeText(context, "CORRECT", Toast.LENGTH_SHORT).show()
//            }

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
                val prevScore = sharedPref?.getInt("unit5Quiz2Score", 0)!!
                if (prevScore < score) {
                    // Save score and number of questions in shared preferences
                    with (sharedPref?.edit()) {
                        this?.putInt("unit5Quiz2Score", score)
                        this?.putString("unit5Quiz2NumQuestions", numQuestions.toString())
                        this?.apply()
                    }
                }

                // Navigate to score screen
                view.findNavController().navigate(
                    Unit5Quiz2FragmentDirections
                        .actionUnit5Quiz2FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 5: Lesson 2 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit5Quiz2Binding) {

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
                    setIdenQuestion()
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

                if (questionIndex in 5 until numQuestions) {
                    currentIdenQuestion.isCorrect = answerText
                        .toString()
                        .toLowerCase()
                        .replace("\\s+".toRegex()," ")
                        .trim() in currentIdenQuestion.answers

//                    if (currentIdenQuestion.isCorrect) {
//                        Toast.makeText(context, "CORRECT!", Toast.LENGTH_SHORT).show()
//                    }
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
    private fun initQuestions(binding : FragmentUnit5Quiz2Binding) {
        questionIndex = 0

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit5Quiz2Binding) {
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

    }


    private fun setIdenQuestion() {
        val idenQuestionIndex = questionIndex - mulChoQuestions.size
        currentIdenQuestion = idenQuestions[idenQuestionIndex]

        questionText = currentIdenQuestion.text
        // randomize the answers into a copy of the array

        enteredAns = currentIdenQuestion.enteredAns
    }

}