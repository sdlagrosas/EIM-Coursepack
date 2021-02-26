package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.eim_coursepack.databinding.FragmentAllLessonBinding



class AllLessonFragment : Fragment() {
    private lateinit var lessonNickname: LessonNickname

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentAllLessonBinding>(
            inflater,
            R.layout.fragment_all_lesson,
            container,
            false
        )
        val args = AllLessonFragmentArgs.fromBundle(requireArguments())
        val Unit1Lesson1 = binding.Unit1Lesson1pdf
        val Unit1Lesson2 = binding.Unit1Lesson2pdf
        val Unit1Lesson3 = binding.Unit1Lesson3pdf




        when (args.lessonFlag) {
            "Lesson1" -> {
                Unit1Lesson1.visibility=View.VISIBLE
                lessonNickname=LessonNickname("Lesson 1.1")
                binding.lessonNickname= lessonNickname
                Unit1Lesson1.fromAsset("unit1lesson1.pdf")
                    .password(null)
                    .defaultPage(0)
                    .load()

            }
            "Lesson2" -> {
                Unit1Lesson2.visibility=View.VISIBLE
                lessonNickname=LessonNickname("Lesson 1.2")
                binding.lessonNickname= lessonNickname
                //change pdf
                Unit1Lesson2.fromAsset("sources_of_energy.pdf")
                    .password(null)
                    .defaultPage(0)
                    .load()
            }
//more lessons Soon
            else -> {
                Unit1Lesson3.visibility=View.VISIBLE
                lessonNickname=LessonNickname("Lesson 1.3")
                binding.lessonNickname= lessonNickname
                //change pdf
                Unit1Lesson3.fromAsset("unit1lesson1.pdf")
                    .password(null)
                    .defaultPage(0)
                    .load()

            }

        }


        return binding.root
    }


}