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
import com.example.eim_coursepack.databinding.FragmentUnit5Quiz1Binding
import com.example.eim_coursepack.databinding.FragmentUnit5Quiz3Binding

class Unit5Quiz3Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(
            text = "1. Manong Pepito wants to check the condition of the electrical tools and " +
                    "equipment before storing, which of the following form is the best to use.",
            answers = mutableListOf(
                "Requisition slip form",
                "Inventory form",
                "Borrower’s form",
                "Work order form"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(
            text = "2. Manong Cloyd, decided to  clean the hand tools before storage, which of " +
                    "the following procedure is not applicable in cleaning the hand tools.",
            answers = mutableListOf(
                "Clean dirt and debris from tools after each use.",
                "Oil metal parts to prevent rust.",
                "Lightly sand rough wooden handles and apply linseed oil.",
                "Clean the air filter"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(
            text = "3. Manong Raymond, is cleaning the different power tools, which of the " +
                    "following procedure is not applicable in cleaning power tools?",
            answers = mutableListOf(
                "Sharpen dull blades or replace worn blades according to the owner’s manual.",
                "Replace spark plugs.",
                "Drain oil and gasoline before long-term storage.",
                "Clean dirt and debris from tools after each use."),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(
            text = "4. Storage of tools and equipment require the following, except _____.",
            answers = mutableListOf(
                "Sharpen dull blades or replace worn blades according to the owner’s manual.",
                "Replace spark plugs.",
                "Drain oil and gasoline before long-term storage.",
                "Wash the tool using detergent soap and water before storage."),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(
            text = "5. The following are the condition of tools and equipment, except _____.",
            answers = mutableListOf(
                "Repairable",
                "Washable",
                "Functional",
                "Non-Functional"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "Provide the appropriate step count in the procedure (1/6)\n" +
                    "6. Fill out the remark’s column of the inventory forms for any losses/ damages.",
            answers = mutableListOf("5", "5th", "fifth"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Provide the appropriate step count in the procedure (2/6)\n" +
                    "7. Secure inventory forms/memorandum receipt of tools and equipment.",
            answers = mutableListOf("1", "1st", "first"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Provide the appropriate step count in the procedure (3/6)\n" +
                    "8. Check whether the list of tools and equipment in the memorandum receipt " +
                    "tallies with the existing tools and equipment found in the workshop " +
                    "including their specifications and condition.",
            answers = mutableListOf("3", "3rd", "third"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Provide the appropriate step count in the procedure (4/6)\n" +
                    "9. List down any losses and damages you find while conducting the inventory",
            answers = mutableListOf("4", "4th", "fourth"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Provide the appropriate step count in the procedure (5/6)\n" +
                    "10. Study the parts of the Inventory Form",
            answers = mutableListOf("2", "2nd", "second"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "Provide the appropriate step count in the procedure (6/6)\n" +
                    "11. Recommend for replacement of lost tools and equipment and repair of " +
                    "damaged tools and equipment if reparable.",
            answers = mutableListOf("6", "6th", "sixth"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. Provide a data column found in the Inventory Form (1/4)",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. Provide a data column found in the Inventory Form (2/4)",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. Provide a data column found in the Inventory Form (3/4)",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Provide a data column found in the Inventory Form (4/4)",
            answers = mutableListOf(),
            isCorrect = false,
            enteredAns = ""
        )
    )

    private val idenEnumaration : MutableList<String> = mutableListOf(
        "quantity", "unit", "description", "condition"
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
        val binding : FragmentUnit5Quiz3Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit5_quiz3, container, false
        )

        activity

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

            if (questionIndex >= mulChoQuestions.size) {
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

            if (questionIndex < mulChoQuestions.size) {
                currentMulChoQuestion = mulChoQuestions[questionIndex]
                binding.questionRadioGroup.clearCheck()

                setMulChoQuestion(binding)

            } else {
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
            currentIdenQuestion.enteredAns = binding.answerText.text.toString()

//            if (currentIdenQuestion.isCorrect) {
//                Toast.makeText(context, "CORRECT", Toast.LENGTH_SHORT).show()
//            }

            // To make sure button only works once
            if (score == 0) {

                // Count each correct answer
                mulChoQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }
                idenQuestions.slice(0 until 6).forEach {
                    if (it.isCorrect){
                        score++
                    }
                }
                var enumAnswer : String
                idenQuestions.slice(6 until idenQuestions.size).forEach {
                    enumAnswer = it.enteredAns
                        .toLowerCase()
                        .replace("\\s+".toRegex()," ")
                        .trim()
                    if (enumAnswer in idenEnumaration){
                        idenEnumaration.remove(enumAnswer)
                        score++
                    }
                }

                // Update if new score > prev score
                val prevScore = sharedPref?.getInt("unit5Quiz3Score", 0)!!
                if (prevScore < score) {
                    // Save score and number of questions in shared preferences
                    with (sharedPref?.edit()) {
                        this?.putInt("unit5Quiz3Score", score)
                        this?.putString("unit5Quiz3NumQuestions", numQuestions.toString())
                        this?.apply()
                    }
                }

                // Navigate to score screen
                view.findNavController().navigate(
                    Unit5Quiz3FragmentDirections
                        .actionUnit5Quiz3FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 5: Lesson 3 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit5Quiz3Binding) {

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

                if (questionIndex in 5 until numQuestions-4) {
                    currentIdenQuestion.isCorrect = answerText
                        .toString()
                        .toLowerCase()
                        .replace("\\s+".toRegex()," ")
                        .trim() in currentIdenQuestion.answers

//                    if (currentIdenQuestion.isCorrect) {
//                        Toast.makeText(context, "CORRECT!", Toast.LENGTH_SHORT).show()
//                    }
                }

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
    private fun initQuestions(binding : FragmentUnit5Quiz3Binding) {
        questionIndex = 0

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit5Quiz3Binding) {
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text
        // randomize the answers into a copy of the array
        answers = currentMulChoQuestion.answers

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