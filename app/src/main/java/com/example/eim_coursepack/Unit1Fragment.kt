package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit1Binding


class Unit1Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUnit1Binding>(inflater,R.layout.fragment_unit1,container,false)

        binding.unit1Lesson1Button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_unit1Fragment_to_unit1Lesson1)
        }

        return binding.root
    }

}