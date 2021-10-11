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
import com.example.eim_coursepack.databinding.FragmentMainMenuBinding


class MainMenuFragment : Fragment() {

    private val passingScore = 8
    private val enableLock = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentMainMenuBinding>(inflater,R.layout.fragment_main_menu,container,false)

        // SharedPreference Object (for accessing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

        var unitFinalQuizScore: Int

        if (enableLock) {
            binding.unit1Button.setOnClickListener { view : View ->
                view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit1Fragment)
            }
            binding.unit2Button.setOnClickListener { view : View ->
                unitFinalQuizScore = sharedPref?.getInt("unit1Quiz3Score", 0)!!

                if (unitFinalQuizScore > passingScore) {
                    view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit2Fragment)
                } else {lockedMessage()}


            }
            binding.unit3Button.setOnClickListener { view : View ->
                unitFinalQuizScore = sharedPref?.getInt("unit2Quiz4Score", 0)!!

                if (unitFinalQuizScore > passingScore) {
                    view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit3Fragment)
                } else {lockedMessage()}

            }
            binding.unit4Button.setOnClickListener { view : View ->
                unitFinalQuizScore = sharedPref?.getInt("unit3Quiz4Score", 0)!!

                if (unitFinalQuizScore > passingScore) {
                    view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit4Fragment)
                } else {lockedMessage()}
            }
            binding.unit5Button.setOnClickListener { view : View ->

                unitFinalQuizScore = sharedPref?.getInt("unit4Quiz3Score", 0)!!

                if (unitFinalQuizScore > passingScore) {
                    view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit5Fragment)
                } else {lockedMessage()}

            }
            binding.unit6Button.setOnClickListener { view : View ->
                unitFinalQuizScore = sharedPref?.getInt("unit5Quiz3Score", 0)!!

                if (unitFinalQuizScore > passingScore) {
                    view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit6Fragment)
                } else {lockedMessage()}
            }
        }

        else {
            binding.unit1Button.setOnClickListener { view : View ->
                view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit1Fragment)
            }
            binding.unit2Button.setOnClickListener { view : View ->
                view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit2Fragment)
            }
            binding.unit3Button.setOnClickListener { view : View ->
                view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit3Fragment)
            }
            binding.unit4Button.setOnClickListener { view : View ->
                view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit4Fragment)
            }
            binding.unit5Button.setOnClickListener { view : View ->
                view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit5Fragment)

            }
            binding.unit6Button.setOnClickListener { view : View ->
                view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit6Fragment)
            }
        }
        binding.textView7.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_mainMenuFragment_to_references2)
        }

        return binding.root
    }

    private fun lockedMessage() {
        Toast.makeText(context,
            "Reach a passing score (8+/15) on the previous unit's final quiz to proceed.",
            Toast.LENGTH_LONG).show()
    }

}