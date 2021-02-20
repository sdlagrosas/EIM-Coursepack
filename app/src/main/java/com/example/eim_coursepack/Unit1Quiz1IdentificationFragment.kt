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
import com.example.eim_coursepack.databinding.FragmentUnit1Quiz1IdentificationBinding
import java.util.*


class Unit1Quiz1IdentificationFragment : Fragment() {

    data class Question(
        val text: String,
        val answers: String
        )

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "1. It contains the positive and neutral charged of an atom.",
            answers = "nucleus"
        ),
        Question(
            text = "2. This refers to the attraction between the nucleus and the electron.",
            answers = "electrostatic force"
        ),
        Question(
            text = "3. The particle which has equal charged to electron",
            answers = "proton"
        ),
        Question(
            text = "4. Particle which found in the outer orbit of an atom.",
            answers = "electron"
        ),
        Question(
            text = "5. Particle of an atom without electrical charge.",
            answers = "neutron"
        )
    )

    lateinit var currentQuestion: Question
    lateinit var answers: String
    private var questionIndex = 0
    private var numQuestions = questions.size
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit1Quiz1IdentificationBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_quiz1_identification, container, false)

        val args = Unit1Quiz1IdentificationFragmentArgs.fromBundle(requireArguments())


        val sharedPref = this.activity?.getSharedPreferences(getString(R.string.preference_key),
            Context.MODE_PRIVATE)

        score = args.numCorrect

        initQuestions()

        binding.quiz = this

        val answerText = binding.answerText1.text

        binding.button1.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view : View ->
            if (answerText.isNotEmpty()) {
                if (answerText.toString().toLowerCase() in currentQuestion.answers) {
                    score++
                }

                questionIndex++
                // Advance to the next question
                if (questionIndex < numQuestions) {
                    currentQuestion = questions[questionIndex]
                    setQuestion()
                    binding.invalidateAll()
                    answerText.clear()
                } else {
                    // save score in preferences
                    with(sharedPref?.edit()) {
                        this?.putInt("quiz1_score",score)
                        this?.apply()
                    }

                    // Add up the number of questions from multiple choice
                    numQuestions += args.numQuestions

                    view.findNavController().
                    navigate(Unit1Quiz1IdentificationFragmentDirections.
                    actionUnit1Quiz1IdentificationFragmentToUni1Quiz1ScoreFragment(numQuestions, score))
                }

            } else {
                Toast.makeText(context,"Enter your answer",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    // Initialize the questions and set the first question
    private fun initQuestions() {
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers
        // and shuffle them
//        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }

}