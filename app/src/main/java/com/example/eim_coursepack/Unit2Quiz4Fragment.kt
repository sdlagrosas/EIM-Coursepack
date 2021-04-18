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
import com.example.eim_coursepack.databinding.FragmentUnit2Quiz3Binding
import com.example.eim_coursepack.databinding.FragmentUnit2Quiz4Binding

class Unit2Quiz4Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. Mang Nono the carpenter needs to borrow electric drill to " +
                "electric supply officer of the company he is working with, what particular form " +
                "he need to fill out?",
            answers = mutableListOf(
                "Borrower’s Form",
                "Inventory Form",
                "Work Order Form",
                "Requisition Slip Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "2. Mang Canor, listed the different materials needed for the job," +
                " What is the best form he needs to have complete and accurate list?",
            answers = mutableListOf(
                "Requisition Slip Form",
                "Inventory Form",
                "Work Order Form",
                "Borrower’s Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "3. Mang Jojo and other member of the group of worker, decided " +
                "to compute the possible income of the job offered, considering the time, daily " +
                "rate, the work type.",
            answers = mutableListOf(
                "Work Order Form",
                "Inventory Form",
                "Borrower’s Form",
                "Requisition Slip Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "4. Sir Enchong, wanted to check the number of condemnable tools " +
                "in his tool box and replace it with new tools for safety.",
            answers = mutableListOf(
                "Inventory Form",
                "Borrower’s Form",
                "Work Order Form",
                "Requisition Slip Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "5. Mang Elmer, wants to know the last man borrowed the item, " +
                "because upon checking tools was damaged.",
            answers = mutableListOf(
                "Borrower’s Form",
                "Inventory Form",
                "Work Order Form",
                "Requisition Slip Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "6. Manoy Jeorge, checked the payment he received from his boss, " +
                "upon checking he noticed that the payment was not tallied on his expected " +
                "amount. What form is best to check to see the accurate record.",
            answers = mutableListOf(
                "Requisition Slip Form",
                "Inventory Form",
                "Work Order Form",
                "Borrower’s Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "7. Quantity and  Unit  is found on what particular type of Form?",
            answers = mutableListOf(
                "Requisition Slip Form",
                "Inventory Form",
                "Work Order Form",
                "Borrower’s Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "8. Name and condition of the borrowed item is found in _______.",
            answers = mutableListOf(
                "Borrower’s Form",
                "Inventory Form",
                "Work Order Form",
                "Requisition Slip Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "9. In checking the unit cost  of the purchased item, what form " +
                "is best to take a look?",
            answers = mutableListOf(
                "Requisition Slip Form",
                "Inventory Form",
                "Work Order Form",
                "Borrower’s Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "10. To check the number of repairable item, what form is best " +
                "to see?",
            answers = mutableListOf(
                "Inventory Form",
                "Borrower’s Form",
                "Work Order Form",
                "Requisition Slip Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1)
        ,
        MulChoQuestion(text = "11. Total amount of the materials needed in a job is found " +
                "in ______",
            answers = mutableListOf(
                "Requisition Slip Form",
                "Inventory Form",
                "Work Order Form",
                "Borrower’s Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "12. A document generated by a user department or " +
                "storeroom-personnel to notify the purchasing department of items it needs to " +
                "order, their quantity, and the time frame",
            answers = mutableListOf(
                "Requisition Slip Form",
                "Borrower’s Form",
                "Job Order/Work Order Form",
                "Inventory Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "13. The raw materials, work-in-process goods and  completely " +
                "finished goods that are considered  to be the portion of a business's assets " +
                "that are ready or will be ready for sale",
            answers = mutableListOf(
                "Inventory of Materials Form",
                "Borrower’s Form",
                "Job Order/Work Order Form",
                "Requisition Slip Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "14. A form use to request for tools and equipment to be used " +
                "for a particular job.  is a written instruction to perform a work",
            answers = mutableListOf(
                "Borrower’s Form",
                "Inventory Form",
                "Job Order/Work Order Form",
                "Requisition Slip Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "15. A written instruction to perform a work according to specified " +
                "requirements, within specified timeframe and cost estimates.",
            answers = mutableListOf(
                "Job Order/Work Order Form",
                "Inventory Form",
                "Borrower’s Form",
                "Requisition Slip Form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1)
    )



    private lateinit var currentMulChoQuestion: MulChoQuestion
    lateinit var answers: MutableList<String>
    lateinit var questionText : String
    private var questionIndex = 0
    private val numQuestions = mulChoQuestions.size
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit2Quiz4Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit2_quiz4, container, false
        )

        // SharedPreference Object (for storing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

        // Initialize questions and set the first question
        initQuestions(binding)

        // Attach quiz variable from quiz 1 layout
        binding.quiz = this

        // Set the onClickListener for the nextButton
        binding.nextButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            // Handle quiz layout logic and scoring
            handleQuizProper(binding)

            // Show back button after 1st question
            if (questionIndex > 0) {
                binding.backButton.visibility = View.VISIBLE
            }

            // Show submit button and hide next button in last question
            if (questionIndex == numQuestions-1) {
                binding.nextButton.visibility = View.INVISIBLE
                binding.submitButton.visibility = View.VISIBLE
            }


        }

        // Set the onClickListener for the backButton
        binding.backButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            // Save answer in multiple choice
            when (binding.questionRadioGroup.checkedRadioButtonId) {
                R.id.firstChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 0
                R.id.secondChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 1
                R.id.thirdChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 2
                R.id.fourthChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 3
            }


            questionIndex--


            if (questionIndex < numQuestions) {
                currentMulChoQuestion = mulChoQuestions[questionIndex]
                binding.questionRadioGroup.clearCheck()

                setMulChoQuestion(binding)

            }

            // Reset fields
            binding.invalidateAll()

            // Change to multiple choice if on questions 1-10
            if (questionIndex < numQuestions) {
                binding.questionRadioGroup.visibility = View.VISIBLE
            }

            // Hide submit button if not on last question
            if (questionIndex < numQuestions-1) {
                binding.submitButton.visibility = View.GONE
            }

            // Hide back button on 1st question
            if (questionIndex == 0) {
                binding.backButton.visibility = View.INVISIBLE
            }

            // Show next button if not on last question
            if (questionIndex == numQuestions-2) {
                binding.nextButton.visibility = View.VISIBLE
            }
        }

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view : View ->
            // Check last question

            // To make sure button only works once
            if (score == 0) {

                // Count each correct answer
                mulChoQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }

//              Save score and number of questions in shared preferences
                with (sharedPref?.edit()) {
                    this?.putInt("unit2Quiz4Score", score)
                    this?.putString("unit2Quiz4NumQuestions", numQuestions.toString())
                    this?.apply()
                }
//
//                 Navigate to score screen
                view.findNavController().navigate(
                    Unit2Quiz4FragmentDirections
                        .actionUnit2Quiz4FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 2: Lesson 4 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit2Quiz4Binding) {

        // Multiple choice questions 1-10
        if (questionIndex < numQuestions) {

            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            // Check if a radio button is selected
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondChoiceRadioButton -> answerIndex = 1
                    R.id.thirdChoiceRadioButton -> answerIndex = 2
                    R.id.fourthChoiceRadioButton -> answerIndex = 3
                }

                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                currentMulChoQuestion.isCorrect = answerIndex == currentMulChoQuestion.correctIdx

//                if (currentMulChoQuestion.isCorrect) {
//                    Toast.makeText(context, "CORRECT!", Toast.LENGTH_SHORT).show()
//                }

                currentMulChoQuestion.clickedIdx = answerIndex
                binding.questionRadioGroup.clearCheck()


                // Advance to the next question
                questionIndex++



                currentMulChoQuestion = mulChoQuestions[questionIndex]
                setMulChoQuestion(binding)

                binding.invalidateAll()


                // Prompt user to select an answer
            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }

            // Identification questions 11-15
        } else {
            // For error checking only
            Toast.makeText(context, "Error index out of range: $questionIndex", Toast.LENGTH_LONG).show()
        }
    }

    // Initialize the questions and set the first question
    private fun initQuestions(binding : FragmentUnit2Quiz4Binding) {
        questionIndex = 0

        mulChoQuestions.forEach {
            val correct = it.answers[0]

            it.answers.shuffle()

            it.correctIdx = it.answers.indexOf(correct)
        }

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit2Quiz4Binding) {
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text
        // randomize the answers into a copy of the array
        answers = currentMulChoQuestion.answers

        binding.quizImage.visibility = View.GONE


        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

    }

}