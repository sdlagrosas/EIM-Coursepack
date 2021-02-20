package com.example.eim_coursepack

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit1Quiz1MCBinding


class Unit1Quiz1MCFragment : Fragment() {

    private lateinit var binding : FragmentUnit1Quiz1MCBinding

    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "1. The same electrical charge ___________ each other.",
            answers = listOf("repel", "attracts", "destroy", "neutralize"),
            isCorrect = false),
        Question(text = "2. It is neither positively nor negatively charged.",
            answers = listOf("neutron", "electron in motion", "electrostatic force", "atom"),
            isCorrect = false),
        Question(text = "3. It is the equal number of electron and proton in an atom.",
            answers = listOf("neutral", "positive", "negative", "none of the choices"),
            isCorrect = false),
        Question(text = "4. The electron theory states that all matter is made of ________________.",
            answers = listOf("electron", "neutron", "atom", "molecules"),
            isCorrect = false),
        Question(text = "5. It is the smallest particle of molecule.",
            answers = listOf("atom", "ion", "proton", "electron"),
            isCorrect = false),
        Question(text = "6. What is found at the center body of an atom?",
            answers = listOf("nucleus", "atom", "proton", "electron"),
            isCorrect = false),
        Question(text = "7. How will you prove that electricity is a matter?",
            answers = listOf(
                "It occupies space",
                "It is not seen by the naked eye",
                "It travels along insulator materials",
                "It has weight"),
            isCorrect = false
        ),
        Question(text = "8. Movement of electrons from one atom to another atom is ______.",
            answers = listOf("electricity", "resistance", "flow", "voltage"),
            isCorrect = false),
        Question(text = "9. The flow of electrons in a material is _____.",
            answers = listOf("electric current", "resistance", "circuit", "voltage"),
            isCorrect = false),
        Question(text = "10. How do you describe the electrostatic force in an atom?",
            answers = listOf(
                "When attraction occurs between nucleus and electron",
                "When the proton and electron contracts",
                "When proton and electron attracts inside the atom",
                "When separation occurs between the nucleus and proton"),
            isCorrect = false
        ),
        Question(
            text = "11. It contains the positive and neutral charged of an atom.",
            answers = listOf("nucleus","proton","electron","neutron"),
            isCorrect = false
        ),
        Question(
            text = "12. This refers to the attraction between the nucleus and the electron.",
            answers = listOf("electrostatic force", "electricity", "current flow", "voltage"),
            isCorrect = false
        ),
        Question(
            text = "13. The particle which has equal charged to electron",
            answers = listOf("proton", "neutron", "atom", "boson"),
            isCorrect = false
        ),
        Question(
            text = "14. Particle which found in the outer orbit of an atom.",
            answers = listOf("electron", "proton", "neutron", "neutrino"),
            isCorrect = false
        ),
        Question(
            text = "15. Particle of an atom without electrical charge.",
            answers = listOf("neutron", "neutrino", "proton", "electron"),
            isCorrect = false
        )

    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = questions.size
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_unit1_quiz1_m_c, container, false)

//        val sharedPref = this.activity?.getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE)

        // Initialize questions and set the first question
        initQuestions()

        binding.quiz = this

        // Set the onClickListener for the nextButton
        binding.nextButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            handleQuizProper(view)

            if (questionIndex > 0) {
                binding.backButton.visibility = View.VISIBLE
            }

            if (questionIndex == numQuestions-1) {
                binding.nextButton.visibility = View.INVISIBLE
                binding.submitButton.visibility = View.VISIBLE
            }

            if (questionIndex > 9) {
                binding.questionRadioGroup.visibility = View.GONE
                binding.answerText.visibility = View.VISIBLE
            }


        }

        // Set the onClickListener for the nextButton
        binding.backButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->

            // move to previous question
            currentQuestion.isCorrect = false
            questionIndex--
            currentQuestion = questions[questionIndex]
            setQuestion()

            // reset fields
            binding.invalidateAll()
            binding.questionRadioGroup.clearCheck()
            binding.answerText.text.clear()

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

            if (questionIndex in 0 until numQuestions-1) {
                binding.nextButton.visibility = View.VISIBLE
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(view : View) {
        if (questionIndex < 10) {

            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondChoiceRadioButton -> answerIndex = 1
                    R.id.thirdChoiceRadioButton -> answerIndex = 2
                    R.id.fourthChoiceRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    // Add score here
                    currentQuestion.isCorrect = true
                }
                // Advance to the next question
                questionIndex++
                currentQuestion = questions[questionIndex]
                setQuestion()
                binding.invalidateAll()
                binding.questionRadioGroup.clearCheck()


                binding.backButton.visibility = View.VISIBLE



            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }

        } else if (questionIndex in 10 until numQuestions) {
            Toast.makeText(context, "11-15!!!!! Index: $questionIndex", Toast.LENGTH_SHORT).show()

            val answerText = binding.answerText.text

            if (answerText.isNotEmpty()) {
                if (answerText.toString().toLowerCase() in currentQuestion.answers) {
                    currentQuestion.isCorrect = true
                }

                // Advance to the next question
                questionIndex++
                currentQuestion = questions[questionIndex]
                setQuestion()

                // reset fields
                binding.invalidateAll()
                answerText.clear()


            } else {
                Toast.makeText(context,"Enter your answer",Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Error index out of range: $questionIndex", Toast.LENGTH_LONG).show()
            // Navigate to score fragment
        }
    }

    // Initialize the questions and set the first question
    private fun initQuestions() {
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
//        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }


}