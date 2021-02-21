package com.example.eim_coursepack

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit1Quiz1MCBinding


class Unit1Quiz1MCFragment : Fragment() {

    private lateinit var binding : FragmentUnit1Quiz1MCBinding

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. The same electrical charge ___________ each other.",
            answers = mutableListOf("repel", "attracts", "destroy", "neutralize"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "2. It is neither positively nor negatively charged.",
            answers = mutableListOf("neutron", "electron in motion", "electrostatic force", "atom"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "3. It is the equal number of electron and proton in an atom.",
            answers = mutableListOf("neutral", "positive", "negative", "none of the choices"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "4. The electron theory states that all matter is made of ________________.",
            answers = mutableListOf("electron", "neutron", "atom", "molecules"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "5. It is the smallest particle of molecule.",
            answers = mutableListOf("atom", "ion", "proton", "electron"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "6. What is found at the center body of an atom?",
            answers = mutableListOf("nucleus", "atom", "proton", "electron"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "7. How will you prove that electricity is a matter?",
            answers = mutableListOf(
                "It occupies space",
                "It is not seen by the naked eye",
                "It travels along insulator materials",
                "It has weight"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
        MulChoQuestion(text = "8. Movement of electrons from one atom to another atom is ______.",
            answers = mutableListOf("electricity", "resistance", "flow", "voltage"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "9. The flow of electrons in a material is _____.",
            answers = mutableListOf("electric current", "resistance", "circuit", "voltage"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "10. How do you describe the electrostatic force in an atom?",
            answers = mutableListOf(
                "When attraction occurs between nucleus and electron",
                "When the proton and electron contracts",
                "When proton and electron attracts inside the atom",
                "When separation occurs between the nucleus and proton"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1
        ),
//
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "11. It contains the positive and neutral charged of an atom.",
            answers = mutableListOf("nucleus"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. This refers to the attraction between the nucleus and the electron.",
            answers = mutableListOf("electrostatic force"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. The particle which has equal charged to electron",
            answers = mutableListOf("proton"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. Particle which found in the outer orbit of an atom.",
            answers = mutableListOf("electron"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Particle of an atom without electrical charge.",
            answers = mutableListOf("neutron"),
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

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_quiz1_m_c, container, false)

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
                    this?.putString("quiz1Score", score.toString())
                    this?.putString("quiz1NumQuestions", numQuestions.toString())
                    this?.apply()
                }

                // Navigate to score screen
                view.findNavController()
                    .navigate(Unit1Quiz1MCFragmentDirections
                        .actionUnit1Quiz1MCFragmentToUni1Quiz1ScoreFragment(numQuestions,score))
            }


//            Toast.makeText(context, "Score: $score/$numQuestions", Toast.LENGTH_LONG).show()
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

                if (currentMulChoQuestion.isCorrect) {
                    Toast.makeText(context, "CORRECT!", Toast.LENGTH_SHORT).show()
                }

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
                Toast.makeText(context,"Enter your answer",Toast.LENGTH_SHORT).show()
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
        // randomize the answers into a copy of the array
        answers = currentMulChoQuestion.answers.toMutableList()
        // and shuffle them

        enteredAns = currentIdenQuestion.enteredAns




    }


}