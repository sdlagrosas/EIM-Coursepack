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

class Unit1Quiz2Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. The following are the sources of energy except:",
            answers = mutableListOf("transformer", "sun", "nuclear reaction", "fossil fuel"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "2. Which is a nonrenewable source of energy?",
            answers = mutableListOf("fossil fuel", "solar energy", "tidal energy", "wind energy"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "3. Which is non-conventional source of energy?",
            answers = mutableListOf("gasoline", "fossil fuel", "solar energy", "hydroelectric power"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "4. The Makiling-Banahaw Plant in Laguna is an example of _______.",
            answers = mutableListOf("hydroelectric power plant", "nuclear power plant", "geothermal plant", "fossil-fuel-fired plant"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "5. What source of energy In Bicol Region since Mt. Mayon is in their area?",
            answers = mutableListOf("geothermal energy", "fossil fuel", "solar energy", "wind energy"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "6. Two metals bounded together in junction by thermocouple process is _____.",
            answers = mutableListOf("Thermoelectricity", "Friction", "Heat Action", "Squeezed Pressure"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "7. Electricity generated by rubbing two materials.",
            answers = mutableListOf(
                "Friction",
                "Heat Action",
                "Thermoelectricity",
                "Squeezed Pressure"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(text = "8. Electricity produced by batteries.",
            answers = mutableListOf(
                "Chemical Action",
                "Thermoelectricity",
                "Squeezed Pressure",
                "Friction"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "9. A body having the property of polarity and induction of attraction and repulsion found in the nature.",
            answers = mutableListOf(
                "Magnet",
                "Chemical Action",
                "Friction",
                "Mechanical Action"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "10. Electricity produced by a rotating machine",
            answers = mutableListOf(
                "Mechanical Action",
                "Magnet",
                "Chemical Action",
                "Friction"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "11. It is the energy that comes from the sun.",
            answers = mutableListOf("solar", "solar energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. It is the energy that involves water.",
            answers = mutableListOf("tidal", "tidal energy", "hydroelectric", "hydroelectric power", "hydroelectric energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. It is the energy that comes from the inner core of the earth",
            answers = mutableListOf("geothermal", "geothermal energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. It is the result from the splitting or fission of atomic nuclei.",
            answers = mutableListOf("nuclear", "nuclear energy"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. It is the energy formed from the remains of plant and animals which lived thousands of years ago.",
            answers = mutableListOf("fossil fuel", "fossil fuels"),
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
    ): View {
        // Inflate the layout for this fragment
        val binding : FragmentUnit1Quiz2Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_quiz2, container, false)

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

                setMulChoQuestion(binding)

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
                val prevScore = sharedPref?.getInt("unit1Quiz2Score", 0)!!
                if (prevScore < score) {
                    // Save score and number of questions in shared preferences
                    with (sharedPref?.edit()) {
                        this?.putInt("unit1Quiz2Score", score)
                        this?.putInt("unit1Quiz2NumQuestions", numQuestions)
                        this?.apply()
                    }
                }

                // Navigate to score screen
                view.findNavController()
                    .navigate(Unit1Quiz2FragmentDirections
                        .actionUnit1Quiz2FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 1: Lesson 2 Quiz"
                        ))
            }


        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit1Quiz2Binding) {

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
                    setMulChoQuestion(binding)

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
                    .toString()
                    .toLowerCase()
                    .replace("\\s+".toRegex(), " ")
                    .trim() in currentIdenQuestion.answers

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
                Toast.makeText(context,"Enter your answer",Toast.LENGTH_SHORT).show()
            }
        } else {
            // For error checking only
            Toast.makeText(context, "Error index out of range: $questionIndex", Toast.LENGTH_LONG).show()
        }
    }

    // Initialize the questions and set the first question
    private fun initQuestions(binding : FragmentUnit1Quiz2Binding) {
        questionIndex = 0

        mulChoQuestions.forEach {
            val correct = it.answers[0]

            it.answers.shuffle()

            it.correctIdx = it.answers.indexOf(correct)
        }

        enteredAns = ""

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit1Quiz2Binding) {
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
        currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]

        questionText = currentIdenQuestion.text
        // randomize the answers into a copy of the array

        enteredAns = currentIdenQuestion.enteredAns
    }

}