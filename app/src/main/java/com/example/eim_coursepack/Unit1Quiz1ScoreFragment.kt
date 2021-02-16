package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit1Quiz1ScoreBinding


class Unit1Quiz1ScoreFragment : Fragment() {

    lateinit var scoreText : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentUnit1Quiz1ScoreBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_quiz1_score, container, false)

        val args = Unit1Quiz1ScoreFragmentArgs.fromBundle(requireArguments())

        binding.quiz = this

        scoreText = "${args.numCorrect} / ${args.numQuestions}"

        binding.returnButton.setOnClickListener { view : View ->
            view.findNavController().navigate(
                Unit1Quiz1ScoreFragmentDirections.actionUni1Quiz1ScoreFragmentToUnit1Lesson1()
            )
        }

        return binding.root
    }
}