package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.returnButton.setOnClickListener { view : View ->
            view.findNavController().navigate(
                QuizScoreFragmentDirections.actionQuizScoreFragmentToUnit1Fragment()
            )
        }

        return binding.root
    }
}