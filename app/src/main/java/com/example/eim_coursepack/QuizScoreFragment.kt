package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentQuizScoreBinding


class QuizScoreFragment : Fragment() {

    lateinit var scoreText : String
    lateinit var quizTitle : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentQuizScoreBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_quiz_score, container, false)

        val args = QuizScoreFragmentArgs.fromBundle(requireArguments())

        binding.quiz = this

        scoreText = "${args.numCorrect} / ${args.numQuestions}"

        quizTitle = args.quizTitle

        val unitNo = quizTitle[5]

        val text = quizTitle[5]

        Toast.makeText(context, "$text!", Toast.LENGTH_LONG).show()

        var unitFragment = QuizScoreFragmentDirections.actionQuizScoreFragmentToUnit1Fragment()
        when(unitNo) {
            '2' -> unitFragment = QuizScoreFragmentDirections.actionQuizScoreFragmentToUnit2Fragment()
            '3' -> unitFragment = QuizScoreFragmentDirections.actionQuizScoreFragmentToUnit3Fragment()
            '4' -> unitFragment = QuizScoreFragmentDirections.actionQuizScoreFragmentToUnit4Fragment()
            '5' -> unitFragment = QuizScoreFragmentDirections.actionQuizScoreFragmentToUnit5Fragment()
            '6' -> unitFragment = QuizScoreFragmentDirections.actionQuizScoreFragmentToUnit6Fragment()
        }

        binding.returnButton.setOnClickListener { view : View ->
            view.findNavController().navigate(unitFragment)
        }

        return binding.root
    }
}