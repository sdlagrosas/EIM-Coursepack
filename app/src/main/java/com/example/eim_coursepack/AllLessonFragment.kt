package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentAllLessonBinding
import com.example.eim_coursepack.databinding.FragmentUnit2Quiz3Binding


class AllLessonFragment : Fragment() {
    lateinit var lessonNickname: LessonNickname

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        this.activity?.actionBar?.hide()

        val binding = DataBindingUtil.inflate<FragmentAllLessonBinding>(
            inflater,
            R.layout.fragment_all_lesson,
            container,
            false
        )

        binding.lesson = this

        val args = AllLessonFragmentArgs.fromBundle(requireArguments())
        val unitLessonView = binding.unitLessonPDF

        // Default lesson nickname
        lessonNickname = LessonNickname("Lesson 1.1")

        // Default pdf Asset is unit1lesson1.pdf
        var pdfAssetName = "unit1lesson1.pdf"

        // Default quiz fragment is quiz 1
        var quizFragment = AllLessonFragmentDirections.actionAllLessonFragmentToUnit1Quiz1MCFragment()

        // Otherwise, select another PDF asset and quiz fragment, and replace lesson name
        when (args.lessonFlag) {
            "Unit1Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 1.2")
                pdfAssetName = "unit1lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit1Quiz2Fragment()
            }
            "Unit1Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 1.3")
                pdfAssetName = "unit1lesson3.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit1Quiz3Fragment()
            }
            "Unit2Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 2.1")
                pdfAssetName = "unit2lesson1.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit2Quiz1Fragment()
            }
            "Unit2Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 2.2")
                pdfAssetName = "unit2lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit2Quiz2Fragment()
            }
            "Unit2Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 2.3")
                pdfAssetName = "unit2lesson3.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit2Quiz3Fragment()
            }

            "Unit4Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 4.1")
                pdfAssetName = "unit4lesson1.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit4Quiz1Fragment()
            }
            "Unit4Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 4.2")
                pdfAssetName = "unit4lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit4Quiz2Fragment()
            }
            "Unit4Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 4.3")
                pdfAssetName = "unit4lesson3.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit4Quiz3Fragment()
            }
            "Unit6Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 6.1")
                pdfAssetName = "unit6lesson1.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit6Quiz1Fragment()
            }
            "Unit6Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 6.2")
                pdfAssetName = "unit6lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit6Quiz2Fragment()
            }
            "Unit6Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 6.3")
                pdfAssetName = "unit6lesson3.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit6Quiz3Fragment()
            }
        }

        // Load pdf to PDFView
        unitLessonView.fromAsset(pdfAssetName)
            .password(null)
            .defaultPage(0)
            .load()

        // Click listener for takeQuizButton
        binding.takeQuizButton.setOnClickListener { view : View ->

            this.activity?.actionBar?.show()
//          Advance to quiz fragment
            view.findNavController().navigate(quizFragment)

            // For testing quiz fragment only
//            view.findNavController().navigate(
//                AllLessonFragmentDirections.actionAllLessonFragmentToUnit4Quiz3Fragment()
//            )
        }


        return binding.root
    }


}