package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentReferencesBinding



class References : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentReferencesBinding>(inflater,R.layout.fragment_references,container,false)
        binding.button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_references2_to_mainMenuFragment)
        }
        return binding.root
    }


}