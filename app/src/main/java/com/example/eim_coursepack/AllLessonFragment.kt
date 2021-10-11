package com.example.eim_coursepack

import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentAllLessonBinding



class AllLessonFragment : Fragment() {
    lateinit var lessonNickname: LessonNickname
    private val videoFlag: List<String> = listOf(
        "FirstVid",
        "SecondVid",
        "ThirdVid",
        "FourthVid",
        "FifthVid",
        "SixthVid",
        "SeventhVid",
        "EighthVid",
        "NinthVid"
    )

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
        var quizFragment =
            AllLessonFragmentDirections.actionAllLessonFragmentToUnit1Quiz1MCFragment()

        var VideoFragment =
            AllLessonFragmentDirections.actionAllLessonFragmentToAllVideos(videoFlag[0])


        // Otherwise, select another PDF asset and quiz fragment, and replace lesson name
        when (args.lessonFlag) {

            // Unit 1
            "Unit1Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 1.1")
                pdfAssetName = "unit1lesson1.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit1Quiz1MCFragment()
                binding.seeVideo.visibility = View.VISIBLE
                VideoFragment =
                    AllLessonFragmentDirections.actionAllLessonFragmentToAllVideos(videoFlag[0])
            }
            "Unit1Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 1.2")
                pdfAssetName = "unit1lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit1Quiz2Fragment()
                binding.seeVideo.visibility = View.VISIBLE
                VideoFragment =
                    AllLessonFragmentDirections.actionAllLessonFragmentToAllVideos(videoFlag[1])
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
            "Unit2Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 2.2")
                pdfAssetName = "unit2lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit2Quiz2Fragment()
                binding.seeVideo.visibility = View.VISIBLE
                VideoFragment =
                    AllLessonFragmentDirections.actionAllLessonFragmentToAllVideos(videoFlag[2])
            }
            "Unit2Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 2.3")
                pdfAssetName = "unit2lesson3.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit2Quiz3Fragment()
                binding.seeVideo.visibility = View.VISIBLE
                VideoFragment =
                    AllLessonFragmentDirections.actionAllLessonFragmentToAllVideos(videoFlag[3])

            }
            "Unit2Lesson4" -> {
                lessonNickname = LessonNickname("Lesson 2.4")
                pdfAssetName = "unit2lesson4.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit2Quiz4Fragment()
            }

            //Unit 3
            "Unit3Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 3.1")
                pdfAssetName = "unit3lesson1.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit3Quiz12()

            }
            "Unit3Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 3.2")
                pdfAssetName = "unit3lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit3Quiz2Fragment()
                binding.seeVideo.visibility = View.VISIBLE
                VideoFragment =
                    AllLessonFragmentDirections.actionAllLessonFragmentToAllVideos(videoFlag[4])
            }
            "Unit3Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 3.3")
                pdfAssetName = "unit3lesson3.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit3Quiz3()
                binding.seeVideo.visibility = View.VISIBLE
                VideoFragment =
                    AllLessonFragmentDirections.actionAllLessonFragmentToAllVideos(videoFlag[5])
            }
            "Unit3Lesson4" -> {
                lessonNickname = LessonNickname("Lesson 3.4")
                pdfAssetName = "unit3lesson4.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit3Quiz4()
                binding.seeVideo.visibility = View.VISIBLE
                VideoFragment =
                    AllLessonFragmentDirections.actionAllLessonFragmentToAllVideos(videoFlag[6])
            }

            //Unit 4
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
                binding.seeVideo.visibility = View.VISIBLE
                VideoFragment =
                    AllLessonFragmentDirections.actionAllLessonFragmentToAllVideos(videoFlag[7])
            }

            //Unit 5
            "Unit5Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 5.1")
                pdfAssetName = "unit5Lesson1.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit5Quiz1Fragment()
            }
            "Unit5Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 5.2")
                pdfAssetName = "unit5Lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit4Quiz2Fragment()
            }
            "Unit5Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 5.3")
                pdfAssetName = "unit5Lesson3.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit4Quiz3Fragment()
            }

            //Unit 6
            "Unit6Lesson1" -> {
                lessonNickname = LessonNickname("Lesson 6.1")
                pdfAssetName = "unit6Lesson1.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit6Quiz1Fragment()

            }
            "Unit6Lesson2" -> {
                lessonNickname = LessonNickname("Lesson 6.2")
                pdfAssetName = "unit6Lesson2.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit6Quiz2Fragment()
            }
            "Unit6Lesson3" -> {
                lessonNickname = LessonNickname("Lesson 6.3")
                pdfAssetName = "unit6Lesson3.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit6Quiz3Fragment()
            }
            "Unit6Lesson4" -> {
                lessonNickname = LessonNickname("Lesson 6.4")
                pdfAssetName = "unit6Lesson4.pdf"
                quizFragment = AllLessonFragmentDirections
                    .actionAllLessonFragmentToUnit6Quiz4Fragment()
                binding.seeVideo.visibility = View.VISIBLE
                VideoFragment =
                    AllLessonFragmentDirections.actionAllLessonFragmentToAllVideos(videoFlag[8])

            }

        }

        // Load pdf to PDFView
        unitLessonView.fromAsset(pdfAssetName)
            .password(null)
            .defaultPage(0)
            .load()




        // Click listener for takeQuizButton
        binding.takeQuizButton.setOnClickListener { view: View ->

            this.activity?.actionBar?.show()
//          Advance to quiz fragment
            view.findNavController().navigate(quizFragment)

            // For testing quiz fragment only
//            view.findNavController().navigate(
//                AllLessonFragmentDirections.actionAllLessonFragmentToUnit4Quiz3Fragment()
//            )
        }
        binding.seeVideo.setOnClickListener { view: View ->

            this.activity?.actionBar?.show()

            view.findNavController()
                .navigate(VideoFragment)
        }


        return binding.root
    }


}