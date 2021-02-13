package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.eim_coursepack.databinding.FragmentUnit1Quiz1IdentificationBinding


class Unit1Quiz1IdentificationFragment : Fragment() {

    data class Question(
        val text: String,
        val answers: List<String>)

    private val questions: MutableList<Unit1Quiz1MCFragment.Question> = mutableListOf(
        Unit1Quiz1MCFragment.Question(
            text = "What is Android Jetpack?",
            answers = listOf("tools", "tool", "documentation", "library", "libraries")
        ),
        Unit1Quiz1MCFragment.Question(
            text = "What is the base class for layouts?",
            answers = listOf("ViewGroup", "viewgroup")
        ),
        Unit1Quiz1MCFragment.Question(
            text = "What layout do you use for complex screens?",
            answers = listOf("ConstraintLayout", "constraintlayout")
        )
    )

    lateinit var currentQuestion: Unit1Quiz1MCFragment.Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = questions.size
    private val score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit1Quiz1IdentificationBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_quiz1_identification, container, false)

        initQuestions()

        binding.quiz = this

        val answerText = binding.answerText.text

        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view : View ->
            if (answerText.isNotEmpty()) {
                if (answerText.toString() in currentQuestion.answers) {
                    score.plus(1)

                    // for checking only (delete)
                    Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
                }

                questionIndex++
                // Advance to the next question

                if (questionIndex < numQuestions) {
                    currentQuestion = questions[questionIndex]
                    setQuestion()
                    binding.invalidateAll()
                    answerText.clear()
                } else {
                    // move to eval score fragment
                }

            } else {
                Toast.makeText(context,"Type your answer",Toast.LENGTH_SHORT).show()
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
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
//        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }

}