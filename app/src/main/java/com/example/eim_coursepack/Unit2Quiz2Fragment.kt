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
import com.example.eim_coursepack.databinding.FragmentUnit2Quiz2Binding


class Unit2Quiz2Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. Is a device inserted to a convenience outlet to conduct electric current. A flat cord is attached to it on one end and the other end is connected to a current consuming instrument or appliance. ",
            answers = mutableListOf(
                "Male Plug",
                "Utility box",
                "Switch",
                "Fuse"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "This is a circuit protective device that automatically blows and cut the current when an overload or short circuit happens. ",
            answers = mutableListOf(
                "Fuse",
                "Utility box",
                "Switch",
                "Male plug"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "3. This is a rectangular shaped metallic or plastic (PVC) material in which flush type convenience outlet and switch are attached.",
            answers = mutableListOf(
                "Utility box",
                "Male plug",
                "Switch",
                "Fuse"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "4. They are electrical materials used as the passage of wires for protection and Insulation.",
            answers = mutableListOf(
                "Conduit pipes",
                "Switches",
                "Connectors",
                "Fuses"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "5. These are devices that hold and protect the lamp.",
            answers = mutableListOf(
                "Receptacle",
                "Connector",
                "Switch",
                "Male plug"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "6. An octagonal shaped electrical material where the connections or joints of wires are being done. It is also where the flush type lamp holder is attached.",
            answers = mutableListOf(
                "Junction box",
                "Male plug",
                "Switch",
                "Fuse"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "7. This is a circuit protective device that automatically burn itself and cut the current when an over load or short circuit happens.",
            answers = mutableListOf(
                "Fuse",
                "Utility box",
                "Switch",
                "Male plug"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "8. Material which connect and disconnect the flow of electricity in a the circuit.",
            answers = mutableListOf(
                "Switch",
                "Utility box",
                "Male plug",
                "Fuse"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "9. An electrical material which hold the conduit pipe on wall.",
            answers = mutableListOf(
                "Clamp",
                "Connector",
                "Conduit pipe",
                "Fuse"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "10. They are used to attach metallic or non-metallic conduit to the junction or utility boxes.",
            answers = mutableListOf(
                "Connectors",
                "Conduit pipe",
                "Fuses",
                "Receptacle"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "11. Electrical materials used as the passage of wires for protection and insulation.",
            answers = mutableListOf("conduit", "conduits"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. Wire which is made of multiple strands joined together to make a single wire.",
            answers = mutableListOf("stranded", "stranded wire", "stranded wires"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. A rectangular shaped metallic or plastic (PVC) material in which flush type convenience outlet and switch are attached.",
            answers = mutableListOf("utility box", "utility boxes"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. A circuit protective device that automatically blows and cut the current when and overload or short circuit happens.",
            answers = mutableListOf("fuse", "fuses"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Kind of screwdriver has a cross tip resembling a positive (+) sign.",
            answers = mutableListOf("phillip", "phillips", "phillip screwdriver", "phillips screwdriver"),
            isCorrect = false,
            enteredAns = ""
        )
    )

    private lateinit var currentMulChoQuestion: MulChoQuestion
    private lateinit var currentIdenQuestion: IdenQuestion
    lateinit var answers: MutableList<String>
    lateinit var questionText : String
    lateinit var enteredAns : String
    private var questionIndex = 0
    private val numQuestions = mulChoQuestions.size + idenQuestions.size
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit2Quiz2Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit2_quiz2, container, false
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

            // Hide radio group and show edit text in identification part of the quiz
            if (questionIndex > mulChoQuestions.size-1) {
                binding.questionRadioGroup.visibility = View.GONE
                binding.answerText.visibility = View.VISIBLE
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
            // Save answer in identification
            currentIdenQuestion.enteredAns = binding.answerText.text.toString()

            questionIndex--


            if (questionIndex in 0 until mulChoQuestions.size) {
                currentMulChoQuestion = mulChoQuestions[questionIndex]
                binding.questionRadioGroup.clearCheck()

                setMulChoQuestion(binding)

            } else if (questionIndex in mulChoQuestions.size until numQuestions) {
                currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                binding.answerText.text.clear()
                setIdenQuestion()


            }

            // Reset fields
            binding.invalidateAll()

            // Change to multiple choice if on questions 1-10
            if (questionIndex < mulChoQuestions.size) {
                binding.questionRadioGroup.visibility = View.VISIBLE
                binding.answerText.visibility = View.GONE
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
            val answerText = binding.answerText.text.toString()
            currentIdenQuestion.isCorrect = answerText
                .toLowerCase()
                .replace("\\s+".toRegex()," ")
                .trim() in currentIdenQuestion.answers

            // To make sure button only works once
            if (score == 0) {

                // Count each correct answer
                mulChoQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }
                idenQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }

//              Save score and number of questions in shared preferences
                with (sharedPref?.edit()) {
                    this?.putInt("unit2Quiz2Score", score)
                    this?.putString("unit2Quiz2NumQuestions", numQuestions.toString())
                    this?.apply()
                }
//
                // Navigate to score screen
                view.findNavController().navigate(
                    Unit2Quiz2FragmentDirections
                        .actionUnit2Quiz2FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 2: Lesson 2 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit2Quiz2Binding) {

        // Multiple choice questions 1-10
        if (questionIndex in 0 until mulChoQuestions.size) {

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


                if (questionIndex in 0 until mulChoQuestions.size) {
                    currentMulChoQuestion = mulChoQuestions[questionIndex]
                    setMulChoQuestion(binding)

                } else if (questionIndex in mulChoQuestions.size until numQuestions) {

                    currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                    setIdenQuestion()
                }
                binding.invalidateAll()


                // Prompt user to select an answer
            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }

            // Identification questions 11-15
        } else if (questionIndex in mulChoQuestions.size until numQuestions) {

            val answerText = binding.answerText.text

            if (answerText.isNotEmpty()) {
                currentIdenQuestion.isCorrect = answerText
                    .toString()
                    .toLowerCase()
                    .replace("\\s+".toRegex()," ")
                    .trim() in currentIdenQuestion.answers

//                if (currentIdenQuestion.isCorrect) {
//                    Toast.makeText(context, "CORRECT! Score:", Toast.LENGTH_SHORT).show()
//                }

                currentIdenQuestion.enteredAns = answerText.toString()

                // Advance to the next question
                questionIndex++
                currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                setIdenQuestion()

                // reset fields
                binding.invalidateAll()
                answerText.clear()


            } else {
                Toast.makeText(context,"Enter your answer", Toast.LENGTH_SHORT).show()
            }
        } else {
            // For error checking only
            Toast.makeText(context, "Error index out of range: $questionIndex", Toast.LENGTH_LONG).show()
        }
    }

    // Initialize the questions and set the first question
    private fun initQuestions(binding : FragmentUnit2Quiz2Binding) {
        questionIndex = 0

        mulChoQuestions.forEach {
            val correct = it.answers[0]

            it.answers.shuffle()

            it.correctIdx = it.answers.indexOf(correct)
        }

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit2Quiz2Binding) {
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

    private fun setIdenQuestion() {
        val idenQuestionIndex = questionIndex - mulChoQuestions.size
        currentIdenQuestion = idenQuestions[idenQuestionIndex]

        questionText = currentIdenQuestion.text
        // randomize the answers into a copy of the array

        enteredAns = currentIdenQuestion.enteredAns
    }

}