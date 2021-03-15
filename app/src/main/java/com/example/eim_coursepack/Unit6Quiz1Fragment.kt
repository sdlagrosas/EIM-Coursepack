package com.example.eim_coursepack

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit4Quiz1Binding
import com.example.eim_coursepack.databinding.FragmentUnit6Quiz1Binding

class Unit6Quiz1Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. Zilong has been stressed in his family situation, in doing " +
                "the electrical work in his shop, he can’t handle it which makes him irritated " +
                "to his co-workers, what type of hazard Zilong can bring to his work place?",
            answers = mutableListOf(
                "Ergonomic Hazard",
                "Psychological Hazard",
                "Biological Hazard",
                "Physical Hazard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "2. Tigreal, always forget to dispose properly all the " +
                "unnecessary things in the shop like, garbage and stock water which mosquitos " +
                "can lay eggs and can cause of Dengue. What hazard is possible into that " +
                "situation?",
            answers = mutableListOf(
                "Ergonomic Hazard",
                "Psychological Hazard",
                "Biological Hazard",
                "Physical Hazard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2),
        MulChoQuestion(text = "3. Alucard the electrician on the shop is not able to focus on " +
                "his work because of the noise on the other side of the shop, What hazard does " +
                "Bogs encountered?",
            answers = mutableListOf(
                "Ergonomic Hazard",
                "Psychological Hazard",
                "Biological Hazard",
                "Physical Hazard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "4. Badang has the difficulty in fixing the light bulbs because " +
                "of his narrow space in installing  it, what is the possible hazard is present " +
                "to Jordan’s situation?",
            answers = mutableListOf(
                "Ergonomic Hazard",
                "Psychological Hazard",
                "Biological Hazard",
                "Physical Hazard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "5. Bruno forget to clean the floor after he spilled the bottle " +
                "of water during their snack time, water is very dangerous specially they are " +
                "in electrical shop which consist of high voltages equipment. What hazard is " +
                "possible into that situation?",
            answers = mutableListOf(
                "Ergonomic Hazard",
                "Psychological Hazard",
                "Chemical Hazard",
                "Physical Hazard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "6. This may cause a decrease in lifespan",
            answers = mutableListOf("adverse health effect", "adverse health effects"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "7. It is a source of potential damage, harm or adverse health effects",
            answers = mutableListOf("hazards", "hazard"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "8. These include vapor or gaseous substance",
            answers = mutableListOf("chemical hazards", "chemical hazard"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "9. It is the chance or the probability that a person will be harmed.",
            answers = mutableListOf("risk", "risks"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "10. These include hot or cold conditions",
            answers = mutableListOf("physical hazards", "physical hazards"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "11. This may cause slips and falls",
            answers = mutableListOf("wet floor", "wet floors"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. These include awkward posture arising from improper work methods",
            answers = mutableListOf("ergonomic hazards", "ergonomic hazard"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. Those that are basically causing stress",
            answers = mutableListOf("psychological hazards", "psychological hazard"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. These include unsafe work practices",
            answers = mutableListOf("safety hazards", "safety hazard"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Those hazards that are caused by organism such as viruses, bacteria " +
                    "fungi and parasites",
            answers = mutableListOf("biological hazards", "biological hazard"),
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
        val binding : FragmentUnit6Quiz1Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit6_quiz1, container, false
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

                setIdenQuestion(binding)
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
            val answerText = binding.answerText.text
            currentIdenQuestion.isCorrect = answerText
                .toString()
                .toLowerCase()
                .replace("\\s+".toRegex()," ")
                .trim() in currentIdenQuestion.answers

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
                idenQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }

//              Save score and number of questions in shared preferences
                with (sharedPref?.edit()) {
                    this?.putString("unit6Quiz1Score", score.toString())
                    this?.putString("unit6Quiz1NumQuestions", numQuestions.toString())
                    this?.apply()
                }
//
                // Navigate to score screen
                view.findNavController().navigate(
                    Unit6Quiz1FragmentDirections
                        .actionUnit6Quiz1FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 6: Lesson 1 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit6Quiz1Binding) {

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
                    setIdenQuestion(binding)
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

                if (questionIndex in 5 until numQuestions) {
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
                setIdenQuestion(binding)

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
    private fun initQuestions(binding : FragmentUnit6Quiz1Binding) {
        questionIndex = 0

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit6Quiz1Binding) {
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


    private fun setIdenQuestion(binding: FragmentUnit6Quiz1Binding) {
        val idenQuestionIndex = questionIndex - mulChoQuestions.size
        currentIdenQuestion = idenQuestions[idenQuestionIndex]

        questionText = currentIdenQuestion.text
        // randomize the answers into a copy of the array

        enteredAns = currentIdenQuestion.enteredAns
    }


}