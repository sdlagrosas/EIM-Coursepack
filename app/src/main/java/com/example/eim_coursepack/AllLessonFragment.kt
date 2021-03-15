package com.example.eim_coursepack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentAllLessonBinding



class AllLessonFragment : Fragment() {
    lateinit var lessonNickname: LessonNickname

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
            "Unit1Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 1.1")
                pdfAssetName = "unit1lesson1.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit1Quiz1MCFragment()
            }
            "Unit1Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 1.2")
                pdfAssetName = "unit1lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit1Quiz2Fragment()
            }
            "Unit1Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 1.3")
                pdfAssetName = "unit1Lesson3.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit1Quiz3Fragment()
            }
            // Unit 2
            "Unit2Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 2.1")
                pdfAssetName = "unit2lesson1.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit2Quiz1Fragment()
            }
            //Unit 3
            "Unit3Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 3.1")
                pdfAssetName = "unit3lesson1.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit3Quiz1()
            }
            "Unit3Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 3.2")
                pdfAssetName = "unit3lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit3Quiz2Fragment()
            }
            "Unit3Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 3.3")
                pdfAssetName = "unit3lesson3.pdf"
//                quizFragment = AllLessonFragmentDirections
//                    .actionAllLessonFragmentToUnit3Quiz3Fragment()
            }
            "Unit3Lesson4" -> {
                lessonNickname = LessonNickname("Lesson 3.4")
                pdfAssetName = "unit3lesson4.pdf"
//                quizFragment = AllLessonFragmentDirections
//                    .actionAllLessonFragmentToUnit3Quiz3Fragment()
            }
            //Unit 4
            "Unit4Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 4.1")
                pdfAssetName = "unit4lesson1.pdf"
//                quizFragment = AllLessonFragmentDirections
//                    .actionAllLessonFragmentToUnitQuiz1Fragment()
            }
            "Unit4Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 4.2")
                pdfAssetName = "unit4lesson2.pdf"
//                quizFragment = AllLessonFragmentDirections
//                    .actionAllLessonFragmentToUnit4Quiz2Fragment()
            }
            "Unit4Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 4.3")
                pdfAssetName = "unit4lesson3.pdf"
//                quizFragment = AllLessonFragmentDirections
//                    .actionAllLessonFragmentToUnit4Quiz3Fragment()
            }

        }

        // Load pdf to PDFView
        unitLessonView.fromAsset(pdfAssetName)
            .password(null)
            .defaultPage(0)
            .load()

        // Click listener for takeQuizButton
        binding.takeQuizButton.setOnClickListener { view : View ->

            // Advance to quiz fragment
            view.findNavController().navigate(quizFragment)
        }


        return binding.root
    }


}