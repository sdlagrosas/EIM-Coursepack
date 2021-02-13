package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentMainMenuBinding


class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentMainMenuBinding>(inflater,R.layout.fragment_main_menu,container,false)

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
            view.findNavController().navigate(R.id.action_mainMenuFragment_to_unit6)
        }

        return binding.root
    }

}