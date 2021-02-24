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

    val lesson1flag = "Lesson1"
    val lesson2flag= "Lesson2"
    val lesson3flag= "Lesson3"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentUnit1Binding>(inflater,R.layout.fragment_unit1,container,false)

        val lesson1id= binding.unit1Lesson1Button
        val lesson2id= binding.unit1Lesson2Button
        val lesson3id= binding.unit1Lesson3Button

        val clickableViews: List<View> = listOf(lesson1id,lesson2id,lesson3id)
        for (items in clickableViews) {
            items.setOnClickListener{whatButtons(it)}
        }
//        binding.unit1Lesson1Button.setOnClickListener { view : View ->
//            view.findNavController().navigate(Unit1FragmentDirections.actionUnit1FragmentToAllLessonFragment())
//        }


        return binding.root
    }


    private fun whatButtons(view: View) {
        when (view.id) {
            R.id.unit1Lesson1Button->view.findNavController().navigate(Unit1FragmentDirections.actionUnit1FragmentToAllLessonFragment(lesson1flag))
            R.id.unit1Lesson2Button->view.findNavController().navigate(Unit1FragmentDirections.actionUnit1FragmentToAllLessonFragment(lesson2flag))
            R.id.unit1Lesson3Button->view.findNavController().navigate(Unit1FragmentDirections.actionUnit1FragmentToAllLessonFragment(lesson3flag))



        }

    }


}

