package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit1Quiz2ScoreBinding

class Unit1Quiz2ScoreFragment : Fragment() {

    lateinit var scoreText : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit1Quiz2ScoreBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit1_quiz2_score, container, false
        )

        val args = Unit1Quiz2ScoreFragmentArgs.fromBundle(requireArguments())

        binding.quiz = this

        scoreText = "${args.numCorrect} / ${args.numQuestions}"

        binding.returnButton.setOnClickListener { view : View ->
            view.findNavController().navigate(
                Unit1Quiz2ScoreFragmentDirections.actionUnit1Quiz2ScoreFragmentToUnit1Fragment()
            )
        }

        return binding.root
    }


}